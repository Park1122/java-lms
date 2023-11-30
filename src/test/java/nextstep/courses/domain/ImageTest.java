package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ImageTest {

    public static final String IMAGE_URL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";

    @Test
    @DisplayName("이미지 크기는 최대 크기 이하 여야 한다.")
    void imageSizeLessThanMaximumSize() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Image(IMAGE_URL, Image.MAXIMUM_WIDTH, Image.MAXIMUM_HEIGHT+1, ImageType.PNG);
            new Image(IMAGE_URL, Image.MAXIMUM_WIDTH+1, Image.MAXIMUM_HEIGHT, ImageType.PNG);
        });
    }

    @Test
    @DisplayName("이미지의 가로는 최소 픽셀 이상이어야한다.")
    void imageWidthSizeEqualsOrMoreThanMinimumPixel() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Image(IMAGE_URL,Image.MINIMUM_WIDTH-1, Image.MINIMUM_HEIGHT, ImageType.PNG);
        });
    }

    @Test
    @DisplayName("이미지의 세로는 최소 픽셀 이상이어야한다.")
    void imageHeightSizeEqualsOrMoreThanMinimumPixel() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Image(IMAGE_URL, Image.MINIMUM_WIDTH, Image.MAXIMUM_HEIGHT-1, ImageType.PNG);
        });
    }

    @Test
    @DisplayName("이미지의 가로와 세율의 비율은 정해진 비율이어야한다.")
    void imageRateBetweenWidthAndHeightShouldBe_FixedRate() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Image(IMAGE_URL ,Image.RATE_OF_WIDTH+1, Image.RATE_OF_HEIGHT, ImageType.PNG);
            new Image(IMAGE_URL ,Image.RATE_OF_WIDTH, Image.RATE_OF_HEIGHT+1, ImageType.PNG);
        });
    }
}
