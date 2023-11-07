package com.spring.community.member.VO;

import java.sql.Date;

import org.springframework.stereotype.Component;

//VO란?
//Value Object라는 뜻으로
//역할1. 데이터베이스에 저장된 회원 한사람의 정보를 조회한 후
//     VO클래스의 변수에 저장할 역할
//역할2. 새로운 회원정보 하나를 데이터베이스에 insert하기전에
//     임시로 VO클래스의 각변수에 저장할 역할

@Component("memberVO")
public class MemberVO {

	//변수
	//t_member테이블의 컬럼 이름과 동일한 자료형과 이름으로 
	//변수들을 선언합니다.
	private String id;
	private String password;
	private String name;
	private String ssn;
	private String nickname;
	private String email;
	private String addr1;
	private String addr2;
	private String addr3;
	private String addr4;
	private String fileName;
	private String fileRealName;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String id, String password, String name, String ssn, String nickname, String email, String addr1,
			String addr2, String addr3, String addr4, String fileName, String fileRealName) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.ssn = ssn;
		this.nickname = nickname;
		this.email = email;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.addr3 = addr3;
		this.addr4 = addr4;
		this.fileName = fileName;
		this.fileRealName = fileRealName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getAddr3() {
		return addr3;
	}

	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}

	public String getAddr4() {
		return addr4;
	}

	public void setAddr4(String addr4) {
		this.addr4 = addr4;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileRealName() {
		return fileName;
	}

	public void setFileRealName(String fileName) {
		this.fileName = fileName;
	}

}









