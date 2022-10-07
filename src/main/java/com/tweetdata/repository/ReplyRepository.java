package com.tweetdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetdata.bean.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Integer>{

}
