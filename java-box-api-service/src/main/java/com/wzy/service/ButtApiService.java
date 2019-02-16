package com.wzy.service;
import com.wzy.entity.*;
import com.wzy.entity.vo.BoxAppApiVo;
import com.wzy.entity.vo.BoxAppVo;
import com.wzy.entity.vo.BoxConfigVo;
import com.wzy.entity.vo.BoxWorkFilterVo;
import com.wzy.mapper.*;
import com.wzy.mapper.table.TabBoxApp;
import com.wzy.mapper.table.TabBoxWorkFilter;
import com.wzy.server.jar.loader.config.ScanJar;
import com.wzy.util.DateUtil;
import com.wzy.util.PageUtil;
import com.wzy.util.upload.FileVo;
import com.wzy.util.zookeeper.ZookeeperUtil;
import org.apache.zookeeper.KeeperException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ButtApiService{

    @Resource
    BoxAppMapper boxAppMapper;

    @Resource
    BoxAppApiMapper boxAppApiMapper;

    @Autowired
    ZookeeperUtil zookeeperUtil;

    @Resource
    BoxWorkFilterMapper boxWorkFilterMapper;

    @Resource
    BoxConnectionPoolMapper boxConnectionPoolMapper;

    @Autowired
    ConfigService configService;

    /**
     * 通过上传添加应用信息，通过扫描注解生成项目信息
     * @param scanJar
     * @param fileVo
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addProject(ScanJar scanJar, FileVo fileVo) throws Exception{
        scanJar.getBoxProjectVo().forEach(boxProjectVo -> {

            // 查看項目是否存在不存在插入新數據
            BoxApp boxApp = boxAppMapper.getRouteApp(boxProjectVo.getRoute());
            if (boxApp == null) {
                boxApp = new BoxApp();
                boxApp.setJarMd5(fileVo.getFileMd5());
                boxApp.setJarName(fileVo.getFileName());
                boxApp.setJarUrl(fileVo.getFileUrl());
                boxApp.setName(boxProjectVo.getName());
                boxApp.setRoute(boxProjectVo.getRoute());
                boxApp.setStats(0);
                boxApp.setJarType(1);
                boxAppMapper.add(boxApp);
            } else {
                boxApp.setJarMd5(fileVo.getFileMd5());
                boxApp.setJarName(fileVo.getFileName());
                boxApp.setJarUrl(fileVo.getFileUrl());
                boxApp.setName(boxProjectVo.getName());
                boxApp.setRoute(boxProjectVo.getRoute());
                boxAppMapper.update(boxApp);
            }

            // 生成访问接口信息
            BoxApp finalBoxApp = boxApp;
            scanJar.getBoxApiVoList().forEach(boxApiVo -> {
                BoxAppApi boxAppApi = boxAppApiMapper.getRouteAppApi(boxApiVo.getRoute(), finalBoxApp.getAppId().toString());
                if (boxAppApi == null) {
                    boxAppApi = new BoxAppApi();
                    boxAppApi.setAppId(finalBoxApp.getAppId());
                    boxAppApi.setJarMd5(finalBoxApp.getJarMd5());
                    boxAppApi.setStats(0);
                    boxAppApi.setName(boxApiVo.getName());
                    boxAppApi.setRoute(boxApiVo.getRoute());
                    boxAppApi.setRunClass(boxApiVo.getRunClass());
                    boxAppApi.setRunFunction(boxApiVo.getRunFunction());
                    boxAppApi.setLinkUrl(finalBoxApp.getRoute()+boxApiVo.getRoute());
                    boxAppApiMapper.add(boxAppApi);
                } else {
                    boxAppApi.setAppId(finalBoxApp.getAppId());
                    boxAppApi.setJarMd5(finalBoxApp.getJarMd5());
                    boxAppApi.setStats(0);
                    boxAppApi.setName(boxApiVo.getName());
                    boxAppApi.setRoute(boxApiVo.getRoute());
                    boxAppApi.setRunClass(boxApiVo.getRunClass());
                    boxAppApi.setRunFunction(boxApiVo.getRunFunction());
                    boxAppApi.setLinkUrl(finalBoxApp.getRoute()+boxApiVo.getRoute());
                    boxAppApiMapper.update(boxAppApi);
                }
            });
        });

        // 新增过滤器
        scanJar.getBoxFilters().forEach(boxFilter -> {
            Map<String,String> keys = new HashMap<>();
            keys.put(TabBoxWorkFilter.NAME,boxFilter.getName());
            keys.put(TabBoxWorkFilter.PATH,boxFilter.getPath());
            BoxWorkFilter boxWorkFilter = boxWorkFilterMapper.get(keys);
            if (boxWorkFilter != null) {
                if (boxWorkFilter.getJarMd5().equals(fileVo.getFileMd5()))
                return;
                else {
                    boxWorkFilter.setName(boxFilter.getName());
                    boxWorkFilter.setPath(boxFilter.getPath());
                    boxWorkFilter.setClassName(boxFilter.getClassName());
                    boxWorkFilter.setJarMd5(fileVo.getFileMd5());
                    boxWorkFilter.setJarUrl(fileVo.getFileUrl());
                    boxWorkFilterMapper.update(boxWorkFilter);
                }
            } else {
                boxWorkFilter = new BoxWorkFilter();
                boxWorkFilter.setName(boxFilter.getName());
                boxWorkFilter.setPath(boxFilter.getPath());
                boxWorkFilter.setClassName(boxFilter.getClassName());
                boxWorkFilter.setJarMd5(fileVo.getFileMd5());
                boxWorkFilter.setJarUrl(fileVo.getFileUrl());
                boxWorkFilterMapper.add(boxWorkFilter);

            }
        });

        /**
         * 存放连接池信息
         */
        scanJar.getBoxDataSources().forEach(boxDataSource -> {
            BoxConnectionPool boxConnectionPool = new BoxConnectionPool();
            boxConnectionPool.setClassName(boxDataSource.getClassName());
            boxConnectionPool.setConfigStr(boxDataSource.getConfigStr());
            boxConnectionPool.setJarMd5(fileVo.getFileMd5());
            boxConnectionPool.setJarName(fileVo.getFileName());
            boxConnectionPool.setJarUrl(fileVo.getFileUrl());
            boxConnectionPool.setMethods(boxDataSource.getMethods());
            boxConnectionPool.setName("");
            boxConnectionPoolMapper.save(boxConnectionPool);
        });

        /**
         * 存放配置信息
         */
        scanJar.getConfigs().forEach((k,v) ->{
            try {
                BoxConfigVo boxConfig = configService.get(k);
                if (boxConfig == null) {
                    BoxConfigVo boxConfigVo = new BoxConfigVo();
                    boxConfigVo.setK(k);
                    boxConfigVo.setV(v);
                    configService.add(boxConfigVo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
       return false;
    }

    /**
     * 分页查询应用列表
     * @param appName
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<BoxAppVo> getList(String appName, int pageNo, int pageSize) throws Exception {

        // 组合查询语句
        Map<String,String> keys = new HashMap<>();
        if (appName != null && !appName.trim().equals(""))
        keys.put(TabBoxApp.NAME, "like '%"+appName.trim()+"%'");
        pageNo = PageUtil.returnPageNo(pageNo,pageSize);
        keys.put("pageNo", pageNo+"");
        keys.put("pageSize", pageSize+"");

        // 查询数据
        List<BoxAppVo> boxAppVos = new ArrayList<>();
        boxAppMapper.getList(keys).forEach(boxApp -> {
            BoxAppVo boxAppVo = new BoxAppVo();
            BeanUtils.copyProperties(boxApp,boxAppVo);
            if (boxApp.getStats().equals(1)){
                boxAppVo.setStats("已发布");
            } else {
                boxAppVo.setStats("未发布");
            }
            boxAppVo.setCreateTime(DateUtil.getyyMMddHHmmss(boxApp.getCreateTime()));
            boxAppVos.add(boxAppVo);
        });
        return boxAppVos;
    }

    /**
     * 获取数据行数
     * @param appName
     * @return
     * @throws Exception
     */
    public int getListCount(String appName) throws Exception {

        // 组合查询语句
        Map<String,String> keys = new HashMap<>();
        keys.put(TabBoxApp.NAME, "like '%"+appName+"%'");
        return boxAppMapper.getListCount(keys);
    }

    /**
     * 获取应应用的接口信息
     * @return
     */
    public List<BoxAppApiVo> getBoxAppApiVoList(Integer appId) throws Exception{
        List<BoxAppApi> boxAppApis = boxAppApiMapper.getBoxAppApiList(appId);
        List<BoxAppApiVo> boxAppApiVos = new ArrayList<>();
        boxAppApis.forEach(boxAppApi -> {
            BoxAppApiVo boxAppApiVo = new BoxAppApiVo();
            BeanUtils.copyProperties(boxAppApi, boxAppApiVo);
            boxAppApiVo.setCreateTime(DateUtil.getyyMMddHHmmss(boxAppApi.getCreateTime()));
            boxAppApiVos.add(boxAppApiVo);
        });
        return boxAppApiVos;
    }

    /**
     * BOx容器获取载入的项目
     * @return
     * @throws Exception
     */
    public List<BoxApp> getBoxApp() throws Exception{
        return boxAppMapper.getList(new HashMap());
    }

    /**
     * Box容器查询应用的Api信息
     * @return
     * @throws Exception
     */
    public List<BoxAppApi> getBoxAppApi(Integer appId) throws Exception {
        return boxAppApiMapper.getBoxAppApiList(appId);
    }

    /**
     * 删除App
     * @param appId
     * @return
     * @throws Exception
     */
    @Transactional
    public int deleteApp(Integer appId) throws Exception{
        int num = boxAppMapper.deleteApp(appId);
        num+=boxAppApiMapper.deleteAppApi(appId);
        zookeeperUtil.deleteAppNode(appId);
        return num;
    }

    /**
     * 修改状态
     * @param appId
     * @param stats
     * @return
     * @throws Exception
     */
    public int updateStats(Integer appId, Integer stats) throws Exception{
        if (stats.equals(0)) {
            zookeeperUtil.deleteAppNode(appId);
        } else {
            zookeeperUtil.addAppNode(appId);
        }
        return boxAppMapper.updateStats(appId,stats);
    }

    /**
     * 查询详情
     * @param appId
     * @return
     * @throws Exception
     */
    public BoxApp getBoxApp(Integer appId) throws Exception{
        return boxAppMapper.getApp(appId);
    }

    /**
     * 获取过滤器
     * @param map
     * @return
     */
    public List<BoxWorkFilterVo> getBoxWorkFilters(Map map){
        List<BoxWorkFilter> boxWorkFilters = boxWorkFilterMapper.getList(map);
        List<BoxWorkFilterVo> boxWorkFilterVos = new ArrayList<>();
        boxWorkFilters.forEach(boxWorkFilter->{
            BoxWorkFilterVo boxWorkFilterVo = new BoxWorkFilterVo();
            BeanUtils.copyProperties(boxWorkFilter,boxWorkFilterVo);
            boxWorkFilterVo.setCreateTime(DateUtil.getyyMMddHHmmss(boxWorkFilter.getCreateTime()));
            if (boxWorkFilter.getStat().equals(0)) {
                boxWorkFilterVo.setStat("未发布");
            } else {
                boxWorkFilterVo.setStat("已发布");
            }
            boxWorkFilterVos.add(boxWorkFilterVo);
        });
        return boxWorkFilterVos;
    }

    /**
     * 获取过滤器分页数量
     * @return
     */
    public int getBoxWorkFiltersCount(Map map){
        return boxWorkFilterMapper.getListCount(map);
    }

     /**
     * 获取过滤器
     * @param map
     * @return
     */
    public List<BoxWorkFilter> getButBoxWorkFilters(Map map){
        List<BoxWorkFilter> boxWorkFilters = boxWorkFilterMapper.getList(map);
        return boxWorkFilters;
    }

    /*
    delete data
     */
    public int deleteButBoxWorkFilters(Integer id){
        try {
            zookeeperUtil.delFliters(id);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return boxWorkFilterMapper.del(id);
    }

    public int updateBoxWorkFileters(Integer id, Integer stat) {
         try {
             if (stat.equals(1))
                zookeeperUtil.addFliters(id);
             else
                zookeeperUtil.delFliters(id);
         } catch (KeeperException e) {
             e.printStackTrace();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
        return boxWorkFilterMapper.updateStart(id,stat);
    }
}
