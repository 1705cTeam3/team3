package com.jk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jk.provide.entity.LogDTO;
import com.jk.provide.entity.Operationlogs;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-mongo.xml"})
public class Text {
	@Autowired
	private MongoTemplate mongoTemplate;
	@Test
	public void add(){
		
		/*logid;//主键   
		classname;//类
		methodname;//
		cztime;//操作时间
		cname;//操作人  
		cip;//操作者的ip 
		status;//地位; 
*/		
	/*	LogDTO dl=new LogDTO();
	     
		 dl.setCip("192.168.1.11");
		 dl.setCztime("2017-12-12");
	     dl.setClassname("sdf");
	     dl.setFileName("sfs");
	     dl.setLineNumber(11);
	     dl.setLogid("ewwe");
	     dl.setMethodname("wewret");
	     dl.setStatus("a");
	     dl.setCname("dsfsd");
		mongoTemplate.save(dl);*/
		
		
		Operationlogs oper=new Operationlogs();

        oper.setOperlogid("11");
        oper.setTomcatport(22);
        oper.setOpercontroller("sds");
        oper.setOperMethod("dss");
        oper.setOperParams("dss");
        oper.setOperURI("sd");
        oper.setOpertime("sdf");
        oper.setComputerIp("sdf");
		oper.setComputerName("sfgdf");//访问者计算机名称
		oper.setOperloginId("sds");
    	oper.setOperlognsolr("dtg");
    	oper.setHaoshi("11");
    	mongoTemplate.insert(oper);
		
		//添加树的数据
		/*Tree tree =new Tree();
		tree.setPid("59cb8c247b7be72db0e297b4");
		tree.setText("赵倪妮的人生");
		tree.setHref(""2017-12-12"");
		mongoTemplate.insert(tree);*/
		
		
		//添加员工的数据
		/*for (int i = 0; i < 10; i++) {
		 
			EmpDto emp = new EmpDto();
			emp.setSex(0);
			emp.setEmpname("阳历"+i);
			emp.setPassword("123456");
			emp.setTelphone("1511047821");
			emp.setBirthday("2017-12-12");
			mongoTemplate.insert(emp);
		}*/
		
		
		
		
		//添加充值表的数据
		/*Cost cc = new Cost();
		cc.setCosttype(1);
		cc.setCostmoney(200);
		cc.setCostname("话费");
		cc.setCostsort(i+1);
		cc.setCoststatus(1);
		mongoTemplate.save(cc);*/
	
		
		//图书作者
/*	Author author = new Author();
		author.setAuthorname("小李说");
		mongoTemplate.insert(author);*/
		/*//图书类型
		Types tt = new Types();
		tt.setTypename("文学");
		mongoTemplate.insert(tt);*/
		for (int i = 0; i < 1; i++) {
		
			//添加续费表的数据
			/*Server se = new Server();
			se.setServerName("华北二区服务器");
			se.setServerStatus(1);
			se.setServerDate(new Date());
			//se.setServerDate("2016-10-10");
			se.setServerHost("169.0.0.1");
			mongoTemplate.insert(se);*/
			
			 //添加文件表的数据
			/*	
				Files ff = new Files();
				ff.setFiledate(new Date());
				ff.setFiletype(1);
				ff.setFiletop((long) 0);
				ff.setFilename("java");
				ff.setFiletitle("java特性"+"i");
				mongoTemplate.save(ff);*/
			
			//图书
		/*Book bb = new Book();
			bb.setBookname("西游记"+i);
			bb.setPrice(20+i);
			bb.setIntro("女儿国"+i);
			bb.setAuthorid("59d7927f9c7b0c1eb87e3a9f");
			bb.setTypeid("59d7959c78f528868bebbb95");
			bb.setBookimg("");
			mongoTemplate.insert(bb);*/
			
		}
		
	}
	
	

}
