package com.coderman.system.service.impl;

import com.coderman.common.enums.system.UserStatusEnum;
import com.coderman.common.error.SystemCodeEnum;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Dictionary;
import com.coderman.common.model.system.RunText;
import com.coderman.common.vo.system.PageVO;
import com.coderman.system.mapper.RunTextMapper;
import com.coderman.system.service.RunTextService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RunTextServiceImpl implements RunTextService {

    @Autowired
    private RunTextMapper runTextMapper;

    @Override
    public PageVO<RunText> findRunTextList(Integer pageNum, Integer pageSize, RunText runText) {
        PageHelper.startPage(pageNum, pageSize);
        String message = runText.getMessage();
        Example o = new Example(RunText.class);
        Example.Criteria criteria = o.createCriteria();
        if (message != null && !"".equals(message)) {
            criteria.andLike("message", "%" + message + "%");
        }
        List<RunText> runTexts = runTextMapper.selectByExample(o);

        PageInfo<RunText> runTextPageInfo = new PageInfo<>(runTexts);
        return new PageVO<RunText>(runTextPageInfo.getTotal(), runTexts);
    }

    @Override
    public void add(RunText runText) {
        runTextMapper.insert(runText);
    }

    @Override
    public RunText selectByPrimaryKey(Long id) throws SystemException {
        return runTextMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Long id, RunText runText) throws SystemException {
        RunText originRunText = runTextMapper.selectByPrimaryKey(id);
        if(originRunText==null){
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR,"要更新的跑馬燈不存在");
        }
        runTextMapper.updateByPrimaryKeySelective(runText);
    }

    @Override
    public List<RunText> findAll() {
        return runTextMapper.selectAll();
    }

    @Override
    public void updateStatus(Long id, Boolean status) throws SystemException {
        RunText d = new RunText();
        d.setId(id);
        d.setStatus(status ? UserStatusEnum.DISABLE.getStatusCode() :
                UserStatusEnum.AVAILABLE.getStatusCode());
        runTextMapper.updateByPrimaryKeySelective(d);
    }
}
