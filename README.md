# School Projects

Computer science projects from my B.S. in Computer Science at North Carolina State University (2020–2024). Most are Java; two are in C.

| Project | What it is | Built with |
|---|---|---|
| [course-scheduler](course-scheduler) | Course registration system: catalog, student schedules, enrollment caps and waitlists, conflict detection, and a finite-state-machine course-name validator. Team project. | Java, Swing, JUnit |
| [product-backlog](product-backlog) | Agile product backlog manager. Tasks move through a state machine (backlog → owned → processing → verifying → done), with file import/export and a GUI. | Java, Swing, JUnit |
| [test-plan-tracker](test-plan-tracker) | System test plan tracker with custom sorted and swappable list data structures and a log. Team project. | Java, Swing, JUnit |
| [data-structures](data-structures) | Library of lists, stacks, queues, maps, search trees, priority queues, graphs and sorting algorithms, built week by week. | Java, JUnit |
| [cleaning-manager](cleaning-manager) | Room-cleaning log analyzer that reads room and cleaning-log files and reports cleaning events by room, coverage over time, and when vacuum bags need changing, using a swappable data-structure factory. | Java |
| [coffee-maker](coffee-maker) | Full-stack CoffeeMaker web app: REST API for recipes, ingredients and inventory, with a database layer and UI wireframes. | Java, Spring Boot, Hibernate, MySQL |
| [disk-defrag](disk-defrag) | Disk image defragmenter that reads an inode-based file system image and rewrites it with each file's blocks laid out contiguously. Team project. | C |
| [shellcode-exercises](shellcode-exercises) | Systems security exercises: hand-written x86 shellcode and a C test harness. | x86 assembly, C |

## Credits

These were class projects, so not every line here is mine:

- **Starter code.** Several projects began from instructor-provided starter code and interfaces. Instructor-written files keep their original `@author` tags.
- **Textbook code.** Parts of [data-structures](data-structures) come from *Data Structures and Algorithms in Java* (6th ed.) by Goodrich, Tamassia and Goldwasser, used under the GPL. Those classes are cited in their class-level Javadoc; see [data-structures/LICENSE](data-structures/LICENSE).
- **Teammates.** Team projects include code by my classmates, credited in each file's `@author` tags.

My senior design project, a full-stack data management app built with a sponsor, isn't included because the sponsor owns the code.

## Running the tests

Every project has a test suite, and all of them pass:

| Project | Tests | How to run |
|---|---|---|
| course-scheduler | 213 | JUnit 4/5; compile `src` and `test` with the jar in `lib/` and run from the project folder |
| product-backlog | 35 | JUnit 5, run from the project folder |
| test-plan-tracker | 52 | JUnit 5, run from the project folder |
| data-structures | 215 | JUnit 4, run from the project folder |
| cleaning-manager | 6 (+1 large-input performance test) | JUnit 4 with the jars in `lib/`; the performance test skips itself unless the large generated inputs are in `input/` |
| coffee-maker | 44 | `./mvnw test` (Java 11+). Tests use an in-memory H2 database; the app itself uses MySQL (copy `application.yml.template` to `application.yml`) |
| disk-defrag | 4 sample disks | `./run_tests.sh` on Linux or WSL: defragments each sample disk and verifies every file is intact, contiguous, and the swap region is preserved |
| shellcode-exercises | — | Linux x86-64 only: `gcc -z execstack -fno-stack-protector -o shelltest shelltest.c` |
