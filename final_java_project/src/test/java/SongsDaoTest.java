import com.JukeBox.DaoImpl.SongsDaoImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class SongsDaoTest {
    SongsDaoImpl songsDao;

    @BeforeEach
    void setUp()
    {
        songsDao=new SongsDaoImpl();
    }

    @AfterEach
    void tearDown()
    {
        songsDao=new SongsDaoImpl();
    }

    @Test
    public void returnFalseWhenGivenCrendetialInvalid()
    {
        assertEquals(false,songsDao.Login_credential1("854474979",5475));
        assertEquals(false,songsDao.Login_credential1("67543789",5555));
       }

    @Test
    public void returnTrueWhenGivenCrendetialValid()
    {
        assertEquals(true,songsDao.Login_credential1("6367956203",54757));
        assertEquals(true,songsDao.Login_credential1("8544747979",4710));
    }
}
