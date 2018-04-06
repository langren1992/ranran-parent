package com.ranran.system.operate.controller.impl;

import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.core.redis.RanranRedisTemplate;
import com.ranran.system.operate.controller.TsDictRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 数据字典请求控制类
 * @creator zengrui 2018-01-21 11:14
 */
@RestController(value = "TsDictRestController")
@RequestMapping("/tsDict")
public class TsDictRestControllerImpl extends RestBaseController implements TsDictRestController {

    @Autowired
    private TsDictRestController tsDictRestController;

    @Autowired
    private RanranRedisTemplate ranranRedisTemplate;

    /**
     * 查询数据字典信息
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果,
     */
    @Override
    @PostMapping("/selectTsDict.html")
    public ResponseResult selectTsDict(HttpServletRequest request) {
        return tsDictRestController.selectTsDict(request);
    }

    /**
     * 数据字典资源信息
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/selectTsDictTsResource.html")
    public ResponseResult selectTsDictTsResource(HttpServletRequest request) {
        return tsDictRestController.selectTsDictTsResource(request);
    }

    /**
    * 新增、启用、停用、删除（逻辑阐述）数据字典
    *
    * @param request 请求参数
    * @return ResponseResult 响应结果
    */
    @Override
    @PostMapping("/updateTsDicts.html")
    public ResponseResult updateTsDicts(HttpServletRequest request) {
        return tsDictRestController.updateTsDicts(request);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/test.html")
    public ResponseResult test(String key,String value) throws IOException {
//        ranranRedisTemplate.setForString(key,value);
//        List<String> list = new ArrayList<String>();
//        list.add("a");
//        list.add("bb");
//        ranranRedisTemplate.setForJsonList(key,list);
//        System.out.printf(Arrays.toString(ranranRedisTemplate.getForList(key,String.class).toArray()));
//        MongoTestVo mongoTestVo =new  MongoTestVo();
//        mongoTestVo.setId("1");
//        mongoTestVo.setCode(1);
//        mongoTestVo.setName("111111");
//        mongoTemplate.save(mongoTestVo);
//        Criteria criteria = new Criteria();
//        criteria.and("id").is("1");
//        System.out.printf(Arrays.toString(mongoTemplate.find(Query.query(criteria),MongoTestVo.class).toArray()));
//        DB db = this.mongoTemplate.getDb();
//        GridFS gridfs = new GridFS(db);
//        GridFSInputFile file = gridfs.createFile(new File("C:\\Users\\zengrui\\Desktop\\往期直播视频学习.txt"));
//        file.save();
//        List<GridFSDBFile> gridFSDBFiles = gridfs.find("往期直播视频学习.txt");
//        GridFSDBFile gridFSDBFile = gridFSDBFiles.get(0);
//        gridFSDBFile.writeTo("C:\\Users\\zengrui\\Desktop\\aaa.txt");
//        RedisTemplate redisTemplate = ranranRedisTemplate.getRedisTemplate();
//        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
//        ranranRedisTemplate.setForString("a","11111");
//        ranranRedisTemplate.setForString("b","22222");
//        ranranRedisTemplate.setForString("c","33333");
//        ranranRedisTemplate.setForString("d","44444");
//        String keys = ranranRedisTemplate.getForString("a");
//        Set<String> keys1 = redisTemplate.keys("*");
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        for (String k: keys1) {
//            System.out.printf((String) valueOperations.get(k));
//        }
//        List<DictRedis.TsValue> list = new ArrayList<DictRedis.TsValue>();
//        DictRedis dictRedis = new DictRedis();
//        dictRedis.setTsKey("RESOURCE_STATUS");
//        DictRedis.TsValue dictRedis1 = new DictRedis.TsValue();
//        dictRedis1.setTsCode("1");
//        dictRedis1.setTsName("启用");
//        list.add(dictRedis1);
//        dictRedis1 = new DictRedis.TsValue();
//        dictRedis1.setTsCode("0");
//        dictRedis1.setTsName("停用");
//        list.add(dictRedis1);
//        dictRedis.setTsValues(list);
//        ranranRedisTemplate.setForString(dictRedis.getKey(),dictRedis.getValue());
//        String forString = ranranRedisTemplate.getForString(dictRedis.getKey());
//        List<DictRedis.TsValue> tsValues = JSONArray.parseArray(forString, DictRedis.TsValue.class);

        return null;
    }
}
