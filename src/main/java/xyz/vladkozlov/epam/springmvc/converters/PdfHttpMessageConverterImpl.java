package xyz.vladkozlov.epam.springmvc.converters;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import xyz.vladkozlov.epam.springmvc.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfHttpMessageConverterImpl implements HttpMessageConverter {

    @Override
    public boolean canWrite(@Nullable Class clazz, @Nullable MediaType mediaType) {
        return (clazz == User.class || clazz == ArrayList.class) && ( mediaType == null || mediaType.toString().equals("application/pdf"));
    }

    @Override
    public boolean canRead(@Nullable Class clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        List<MediaType> a = new ArrayList<>();
        a.add(MediaType.APPLICATION_PDF);
        return a;
    }

    @Override
    public Object read(@Nullable Class clazz, @Nullable HttpInputMessage inputMessage) throws HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getHeaders().setContentType(contentType);

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, outputMessage.getBody());
            document.open();


            document.add(new Paragraph("This was created from PdfHttpMessageConverterImpl"));

            if (o.getClass() == User.class) {
                generateUserTable(document, (User) o);
            } else if (o.getClass() == ArrayList.class) {
                List users = (ArrayList) o;
                for (Object shouldBeUserLol : users) {
                    if (shouldBeUserLol.getClass() == User.class) {
                        generateUserTable(document, (User) shouldBeUserLol);
                    }
                }
            }


        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();
    }

    private void generateUserTable(Document document, User shouldBeUserLol) throws DocumentException {
        User user = shouldBeUserLol;

        Table table = new Table(2);
        table.addCell("Name");
        table.addCell(user.getFullName());
        table.addCell("Username");
        table.addCell(user.getUsername());
        table.addCell("Roles:");
        table.addCell(user.getRoles());
        table.addCell("Cell operator");
        table.addCell(user.getUserAccount().getCellOperator());
        table.addCell("User Phone number:");
        table.addCell(user.getUserAccount().getPhoneNumber());
        table.addCell("Phone dictionary");
        table.addCell(user.getPhoneNumbers().stream()
                .map(phoneNumber -> phoneNumber.getNumber() + "(" + phoneNumber.getCompany() + ")")
                .reduce((a, b) -> a + "\n" + b)
                .orElse(""));
        document.add(table);
    }
}
