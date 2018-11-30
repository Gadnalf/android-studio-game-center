package fall2018.csc2017.GameCentre;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AccountManagerTest {

    AccountManager accountManager = new AccountManager();

    @Test
    public void TestAddAndLogin(){
        assertEquals(true, accountManager.addAccount("Johnny", "Test"));
        assertEquals("Johnny", accountManager.getName());
        assertEquals(true, accountManager.addAccount("Bobby", "Best"));
        assertEquals("Bobby", accountManager.getName());
        accountManager.login("Johnny", "Test");
        assertEquals("Johnny", accountManager.getName());
        accountManager.login("Bobby", "Best");
        assertEquals("Bobby", accountManager.getName());
        accountManager.logout();
        assertEquals("Guest", accountManager.getName());
    }
}
