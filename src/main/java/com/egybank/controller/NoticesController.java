package com.egybank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egybank.dal.entities.Notice;
import com.egybank.dal.repositories.NoticeRepository;

@RestController
@RequestMapping("/public")
public class NoticesController {

	@Autowired
	private NoticeRepository noticeRepository;

	@GetMapping("/notices")
	public List<Notice> getNotices() {
		return noticeRepository.findAllActiveNotices();
	}

}
