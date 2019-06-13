package tags;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class GetCurrentDay extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter writer = getJspContext().getOut();
        LocalDate date = LocalDate.now(ZoneId.systemDefault());
        writer.print(date.getDayOfMonth()+"-"+date.getMonth()+"-"+date.getYear());
    }
}
