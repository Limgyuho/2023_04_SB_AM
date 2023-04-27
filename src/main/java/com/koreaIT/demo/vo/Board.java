package com.koreaIT.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int id;
	private String regDate;
	private String updateDate;
	private String code;
	private String name;
	private int delStatus;
	private String delDate;
}