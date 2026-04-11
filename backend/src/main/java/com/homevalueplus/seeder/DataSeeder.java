package com.homevalueplus.seeder;

import com.homevalueplus.entity.Admin;
import com.homevalueplus.entity.Idea;
import com.homevalueplus.enums.Category;
import com.homevalueplus.enums.PropertyType;
import com.homevalueplus.repository.AdminRepository;
import com.homevalueplus.repository.IdeaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final IdeaRepository ideaRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(IdeaRepository ideaRepository, AdminRepository adminRepository,
                     PasswordEncoder passwordEncoder) {
        this.ideaRepository = ideaRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedAdmin();
        seedIdeas();
    }

    private void seedAdmin() {
        if (!adminRepository.existsByUsername("admin")) {
            Admin admin = Admin.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .email("admin@homevalueplus.in")
                    .fullName("System Administrator")
                    .build();
            adminRepository.save(admin);
            System.out.println("Default admin created (username: admin, password: admin123)");
        }
    }

    private void seedIdeas() {
        if (ideaRepository.count() > 0) {
            System.out.println("Ideas already seeded, skipping...");
            return;
        }

        List<Idea> ideas = List.of(
            // INTERIOR ideas
            Idea.builder()
                .title("Modular Kitchen Upgrade")
                .description("Install a modern modular kitchen with granite countertops, soft-close cabinets, chimney, and built-in appliances. Includes waterproof laminate finish and LED under-cabinet lighting.")
                .category(Category.INTERIOR)
                .estimatedCostMin(150000.0)
                .estimatedCostMax(500000.0)
                .expectedValueIncrease(15.0)
                .propertyType(PropertyType.APARTMENT)
                .build(),

            Idea.builder()
                .title("Premium Bathroom Renovation")
                .description("Complete bathroom makeover with anti-skid vitrified tiles, wall-mounted WC, rain shower, vanity unit, and concealed plumbing. Adds luxury appeal to the property.")
                .category(Category.INTERIOR)
                .estimatedCostMin(80000.0)
                .estimatedCostMax(250000.0)
                .expectedValueIncrease(10.0)
                .propertyType(PropertyType.APARTMENT)
                .build(),

            Idea.builder()
                .title("False Ceiling with Cove Lighting")
                .description("Install gypsum false ceiling with recessed LED cove lighting in living room and bedrooms. Creates a premium feel and hides wiring and AC ducting.")
                .category(Category.INTERIOR)
                .estimatedCostMin(40000.0)
                .estimatedCostMax(120000.0)
                .expectedValueIncrease(7.0)
                .propertyType(PropertyType.APARTMENT)
                .build(),

            // EXTERIOR ideas
            Idea.builder()
                .title("Exterior Wall Cladding")
                .description("Apply stone or tile cladding on exterior walls for weather protection and aesthetic enhancement. Includes HPL cladding or natural stone options with 10+ year durability.")
                .category(Category.EXTERIOR)
                .estimatedCostMin(200000.0)
                .estimatedCostMax(600000.0)
                .expectedValueIncrease(12.0)
                .propertyType(PropertyType.INDEPENDENT_HOUSE)
                .build(),

            Idea.builder()
                .title("Landscaped Garden with Pergola")
                .description("Design a landscaped front/back garden with drought-resistant plants, decorative paving, a wooden pergola seating area, and solar garden lights.")
                .category(Category.EXTERIOR)
                .estimatedCostMin(100000.0)
                .estimatedCostMax(300000.0)
                .expectedValueIncrease(8.0)
                .propertyType(PropertyType.INDEPENDENT_HOUSE)
                .build(),

            Idea.builder()
                .title("Boundary Wall and Gate Renovation")
                .description("Rebuild compound wall with designer MS gate, intercom system, and CCTV provision. Modern elevation designs with textured paint or stone finish.")
                .category(Category.EXTERIOR)
                .estimatedCostMin(80000.0)
                .estimatedCostMax(250000.0)
                .expectedValueIncrease(6.0)
                .propertyType(PropertyType.INDEPENDENT_HOUSE)
                .build(),

            // ENERGY ideas
            Idea.builder()
                .title("Rooftop Solar Panel System")
                .description("Install a 3-5 kW rooftop solar system with net metering. Reduces electricity bill by 70-80% and qualifies for government subsidies under PM Surya Ghar scheme.")
                .category(Category.ENERGY)
                .estimatedCostMin(180000.0)
                .estimatedCostMax(350000.0)
                .expectedValueIncrease(10.0)
                .propertyType(PropertyType.INDEPENDENT_HOUSE)
                .build(),

            Idea.builder()
                .title("Energy-Efficient Windows")
                .description("Replace standard windows with double-glazed UPVC windows for better insulation, noise reduction, and AC efficiency. Reduces energy consumption by 20-30%.")
                .category(Category.ENERGY)
                .estimatedCostMin(60000.0)
                .estimatedCostMax(200000.0)
                .expectedValueIncrease(6.0)
                .propertyType(PropertyType.APARTMENT)
                .build(),

            // TECHNOLOGY ideas
            Idea.builder()
                .title("Smart Home Automation")
                .description("Install smart switches, voice-controlled lights, smart locks, video doorbell, and automated curtains. Compatible with Alexa/Google Home. Full home automation for 2-3 BHK.")
                .category(Category.TECHNOLOGY)
                .estimatedCostMin(50000.0)
                .estimatedCostMax(200000.0)
                .expectedValueIncrease(8.0)
                .propertyType(PropertyType.APARTMENT)
                .build(),

            Idea.builder()
                .title("EV Charging Station")
                .description("Install a Level 2 electric vehicle charging point in parking area. Future-proof investment as EV adoption grows in India. Wall-mounted or pedestal options available.")
                .category(Category.TECHNOLOGY)
                .estimatedCostMin(25000.0)
                .estimatedCostMax(80000.0)
                .expectedValueIncrease(4.0)
                .propertyType(PropertyType.INDEPENDENT_HOUSE)
                .build(),

            // INVESTMENT ideas
            Idea.builder()
                .title("Additional Floor Construction")
                .description("Build an additional floor on independent house with RCC construction, modern design, and separate entry provision. Can be used for rental income or future family expansion.")
                .category(Category.INVESTMENT)
                .estimatedCostMin(800000.0)
                .estimatedCostMax(2500000.0)
                .expectedValueIncrease(30.0)
                .propertyType(PropertyType.INDEPENDENT_HOUSE)
                .build(),

            Idea.builder()
                .title("Convert Terrace to Livable Space")
                .description("Transform open terrace into a covered multipurpose room with weather-proof construction, proper waterproofing, drainage, and separate washroom. Ideal for home office or guest room.")
                .category(Category.INVESTMENT)
                .estimatedCostMin(300000.0)
                .estimatedCostMax(800000.0)
                .expectedValueIncrease(18.0)
                .propertyType(PropertyType.INDEPENDENT_HOUSE)
                .build(),

            // STRUCTURAL ideas
            Idea.builder()
                .title("Complete Waterproofing Treatment")
                .description("Full waterproofing of terrace, bathrooms, and external walls using Dr. Fixit or Sika products. Includes crack repair, anti-dampness coating, and 10-year warranty materials.")
                .category(Category.STRUCTURAL)
                .estimatedCostMin(30000.0)
                .estimatedCostMax(100000.0)
                .expectedValueIncrease(5.0)
                .propertyType(PropertyType.APARTMENT)
                .build(),

            Idea.builder()
                .title("Structural Strengthening and Retrofitting")
                .description("Strengthen existing RCC columns, beams, and foundation using carbon fiber wrapping or jacketing. Essential for older properties (15+ years) to ensure earthquake safety compliance.")
                .category(Category.STRUCTURAL)
                .estimatedCostMin(200000.0)
                .estimatedCostMax(700000.0)
                .expectedValueIncrease(12.0)
                .propertyType(PropertyType.INDEPENDENT_HOUSE)
                .build(),

            Idea.builder()
                .title("Fresh Interior and Exterior Painting")
                .description("Premium interior and exterior painting with Asian Paints Royale or equivalent. Includes wall preparation, primer, putty, and 2 coats of weather-resistant paint.")
                .category(Category.STRUCTURAL)
                .estimatedCostMin(40000.0)
                .estimatedCostMax(150000.0)
                .expectedValueIncrease(5.0)
                .propertyType(PropertyType.APARTMENT)
                .build()
        );

        ideaRepository.saveAll(ideas);
        System.out.println("Seeded " + ideas.size() + " improvement ideas");
    }
}
