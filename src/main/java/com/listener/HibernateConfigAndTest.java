package com.listener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.seller.entity.SellerVO;

import util.HibernateUtil;

public class HibernateConfigAndTest {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
	}
}
