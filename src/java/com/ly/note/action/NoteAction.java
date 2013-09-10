package com.ly.note.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.ly.comm.CommAction;
import com.ly.comm.CommDate;
import com.ly.comm.Page;
import com.ly.comm.ParseObj;
import com.ly.note.vo.Note;

@IocBean
@InjectName("NoteAction")
@At("/note")
@Fail("json")
@Filters( { @By(type = CheckSession.class, args = { "username", "/WEB-INF/login.jsp" }) })
public class NoteAction extends CommAction{
	
	private static final Log log = Logs.getLog(NoteAction.class);

	@Inject
	private Dao dao;
	
	
	@At
	@Ok("jsp:/WEB-INF/page/note/note_list.jsp")
	public void list(@Param("..") Page p,HttpServletRequest request,@Param("..")Note note) {
            
            String name = (String) request.getSession().getAttribute("username");
            if (name.equals("dongfangx1")) {
                		Cnd c = new ParseObj(note).getCnd();
		List<Note> note_list = null;
		if (note.getDay() == null) {
			note_list = dao.query(Note.class, Cnd.wrap("ORDER BY day desc") , p);
		}else{
			note_list = dao.query(Note.class, c.desc("day") , p);
		}
		
		p.setRecordCount(dao.count(Note.class, c));
		request.setAttribute("note", note);
		request.setAttribute("note_list", note_list);
		request.setAttribute("page", p);
            }else{
                		request.setAttribute("note", note);
		request.setAttribute("note_list", null);
		request.setAttribute("page", p);

            }
            
	}
	
	@At
	@Ok("jsp:/WEB-INF/page/note/note_review.jsp")
	public void review(@Param("..") Page p,HttpServletRequest request,@Param("..")Note note) throws ParseException {
                        String name = (String) request.getSession().getAttribute("username");
            if (name.equals("dongfangx1")) {
            
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		if (note.getDay() == null) {
			note.setDay(date);
		}
		List<Note> note_list = dao.query(Note.class, Cnd.where("day", "=", sdf.parse(sdf.format(note.getDay()))));
		
		CommDate commDate = new CommDate();
		Date preWeek = commDate.preWeek(note.getDay());
		note_list.addAll(dao.query(Note.class, Cnd.where("day", "=", sdf.parse(sdf.format(preWeek)))));
		
		Date preMonth = commDate.preMonth(note.getDay());
		note_list.addAll(dao.query(Note.class, Cnd.where("day", "=", sdf.parse(sdf.format(preMonth)))));
		
		Date preYear = commDate.preYear(note.getDay());
		note_list.addAll(dao.query(Note.class, Cnd.where("day", "=", sdf.parse(sdf.format(preYear)))));
		
		request.setAttribute("note", note);
		request.setAttribute("note_list", note_list);
		p.setRecordCount(10);
		request.setAttribute("page", p);
            }else{
                		request.setAttribute("note", note);
		request.setAttribute("note_list", null);
		p.setRecordCount(10);
		request.setAttribute("page", p);

            }
	}

	@At
	@Ok("jsp:/WEB-INF/page/note/note.jsp")
	public void edit(Integer noteid,HttpServletRequest request) {
		log.debug(noteid);
//		request.setAttribute("dept_tree", getDeptTree());
		if(noteid == null || noteid == 0){
			request.setAttribute("note", null);
		}else{
			Note note = this.dao.fetch(Note.class, noteid);
			request.setAttribute("note", note);
		}
	}

	
	@At
	@Ok("json")
	public Map<String, String> save(Note note){
		log.debug(note.getInfo());
		Object rtnObject;
		if (note.getNoteid() == null || note.getNoteid() == 0) {
			Note tNote = this.dao.fetch(Note.class, Cnd.where("day", "=", note.getDay()));
			if (tNote != null) {
				tNote.setInfo(tNote.getInfo() +"</br>"+note.getInfo());
				rtnObject = this.dao.update(tNote);
			}else{
				rtnObject = this.dao.insert(note);
			}
		}else{
			rtnObject = this.dao.update(note);
		}
		return super.tabMap((rtnObject != null)?"200":"300", "note","closeCurrent");
	}
	
	
	@At
	@Ok("json")
	public Map<String, String> del(@Param("noteid")Integer noteId){
		Note note  = dao.fetch(Note.class, noteId);
		Object rtnObj =  dao.delete(note);
		return super.tabMap((rtnObj != null)?"200":"300", "note","");
	}
}
