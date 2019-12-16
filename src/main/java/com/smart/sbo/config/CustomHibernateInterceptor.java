package com.smart.sbo.config;

import org.springframework.security.core.context.SecurityContextHolder;
import static net.logstash.logback.argument.StructuredArguments.kv;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.smart.sbo.domain.base.BaseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import java.io.Serializable;
import java.util.Iterator;

@Component
public class CustomHibernateInterceptor extends EmptyInterceptor {

    private String crudtype = "";
    private static final long serialVersionUID = -1;
    private static final Logger LOGGER = LogManager.getLogger("jsonLogger");

    @Override
    public String onPrepareStatement(String sql) {
        crudtype = "";
        if (sql.startsWith("update")) {
            crudtype = "UPDATE";
        } else if (sql.startsWith("insert")) {
            crudtype = "INSERT";
        }
        return super.onPrepareStatement(sql);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void postFlush(Iterator entities) {
        super.postFlush(entities);
        while (entities.hasNext()) {
            Object entityObj = entities.next();
            if (crudtype.equals("INSERT") || crudtype.equals("UPDATE")) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                LOGGER.info("", kv("type", crudtype), kv("entity", entityObj), kv("className", entityObj.getClass().toString()), kv("id", BaseEntity.class.cast(entityObj).getId().toString()), kv("user", authentication == null ? "" : authentication.getName()));
            }
        }
    }

    @Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        super.onDelete(entity, id, state, propertyNames, types);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("", kv("type", "DELETE"), kv("entity", entity), kv("className", entity.getClass().toString()), kv("id", id), kv("user", authentication == null ? "" : authentication.getName()));
    }

}