package com.springfield.framework.util.testhelper;


import com.assertthat.selenium_shutterbug.core.Snapshot;
import com.springfield.framework.util.file.PathFinder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AbstractAssert;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.springfield.framework.data.Constants.DIFFERENCE_SCREENSHOTS_DIR_PATH;

/**
 * Screenshot Comparison:
 * Compare screenshot taken with the expected one and create new image with differences highlighted along with specified deviation rate
 * (for example 0.1 represents that if image differences are less than 10% the images are considered to be equal):
 */
@Slf4j
public class AssertSnapshot extends AbstractAssert<AssertSnapshot, Snapshot> {

    @Autowired
    private PathFinder pathFinder;

    public AssertSnapshot(Snapshot snapshot) {
        super(snapshot, AssertSnapshot.class);
    }

    public static AssertSnapshot assertThat(Snapshot snapshot) {
        return new AssertSnapshot(snapshot);
    }

    @SneakyThrows
    public AssertSnapshot matchesWithSnapshot(String expectedImageName) {
        isNotNull();
        BufferedImage expectedImage = ImageIO.read(new File(pathFinder.getFilePathForFile(expectedImageName.replaceAll("\\s","")).toString()));
        actual.equalsWithDiff(expectedImage, DIFFERENCE_SCREENSHOTS_DIR_PATH.concat(expectedImageName.replace(".png","")).concat("Difference"), 0.1);
        return this;
    }

}
