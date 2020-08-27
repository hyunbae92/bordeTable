package com.table.borde.vo;

import lombok.Data;

@Data
public class PostVO {

	private int postNo;
	private String postTitle;
	private String postContent;
	private String postCrename;
	private String postCredat;
	private String postUpName;
	private String postUpdateDat;
	private int postUpdateCnt;
	private int postLocation;
	private int postAdmin;
	private String cmd;
	//create table post_list(
	//post_no NUMBER(10,0) primary key,
	//post_title VARCHAR2(100) not null,
	//post_content varchar2(1000) not null,
	//post_crename VARCHAR2(30) not null,
	//post_credat CHAR(11) not null,
	//post_update_name varchar2(30),
	//post_update_dat char(11),
	//post_update_cnt number(10,0)DEFAULT 0,
	//post_location number(3,0)default 0,
	//post_admin CHAR(1) default 0
	//);
}