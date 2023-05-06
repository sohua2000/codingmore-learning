package top.codingmore.codingmorehelloword.demo2;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class StuThree {
    @NonNull
    private String name;

    private Integer age;
}
