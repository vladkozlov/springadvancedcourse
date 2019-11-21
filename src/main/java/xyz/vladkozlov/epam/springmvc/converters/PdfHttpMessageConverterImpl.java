package xyz.vladkozlov.epam.springmvc.converters;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import xyz.vladkozlov.epam.springmvc.models.User;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class PdfHttpMessageConverterImpl implements PdfHttpMessageConverter {

    @Override
    public boolean canWrite(@Nullable Class clazz, @Nullable MediaType mediaType) {
        return clazz == User.class;
    }

    @Override
    public boolean canRead(@Nullable Class clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(MediaType.APPLICATION_PDF);
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

            User user = (User) o;

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
                    .reduce((a,b)-> a + "\n" + b)
                    .orElse(""));



            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();
    }
}
