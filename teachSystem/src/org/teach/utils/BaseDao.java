package org.teach.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;



public class BaseDao {
	// INSERT, UPDATE, DELETE 操作都可以包含在其中
		public void update(String sql, Object... args) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			try {
				connection = JdbcUtil.getConnection();
				preparedStatement = connection.prepareStatement(sql);

				for (int i = 0; i < args.length; i++) {
					preparedStatement.setObject(i + 1, args[i]);
				}

				preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.release(null, preparedStatement, connection);
			}
		}

		// 查询一条记录, 返回对应的对象
		public <T> T get(Class<T> clazz, String sql, Object... args) {
				
			T entity = null;

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				//1. 得到 ResultSet 对象
				connection = JdbcUtil.getConnection();
				preparedStatement = connection.prepareStatement(sql);
				for (int i = 0; i < args.length; i++) {
					preparedStatement.setObject(i + 1, args[i]);
				}
				resultSet = preparedStatement.executeQuery();

				//2. 得到 ResultSetMetaData 对象
				ResultSetMetaData rsmd = resultSet.getMetaData();
				
				//3. 创建一个 Map<String, Object> 对象, 键: SQL 查询的列的别名, 
				//值: 列的值
				Map<String, Object> values = new HashMap<String,Object>();
				
				//4. 处理结果集. 利用 ResultSetMetaData 填充 3 对应的 Map 对象
				if(resultSet.next()){
					for(int i = 0; i < rsmd.getColumnCount(); i++){
						String columnLabel = rsmd.getColumnLabel(i + 1);
						Object columnValue = resultSet.getObject(i + 1);
						
						values.put(columnLabel, columnValue);
					}
				}
				
				//5. 若 Map 不为空集, 利用反射创建 clazz 对应的对象
				if(values.size() > 0){
					entity = clazz.newInstance();
					
					//5. 遍历 Map 对象, 利用反射为 Class 对象的对应的属性赋值. 
					for(Map.Entry<String, Object> entry: values.entrySet()){
						String fieldName = entry.getKey();
						Object value = entry.getValue();
						BeanUtils.setProperty(entity, fieldName, value);
					}
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.release(resultSet, preparedStatement, connection);
			}

			return entity;

		}

		/**
		 * 传入 SQL 语句和 Class 对象, 返回 SQL 语句查询到的记录对应的 Class 类的对象的集合
		 * 
		 * @param clazz
		 *            : 对象的类型
		 * @param sql
		 *            : SQL 语句
		 * @param args
		 *            : 填充 SQL 语句的占位符的可变参数.
		 * @return
		 */
		public <T> List<T> getForList(Class<T> clazz, String sql, int page , int pageSize) {
			List<T> list = new ArrayList<T>();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				// 1. 得到结果集
				connection = JdbcUtil.getConnection();
				preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setInt(1, page);
				preparedStatement.setInt(2, pageSize);
				
				resultSet = preparedStatement.executeQuery();
				// 2. 处理结果集, 得到 Map 的 List, 其中一个 Map 对象
				// 就是一条记录. Map 的 key 为 reusltSet 中列的别名, Map 的 value
				// 为列的值.
				List<Map<String, Object>> values = handleResultSetToMapList(resultSet);
				// 3. 把 Map 的 List 转为 clazz 对应的 List
				// 其中 Map 的 key 即为 clazz 对应的对象的 propertyName,
				// 而 Map 的 value 即为 clazz 对应的对象的 propertyValue
				list = transfterMapListToBeanList(clazz, values);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.release(resultSet, preparedStatement, connection);
			}
			return list;
		}
		
		
		public <T> List<T> findAll(Class<T> clazz, String sql) {
			List<T> list = new ArrayList<T>();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				// 1. 得到结果集
				connection = JdbcUtil.getConnection();
				preparedStatement = connection.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();
				// 2. 处理结果集, 得到 Map 的 List, 其中一个 Map 对象
				// 就是一条记录. Map 的 key 为 reusltSet 中列的别名, Map 的 value
				// 为列的值.
				List<Map<String, Object>> values = handleResultSetToMapList(resultSet);
				// 3. 把 Map 的 List 转为 clazz 对应的 List
				// 其中 Map 的 key 即为 clazz 对应的对象的 propertyName,
				// 而 Map 的 value 即为 clazz 对应的对象的 propertyValue
				list = transfterMapListToBeanList(clazz, values);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.release(resultSet, preparedStatement, connection);
			}
			return list;
		}

		// 返回某条记录的某一个字段的值 或 一个统计的值(一共有多少条记录等.)
		public int getTotalNums(String sql) {
			// 1. 得到结果集: 该结果集应该只有一行, 且只有一列
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			int count = 0 ;
			try {
				// 1. 得到结果集
				connection = JdbcUtil.getConnection();
				preparedStatement = connection.prepareStatement(sql);
				
				resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					count = resultSet.getInt(1);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				JdbcUtil.release(resultSet, preparedStatement, connection);
			}
			// 2. 取得结果

			return count;
		}
		
		private <T> List<T> transfterMapListToBeanList(Class<T> clazz,
				List<Map<String, Object>> values) throws Exception {
			List<T> result = new ArrayList<T>();
			T bean = null;
			if (values.size() > 0) {
				for (Map<String, Object> m : values) {
					bean = clazz.newInstance();
					for (Map.Entry<String, Object> entry : m.entrySet()) {
						String propertyName = entry.getKey();
						Object value = entry.getValue();

						BeanUtils.setProperty(bean, propertyName, value);
					}
					// 把 Object 对象放入到 list 中.
					result.add(bean);
				}
			}
			return result;
		}

		/**
		 * 处理结果集, 得到 Map 的一个 List, 其中一个 Map 对象对应一条记录
		 * 
		 * @param resultSet
		 * @return
		 * @throws SQLException
		 */
		private List<Map<String, Object>> handleResultSetToMapList(
				ResultSet resultSet) throws SQLException {
			// 准备一个 List<Map<String, Object>>:
			// 键: 存放列的别名, 值: 存放列的值. 其中一个 Map 对象对应着一条记录
			List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
			List<String> columnLabels = getColumnLabels(resultSet);
			Map<String, Object> map = null;
			// 处理 ResultSet, 使用 while 循环
			while (resultSet.next()) {
				map = new HashMap<String, Object>();
				for (String columnLabel : columnLabels) {
					Object value = resultSet.getObject(columnLabel);
					map.put(columnLabel, value);
				}
				// 把一条记录的一个 Map 对象放入 5 准备的 List 中
				values.add(map);
			}
			return values;
		}

		/**
		 * 获取结果集的 ColumnLabel 对应的 List
		 * 
		 * @param rs
		 * @return
		 * @throws SQLException
		 */
		private List<String> getColumnLabels(ResultSet rs) throws SQLException {
			List<String> labels = new ArrayList<String>();
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				labels.add(rsmd.getColumnLabel(i + 1));
			}
			return labels;
		}
}
