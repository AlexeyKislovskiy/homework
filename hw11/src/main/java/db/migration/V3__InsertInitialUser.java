package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V3__InsertInitialUser extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("insert into users(username, password, firstname, lastname)\n" +
                        "values ('admin', '$2a$10$oeitDemctXqD7ywVfLP9ZeRZfVdgIO/57C0nnTsbEuR5YzoLRHGF.', 'admin', 'admin');\n" +
                        "insert into authorities (username, authority)\n" +
                        "values ('admin', 'ROLE_USER')");
    }

}

