package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V2__CreateAuthoritiesTable extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("create table authorities\n" +
                        "(\n" +
                        "    username  varchar not null,\n" +
                        "    authority varchar not null,\n" +
                        "    constraint foreign_authorities_users_1 foreign key(username) references users(username)\n" +
                        ")");
    }

}
