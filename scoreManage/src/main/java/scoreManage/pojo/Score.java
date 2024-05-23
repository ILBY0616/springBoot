package scoreManage.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class Score {
    @NotBlank(message = "id不能为空")
    private String id;

    @NotBlank(message = "name不能为空")
    private String name;

    @Min(value = 0, message = "最低分为0")
    @Max(value = 100, message = "最高分为100")
    private Integer mathScore;

    @Min(value = 0, message = "最低分为0")
    @Max(value = 100, message = "最高分为100")
    private Integer englishScore;

    @Min(value = 0, message = "最低分为0")
    @Max(value = 100, message = "最高分为100")
    private Integer programScore;

    @Min(value = 0, message = "最低分为0")
    @Max(value = 100, message = "最高分为100")
    private Integer databaseScore;
}
