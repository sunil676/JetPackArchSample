/*package com.sunil.arch.ui.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.sqlite.db.SupportSQLiteDatabase



@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val TEST_DB_NAME = "migration-test"

    @Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        MigrationDb::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun migrationFrom2To3_containsCorrectData() {
        // Create the database in version 2
        val db = helper.createDatabase(TEST_DB_NAME, 2)
        // Insert some data
        insertMovie(MOVIE.getId(), MOVIE.getMovieName(), db)
        //Prepare for the next version
        db.close()

        // Re-open the database with version 3 and provide MIGRATION_1_2
        // and MIGRATION_2_3 as the migration process.
        testHelper.runMigrationsAndValidate(
            TEST_DB_NAME, 3,
            validateDroppedTables, MIGRATION_1_2, MIGRATION_2_3
        )

        // MigrationTestHelper automatically verifies the schema
        //changes, but not the data validity
        // Validate that the data was migrated properly.
        val dbMovie = getMigratedRoomDatabase().MovieDao().getMovie()
        assertEquals(dbMovie.getId, MOVIE.getId)
        assertEquals(dbMovie.getUserName, MOVIE.getMovieName)
        // The date was missing in version 2, so it should be null in
        //version 3
        assertEquals(dbMovie.geLast_Update, null)
    }
}*/
