package au.com.rma.reactiveservertest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieEvent {

    private Movie movie;
    private LocalDateTime started;
    private String username;

}
