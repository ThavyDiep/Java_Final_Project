import java.io.*;
import java.util.*;

public class WordScrambleGame {
    // File paths for different word categories
    private static final String FRUITS_FILE = "C:\\Users\\User\\IdeaProjects\\My Java program\\src\\fruits.txt";
    private static final String COUNTRIES_FILE = "C:\\Users\\User\\IdeaProjects\\My Java program\\src\\countries.txt";
    private static final String JOBS_FILE = "C:\\Users\\User\\IdeaProjects\\My Java program\\src\\jobs.txt";
    private static final String VEGETABLES_FILE = "C:\\Users\\User\\IdeaProjects\\My Java program\\src\\vegetables.txt";
    private static final String ANIMALS_FILE = "C:\\Users\\User\\IdeaProjects\\My Java program\\src\\animals.txt";

//    Reads words from a file and stores them in a List.
        private static List<String> readWordsFromFile(String filePath) {
            List<String> words = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    words.add(line.trim());
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + filePath);
            }
            return words;
        }
//    Shuffles the characters of a word to create a scrambled version.
        private static String shuffleWord(String word) {
            List<Character> letters = new ArrayList<>();
            for (char c : word.toCharArray()) {
                letters.add(c);
            }
            Collections.shuffle(letters);

            StringBuilder scrambled = new StringBuilder();
            for (char c : letters) {
                scrambled.append(c);
            }
            return scrambled.toString();
        }

    // Display category selection menu
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();
            boolean playAgain = true;
            int unscrambledCount = 0;
            List<String> selectedCategory = null;

            while (true) {
                System.out.println("\nWelcome to the Word Scramble Game!");
                System.out.println("Choose a category:");
                System.out.println("1. Fruits 🍎");
                System.out.println("2. Countries 🌍");
                System.out.println("3. Jobs 💼");
                System.out.println("4. Vegetables 🥕");
                System.out.println("5. Animal 🐶");

                int choice;
                while (true) {
                    System.out.print("Enter your choice (1/2/3/4/5): ");
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        if (choice >= 1 && choice <= 5) {
                            break;
                        }
                    } else {
                        scanner.next(); // Consume invalid input
                    }
                    System.out.println("Oops! That's not a valid choice. Please select a number between 1 and 5.");
                }

                // Load words from the selected file
                switch (choice) {
                    case 1:
                        selectedCategory = readWordsFromFile(FRUITS_FILE);
                        System.out.println("You selected: Fruits 🍎");
                        break;
                    case 2:
                        selectedCategory = readWordsFromFile(COUNTRIES_FILE);
                        System.out.println("You selected: Countries 🌍");
                        break;
                    case 3:
                        selectedCategory = readWordsFromFile(JOBS_FILE);
                        System.out.println("You selected: Jobs 💼");
                        break;
                    case 4:
                        selectedCategory = readWordsFromFile(VEGETABLES_FILE);
                        System.out.println("You selected: Vegetables 🥕");
                        break;
                    case 5:
                        selectedCategory = readWordsFromFile(ANIMALS_FILE);
                        System.out.println("You selected: Animals 🐶");
                        break;
                }

                if (selectedCategory == null || selectedCategory.isEmpty()) {
                    System.out.println("No words found in the file! Exiting game.");
                    return;
                }
                break; // Exit the category selection loop and start the game
            }

            //    Main game loop
            while (playAgain) {
                String originalWord = selectedCategory.get(random.nextInt(selectedCategory.size()));
                String scrambledWord = shuffleWord(originalWord);

                System.out.println("\nUnscramble this word: " + scrambledWord);
                int attempts = 3;

                // Guessing loop
                while (attempts > 0) {
                    System.out.print("Your guess (or type 'g' to give up this round): ");
                    String userGuess = scanner.nextLine().toLowerCase();

                    if (userGuess.equals("g")) {
                        System.out.println("The correct word was: " + originalWord);
                        break;
                    } else if (userGuess.equals(originalWord)) {
                        System.out.println("🎉 Correct! You guessed the word!");
                        unscrambledCount++;
                        break;
                    } else {
                        attempts--;
                        if (attempts > 0) {
                            System.out.println("❌ Wrong! Try again. Attempts left: " + attempts);
                        } else {
                            System.out.println("❌ Game over! The correct word was: " + originalWord);
                        }
                    }
                }
//                Prompt to play again
                String playAgainInput;
                while (true) {
                    System.out.print("\nDo you want to play again? (y = yes, n = no): ");
                    playAgainInput = scanner.nextLine().toLowerCase();
                    if (playAgainInput.equals("y") || playAgainInput.equals("n")) {
                        break;
                    }
                    System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
                }

                if (playAgainInput.equals("n")) {
                    playAgain = false;
                    System.out.println("\nThanks for playing!");
                    System.out.println("You have unscrambled " + unscrambledCount + " words. 🎉");
                }
            }

            scanner.close(); // close the scanner
        }
    }
