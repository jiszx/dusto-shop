package com.hhnz.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.mapper.TNotesMapper;
import com.hhnz.crm.model.TNotes;
import com.hhnz.crm.model.TNotesExample;
import com.hhnz.crm.service.INotesService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

/**
 * Created by yang on 2016-6-27.
 */
@Service
@Transactional
public class NotesServiceImpl implements INotesService {

    @Autowired
    private TNotesMapper mapper;

    @Override
    public TNotes findById(Long id) throws Exception {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Long addNotes(TNotes notes) throws Exception {
        notes.setStat("1");
        notes.setCreateTs(new Date());
        int res = this.mapper.insert(notes);
        if(res > 0){
            return notes.getId();
        }else{
            return 0L;
        }
    }

    @Override
    public AjaxDTO findNotes(Map<String, Object> params, AjaxDTO bean) throws Exception {
        Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        TNotesExample ex =new TNotesExample();
        ex.setPage(page);
        TNotesExample.Criteria exc = ex.createCriteria();
        if(params !=null && params.size() > 0){
            if(params.containsKey("DEPT")){
                exc.andAreaLike(params.get("DEPT")+"%");
            }
            if(params.containsKey("STATE")){
                exc.andStatEqualTo((String) params.get("STATE"));
            }
        }
        if(StringUtils.isNoneBlank(bean.getSearch())){
        	exc.andTitleLike(bean.getSearch()+"%");
        }
        if(params.containsKey("SORT")){
            ex.setOrderByClause((String)params.get("SORT"));
        }else{
        	ex.setOrderByClause("create_ts desc");
        }
        if(params.containsKey("noteid")){
          exc.andIdGreaterThan((Long)params.get("noteid"));
        }
        List<TNotes> list = this.mapper.selectByExample(ex);
        int total = this.mapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
        return bean;
    }
    
    @Override
    public void fillNoteUrl(List<TNotes> notes){
      if(notes==null || notes.isEmpty()){
        return;
      }
      for(TNotes note:notes){
        note.setUrl("/api/common/note/detail?id="+note.getId());
      }
    }

    @Override
    public Integer delNotes(Long id) throws Exception {
        TNotes notes = this.mapper.selectByPrimaryKey(id);
        if(notes !=null){
            notes.setStat("9");
            return this.mapper.updateByPrimaryKey(notes);
        }else{
            return 0;
        }
    }
    @Override
    public Integer updateNotes(TNotes model) throws Exception {
        TNotesExample ex = new TNotesExample();
        ex.createCriteria().andIdEqualTo(model.getId());
        return this.mapper.updateByExampleSelective(model,ex);
    }
    
    @Override
    public int lasterNote(Long noteid){
      if(noteid==null){
        noteid = 0L;
      }
      TNotesExample ex = new TNotesExample();
      ex.createCriteria().andIdGreaterThan(noteid);
      return mapper.countByExample(ex);
    }

    @Override
    public int applyNotes(Long id, String state,String checker) throws Exception {
        TNotes model = new TNotes();
        model.setChecker(checker);
        model.setCheckTs(new Date());
        model.setId(id);
        model.setStat(state);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        model.setReleaseTs(sf.format(new Date()));
        return this.mapper.updateByPrimaryKeySelective(model);
    }
}
