package xxe_vulnerable_app;

import xxe_vulnerable_app.domain.bookDomain;
import xxe_vulnerable_app.repo.bookRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner init(bookRepo bookRepo) {
        return (args) -> {
            // save a few of items to the grocery list

            bookRepo.save(new bookDomain("The Enchanted Forest", "Fantasy", "15.99","Nora Seed finds herself in a mysterious library between life and death, where every book represents a different version of her life. Faced with infinite possibilities, she must decide which life is truly worth living. A heartwarming and thought-provoking exploration of regrets, second chances, and the pursuit of happiness."));
            bookRepo.save(new bookDomain("Quantum Paradoxes", "Science", "22.5","Le Cirque des Rêves arrives without warning and enchants all who enter. Behind its magical curtains, two young illusionists, Celia and Marco, are locked in a fierce competition, unaware that their fates are intertwined. In this spellbinding novel, love, dreams, and danger come alive under the moonlit skies of the circus."));
            bookRepo.save(new bookDomain("Culinary Wonders", "Cooking", "29.99","Santiago, a young shepherd, sets off on a journey to find a hidden treasure in the Egyptian desert. Along the way, he learns about the true nature of dreams, destiny, and personal legends. This timeless philosophical tale explores the importance of following one's heart and seeking meaning beyond material wealth."));
            bookRepo.save(new bookDomain("The Silent Night", "Horror", "9.99","In a world where memories are traded like currency, one woman discovers a forgotten past that could reshape the future. As she pieces together the fragments of a life erased, she unearths secrets that threaten the balance of power in her city—and her own mind."));
            bookRepo.save(new bookDomain("Historical Journeys", "History", "18.75","When an inventor stumbles upon an ancient shipwreck filled with mechanical creatures, he is drawn into a battle between rival factions vying for control of a vast underwater empire. To survive, he must unlock the secrets of the clockwork machines before the rising tide consumes them all."));
            bookRepo.save(new bookDomain("The Code Breaker", "Technology", "23.99","In the quiet town of Willow’s End, strange flowers bloom only under the moonlight, granting those who find them glimpses of alternate realities. But when the local florist uncovers a deadly connection between the flowers and her missing sister, she must decide whether to use their power or destroy it forever."));
            bookRepo.save(new bookDomain("Gardens of Tomorrow", "Gardening", "45.75","In a land of shifting sand and glass cities, a nomadic thief stumbles upon a shard that can control time itself. Pursued by a ruthless empire and a forgotten race of glass warriors, she must decide whether to wield its power or leave the world to its fate."));
            bookRepo.save(new bookDomain("Mindful Living", "Self-Help", "14.99","In a war-torn land where music is forbidden, a young bard discovers a forbidden melody capable of awakening ancient powers. As he journeys across hostile territories, he learns that the fate of both the living and the dead depends on the final notes of the song he was never meant to play."));
        };

    }

}
