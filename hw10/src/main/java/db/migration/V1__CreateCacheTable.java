package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V1__CreateCacheTable extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("CREATE TABLE cache(id serial primary key,\n" +
                        "    first_argument varchar not null,\n" +
                        "    second_argument varchar not null,\n" +
                        "    operator varchar not null,\n" +
                        "    result varchar not null)");
    }
}
