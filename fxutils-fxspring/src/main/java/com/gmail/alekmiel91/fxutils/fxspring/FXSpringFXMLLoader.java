package com.gmail.alekmiel91.fxutils.fxspring;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksander Mielczarek
 * @since 2015-03-01
 */
public class FXSpringFXMLLoader {
    private final FXMLLoader fxmlLoader;

    private FXSpringFXMLLoader(FXSpringFXMLLoaderBuilder builder) {
        this.fxmlLoader = builder.fxmlLoader;
        builder.fxSpringComponents.forEach(FXSpringComponent::init);
        this.fxmlLoader.setControllerFactory(builder.applicationContext::getBean);
    }

    public <T> T load() throws IOException {
        return fxmlLoader.load();
    }

    public <T> T load(InputStream inputStream) throws IOException {
        return fxmlLoader.load(inputStream);
    }

    public static class FXSpringFXMLLoaderBuilder {
        private final FXMLLoader fxmlLoader;
        private final ApplicationContext applicationContext;
        private final List<FXSpringComponent> fxSpringComponents = new ArrayList<>();

        public FXSpringFXMLLoaderBuilder(FXMLLoader fxmlLoader, ApplicationContext applicationContext) {
            this.fxmlLoader = fxmlLoader;
            this.applicationContext = applicationContext;
        }

        public FXSpringFXMLLoaderBuilder withFXSpringComponent(FXSpringComponent fxSpringComponent) {
            fxSpringComponents.add(fxSpringComponent);
            return this;
        }

        public FXSpringFXMLLoader build() {
            return new FXSpringFXMLLoader(this);
        }
    }
}
