package com.coderman.system.service.impl;

import com.coderman.common.enums.system.UserStatusEnum;
import com.coderman.common.error.SystemCodeEnum;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Dictionary;
import com.coderman.common.model.system.RunText;
import com.coderman.common.vo.system.PageVO;
import com.coderman.common.vo.system.RunTextVO;
import com.coderman.system.mapper.RunTextMapper;
import com.coderman.system.service.RunTextService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RunTextServiceImpl implements RunTextService {

    @Autowired
    private RunTextMapper runTextMapper;

    @Override
    public PageVO<RunTextVO> findRunTextList(Integer pageNum, Integer pageSize, RunText runText) {
        PageHelper.startPage(pageNum, pageSize);
        String message = runText.getMessage();
        Example o = new Example(RunText.class);
        Example.Criteria criteria = o.createCriteria();
        if (message != null && !"".equals(message)) {
            criteria.andLike("message", "%" + message + "%");
        }
        List<RunText> runTexts = runTextMapper.selectByExample(o);
        List<RunTextVO> voList = new ArrayList<>();
        runTexts.stream().forEach(d-> {
            RunTextVO vo = new RunTextVO();
            BeanUtils.copyProperties(d, vo);
            vo.setStatus(d.getStatus()==1? false : true);
            voList.add(vo);
        });
        PageInfo<RunText> runTextPageInfo = new PageInfo<>(runTexts);

        return new PageVO<RunTextVO>(runTextPageInfo.getTotal(), voList);
    }

    @Override
    public void add(RunText runText) {
        runText.setLoadTime(new Date());
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
        runText.setLoadTime(new Date());
        runTextMapper.updateByPrimaryKeySelective(runText);
    }

    @Override
    public List<RunText> findAll() {
        return runTextMapper.selectAll();
    }

    @Transactional
    @Override
    public void updateStatus(Long id, Boolean status) throws SystemException {
        RunText d = new RunText();
        d.setId(id);
        d.setStatus(status ? UserStatusEnum.DISABLE.getStatusCode() :
                UserStatusEnum.AVAILABLE.getStatusCode());
        d.setLoadTime(new Date());
        runTextMapper.updateByPrimaryKeySelective(d);

        if (!status) {
            Example o = new Example(RunText.class);
            o.createCriteria()
                    .andNotEqualTo("id", id)
                    .andEqualTo("status", 1);
            List<RunText> runTexts = runTextMapper.selectByExample(o);
            for (RunText r : runTexts) {
                RunText rt = new RunText();
                rt.setId(r.getId());
                rt.setStatus(0);
                rt.setLoadTime(new Date());
                runTextMapper.updateByPrimaryKeySelective(rt);
            }
        }
    }
}
