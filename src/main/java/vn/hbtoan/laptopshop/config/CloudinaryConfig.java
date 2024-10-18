package vn.hbtoan.laptopshop.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig  {
    
    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "ddwjnjssj");
        config.put("api_key", "928543254767848");  
        config.put("api_secret", "w8JEy_kZ8uyV99aHKnUZPI7NSiQ");  
        return new Cloudinary(config);
    }
}
