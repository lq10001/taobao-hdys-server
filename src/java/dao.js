var ioc = {
		dataSource : {
			type : 'com.alibaba.druid.pool.DruidDataSource',
			fields:{
				driverClassName:'com.mysql.jdbc.Driver',
				url:'jdbc:mysql://localhost/taobao?useUnicode=true&characterEncoding=utf-8',
				username : 'root',
				password : 'asdf'
			}
		},

	dao : {
		type : 'org.nutz.dao.impl.NutDao',
		args : [ {
			refer : 'dataSource'
		} ]
	},
	
	/*
	 * 临时文件池
	 */
	tmpFilePool : {
		type : 'org.nutz.filepool.NutFilePool',
		args : [ "~/upload", 8192 ]
	},

	/*
	 * 上传文件用适配器的上下文
	 */
	uploadFileContext : {
		type : 'org.nutz.mvc.upload.UploadingContext',
		singleton : false,
		args : [ {
			refer : 'tmpFilePool'
		} ],
		fields : {
			ignoreNull : true,
			maxFileSize : {
				java : 1048576
			}
		}
	},

	/*
	 * 上传文件用适配器
	 */
	uploadFile : {
		type : 'org.nutz.mvc.upload.UploadAdaptor',
		singleton : false,
		args : [ {
			refer : 'uploadFileContext'
		} ]
	}

};