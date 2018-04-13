package com.hhnz.interceptor;

import com.hhnz.util.ReflectHelper;
import com.hhnz.util.SQLBuild;
import com.hhnz.util.WebSystemContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Objects;
import java.util.Properties;

/**
 * Created by 杨 on 2017/1/11.
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class }) })
public class PaginatorInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
//        if (arg0.getArgs().length > 1) {
//            parameter = arg0.getArgs()[1];
//        }
//        String sqlId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String sql = boundSql.getSql();
        if(sql.toUpperCase().contains("ROWNUM")){

        }else{
            if(sql.toUpperCase().startsWith("SELECT") && WebSystemContext.getPageSize() !=0){
                if(sql.toUpperCase().startsWith("SELECT COUNT(")){
                    String pageSQL = SQLBuild.pagerLimit(sql, WebSystemContext.getOffSet(),WebSystemContext.getPageSize());
                    ReflectHelper.setFieldValue(boundSql,"sql", pageSQL);
                }else{

                }

            }
        }
        try{
            Object rel = invocation.proceed();
            return rel;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    @Override
    public void setProperties(Properties arg0) {
    }
}
