package top.wakem.abbrlink.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkRequest {
    @NonNull
    private String link;

    private Long expireDays;
}
