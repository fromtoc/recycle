package com.coderman.system.service.impl;

import com.coderman.common.error.SystemCodeEnum;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Log;
import com.coderman.common.model.system.LoginLog;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.vo.system.LogVO;
import com.coderman.common.vo.system.PageVO;
import com.coderman.system.mapper.LogMapper;
import com.coderman.system.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/4/2 20:24
 * @Version 1.0
 **/
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 保存登入日志
     * @param log
     */
    @Override
    public void saveLog(Log log) {
        log.setLoadTime(new Date());
        logMapper.insert(log);
    }


    /**
     * 删除操作日志
     * @param id
     */
    @Override
    public void delete(Long id) throws SystemException {
        Log log = logMapper.selectByPrimaryKey(id);
        if(log==null){
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR,"要删除的操作日志不存在");
        }
        logMapper.deleteByPrimaryKey(id);
    }


    @Override
    public PageVO<LogVO> findLogList(Integer pageNum, Integer pageSize, LogVO logVO) {
        PageHelper.startPage(pageNum,pageSize);
        Example o = new Example(Log.class);
        Example.Criteria criteria = o.createCriteria();
        o.setOrderByClause("create_time desc");
        if(logVO.getLocation()!=null&&!"".equals(logVO.getLocation())){
            criteria.andLike("location","%"+logVO.getLocation()+"%");
        }
        if(logVO.getIp()!=null&&!"".equals(logVO.getIp())){
            criteria.andLike("ip","%"+logVO.getIp()+"%");
        }
        if(logVO.getUsername()!=null&&!"".equals(logVO.getUsername())){
            criteria.andLike("username","%"+logVO.getUsername()+"%");
        }
        if(logVO.getOperation()!=null&&!"".equals(logVO.getOperation())){
            criteria.andLike("operation","%"+logVO.getOperation()+"%");
        }
        List<Log> loginLogs = logMapper.selectByExample(o);
        List<LogVO> logVOS=new ArrayList<>();
        if(!CollectionUtils.isEmpty(loginLogs)){
            for (Log loginLog : loginLogs) {
                LogVO logVO1 = new LogVO();
                BeanUtils.copyProperties(loginLog,logVO1);
                logVOS.add(logVO1);
            }
        }
        PageInfo<Log> info=new PageInfo<>(loginLogs);
        return new PageVO<>(info.getTotal(),logVOS);
    }

    /**
     * 批量删除
     * @param list
     */
    @Override
    public void batchDelete(List<Long> list) throws SystemException {
        for (Long id : list) {
            Log log = logMapper.selectByPrimaryKey(id);
            if(log==null){
                throw new SystemException(SystemCodeEnum.PARAMETER_ERROR,"id="+id+",操作日志不存在");
            }
            delete(id);
        }
    }

    @Override
    public void saveEasyLog(Log log) {
        log.setLoadTime(new Date());
        log.setCreateTime(new Date());
        ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        log.setUsername(activeUser.getUser().getUsername());
        logMapper.insert(log);
    }
}
