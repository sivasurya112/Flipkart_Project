package genericUtility;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class MultipleWindows {
	
	public void switchTab(WebDriver d) {
		Set<String> allTabId = d.getWindowHandles();
		String tabId = d.getWindowHandle();
		for(String id:allTabId) {
			if(!tabId.equals(id))
				d.switchTo().window(id);
		}
	}

}
