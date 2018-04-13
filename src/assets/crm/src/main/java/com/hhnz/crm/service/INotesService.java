package com.hhnz.crm.service;

import com.hhnz.crm.model.TNotes;
import com.hhnz.util.AjaxDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2016-6-27.
 */
public interface INotesService {
    public TNotes findById(Long id)throws Exception;

    Long addNotes(TNotes notes)throws Exception;

    AjaxDTO findNotes(Map<String, Object> stringObjectHashMap, AjaxDTO bean)throws Exception;

    Integer delNotes(Long id)throws Exception;

    Integer updateNotes(TNotes model)throws Exception;

    /**
     * 提交审批
     * @param id
     * @return
     * @throws Exception
     */
    int applyNotes(Long id,String state,String checker)throws Exception;

    void fillNoteUrl(List<TNotes> notes);

    int lasterNote(Long noteid);
}
