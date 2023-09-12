package com.pgoellner.karel.parse;

import java.util.stream.Stream;

public class MadLabyrinth {
    public static final Stream<String> PARSE_INPUT = Stream.of(
            "Dimension: (25, 25)",
            "Wall: (1, 2) south",
            "Wall: (1, 11) south",
            "Wall: (1, 19) south",
            "Wall: (2, 2) west",
            "Wall: (2, 2) south",
            "Wall: (2, 3) west",
            "Wall: (2, 4) west",
            "Wall: (2, 5) south",
            "Wall: (2, 6) west",
            "Wall: (2, 6) south",
            "Wall: (2, 7) west",
            "Wall: (2, 8) west",
            "Wall: (2, 10) south",
            "Wall: (2, 11) south",
            "Wall: (2, 12) south",
            "Wall: (2, 13) west",
            "Wall: (2, 13) south",
            "Wall: (2, 15) west",
            "Wall: (2, 16) west",
            "Wall: (2, 16) south",
            "Wall: (2, 17) west",
            "Wall: (2, 18) south",
            "Wall: (2, 19) south",
            "Wall: (2, 20) west",
            "Wall: (2, 22) west",
            "Wall: (2, 24) west",
            "Wall: (3, 2) south",
            "Wall: (3, 3) west",
            "Wall: (3, 4) west",
            "Wall: (3, 6) south",
            "Wall: (3, 7) west",
            "Wall: (3, 7) south",
            "Wall: (3, 8) west",
            "Wall: (3, 9) south",
            "Wall: (3, 10) west",
            "Wall: (3, 12) south",
            "Wall: (3, 13) south",
            "Wall: (3, 16) south",
            "Wall: (3, 18) south",
            "Wall: (3, 19) south",
            "Wall: (3, 20) south",
            "Wall: (3, 21) south",
            "Wall: (3, 22) west",
            "Wall: (3, 22) south",
            "Wall: (3, 23) south",
            "Wall: (3, 24) south",
            "Wall: (3, 25) south",
            "Wall: (4, 2) west",
            "Wall: (4, 2) south",
            "Wall: (4, 3) west",
            "Wall: (4, 4) west",
            "Wall: (4, 5) west",
            "Wall: (4, 7) south",
            "Wall: (4, 8) west",
            "Wall: (4, 9) south",
            "Wall: (4, 10) west",
            "Wall: (4, 10) south",
            "Wall: (4, 11) south",
            "Wall: (4, 12) west",
            "Wall: (4, 12) south",
            "Wall: (4, 13) south",
            "Wall: (4, 14) west",
            "Wall: (4, 15) west",
            "Wall: (4, 18) south",
            "Wall: (4, 19) south",
            "Wall: (4, 20) south",
            "Wall: (4, 21) south",
            "Wall: (4, 22) south",
            "Wall: (4, 23) south",
            "Wall: (4, 24) south",
            "Wall: (4, 25) south",
            "Wall: (5, 2) south",
            "Wall: (5, 3) west",
            "Wall: (5, 4) west",
            "Wall: (5, 5) west",
            "Wall: (5, 6) west",
            "Wall: (5, 7) south",
            "Wall: (5, 8) south",
            "Wall: (5, 9) west",
            "Wall: (5, 9) south",
            "Wall: (5, 10) south",
            "Wall: (5, 11) south",
            "Wall: (5, 12) south",
            "Wall: (5, 13) south",
            "Wall: (5, 16) west",
            "Wall: (5, 18) west",
            "Wall: (5, 18) south",
            "Wall: (5, 20) south",
            "Wall: (5, 21) south",
            "Wall: (5, 22) south",
            "Wall: (5, 23) south",
            "Wall: (5, 24) south",
            "Wall: (5, 25) south",
            "Wall: (6, 2) west",
            "Wall: (6, 2) south",
            "Wall: (6, 3) west",
            "Wall: (6, 4) west",
            "Wall: (6, 5) west",
            "Wall: (6, 6) south",
            "Wall: (6, 7) south",
            "Wall: (6, 8) west",
            "Wall: (6, 10) south",
            "Wall: (6, 11) west",
            "Wall: (6, 13) west",
            "Wall: (6, 14) west",
            "Wall: (6, 15) west",
            "Wall: (6, 15) south",
            "Wall: (6, 17) west",
            "Wall: (6, 18) west",
            "Wall: (6, 19) west",
            "Wall: (6, 21) south",
            "Wall: (6, 23) west",
            "Wall: (6, 24) south",
            "Wall: (6, 25) south",
            "Wall: (7, 2) south",
            "Wall: (7, 3) west",
            "Wall: (7, 3) south",
            "Wall: (7, 4) west",
            "Wall: (7, 5) south",
            "Wall: (7, 6) south",
            "Wall: (7, 7) south",
            "Wall: (7, 8) west",
            "Wall: (7, 9) south",
            "Wall: (7, 10) south",
            "Wall: (7, 12) west",
            "Wall: (7, 12) south",
            "Wall: (7, 15) west",
            "Wall: (7, 16) south",
            "Wall: (7, 17) west",
            "Wall: (7, 18) west",
            "Wall: (7, 19) west",
            "Wall: (7, 20) south",
            "Wall: (7, 21) west",
            "Wall: (7, 22) south",
            "Wall: (7, 23) west",
            "Wall: (7, 23) south",
            "Wall: (7, 24) south",
            "Wall: (7, 25) south",
            "Wall: (8, 2) south",
            "Wall: (8, 3) south",
            "Wall: (8, 4) south",
            "Wall: (8, 5) south",
            "Wall: (8, 6) south",
            "Wall: (8, 7) south",
            "Wall: (8, 8) south",
            "Wall: (8, 9) south",
            "Wall: (8, 10) south",
            "Wall: (8, 11) south",
            "Wall: (8, 12) south",
            "Wall: (8, 13) west",
            "Wall: (8, 13) south",
            "Wall: (8, 14) west",
            "Wall: (8, 16) south",
            "Wall: (8, 17) west",
            "Wall: (8, 17) south",
            "Wall: (8, 19) west",
            "Wall: (8, 20) west",
            "Wall: (8, 21) south",
            "Wall: (8, 22) south",
            "Wall: (8, 23) south",
            "Wall: (8, 25) west",
            "Wall: (9, 2) south",
            "Wall: (9, 3) south",
            "Wall: (9, 4) south",
            "Wall: (9, 5) south",
            "Wall: (9, 6) south",
            "Wall: (9, 7) west",
            "Wall: (9, 7) south",
            "Wall: (9, 9) south",
            "Wall: (9, 10) south",
            "Wall: (9, 11) south",
            "Wall: (9, 12) south",
            "Wall: (9, 13) south",
            "Wall: (9, 14) west",
            "Wall: (9, 14) south",
            "Wall: (9, 16) south",
            "Wall: (9, 17) west",
            "Wall: (9, 18) west",
            "Wall: (9, 19) west",
            "Wall: (9, 20) south",
            "Wall: (9, 21) west",
            "Wall: (9, 23) south",
            "Wall: (9, 24) south",
            "Wall: (9, 25) west",
            "Wall: (10, 2) south",
            "Wall: (10, 3) south",
            "Wall: (10, 4) south",
            "Wall: (10, 5) south",
            "Wall: (10, 6) south",
            "Wall: (10, 8) west",
            "Wall: (10, 8) south",
            "Wall: (10, 10) west",
            "Wall: (10, 12) west",
            "Wall: (10, 14) west",
            "Wall: (10, 15) south",
            "Wall: (10, 16) south",
            "Wall: (10, 17) west",
            "Wall: (10, 17) south",
            "Wall: (10, 18) west",
            "Wall: (10, 19) south",
            "Wall: (10, 20) west",
            "Wall: (10, 21) west",
            "Wall: (10, 22) south",
            "Wall: (10, 23) south",
            "Wall: (10, 24) west",
            "Wall: (10, 25) south",
            "Wall: (11, 2) south",
            "Wall: (11, 4) south",
            "Wall: (11, 5) south",
            "Wall: (11, 6) west",
            "Wall: (11, 7) west",
            "Wall: (11, 11) south",
            "Wall: (11, 12) south",
            "Wall: (11, 15) west",
            "Wall: (11, 15) south",
            "Wall: (11, 16) west",
            "Wall: (11, 18) west",
            "Wall: (11, 19) west",
            "Wall: (11, 20) west",
            "Wall: (11, 22) west",
            "Wall: (11, 22) south",
            "Wall: (11, 24) west",
            "Wall: (11, 25) west",
            "Wall: (12, 2) west",
            "Wall: (12, 2) south",
            "Wall: (12, 5) west",
            "Wall: (12, 6) west",
            "Wall: (12, 7) west",
            "Wall: (12, 8) south",
            "Wall: (12, 10) west",
            "Wall: (12, 10) south",
            "Wall: (12, 12) west",
            "Wall: (12, 13) south",
            "Wall: (12, 15) west",
            "Wall: (12, 15) south",
            "Wall: (12, 17) south",
            "Wall: (12, 18) west",
            "Wall: (12, 18) south",
            "Wall: (12, 19) south",
            "Wall: (12, 20) west",
            "Wall: (12, 20) south",
            "Wall: (12, 21) west",
            "Wall: (12, 22) west",
            "Wall: (12, 23) west",
            "Wall: (12, 24) west",
            "Wall: (12, 25) south",
            "Wall: (13, 2) west",
            "Wall: (13, 3) west",
            "Wall: (13, 4) west",
            "Wall: (13, 5) west",
            "Wall: (13, 6) west",
            "Wall: (13, 7) west",
            "Wall: (13, 9) west",
            "Wall: (13, 11) west",
            "Wall: (13, 11) south",
            "Wall: (13, 12) south",
            "Wall: (13, 13) west",
            "Wall: (13, 15) west",
            "Wall: (13, 16) west",
            "Wall: (13, 18) south",
            "Wall: (13, 20) south",
            "Wall: (13, 21) west",
            "Wall: (13, 22) west",
            "Wall: (13, 23) west",
            "Wall: (13, 24) south",
            "Wall: (14, 1) west",
            "Wall: (14, 2) west",
            "Wall: (14, 3) west",
            "Wall: (14, 4) west",
            "Wall: (14, 5) west",
            "Wall: (14, 6) west",
            "Wall: (14, 7) west",
            "Wall: (14, 8) south",
            "Wall: (14, 9) west",
            "Wall: (14, 10) south",
            "Wall: (14, 11) west",
            "Wall: (14, 13) west",
            "Wall: (14, 13) south",
            "Wall: (14, 15) west",
            "Wall: (14, 15) south",
            "Wall: (14, 16) west",
            "Wall: (14, 18) west",
            "Wall: (14, 18) south",
            "Wall: (14, 19) west",
            "Wall: (14, 20) west",
            "Wall: (14, 21) west",
            "Wall: (14, 21) south",
            "Wall: (14, 22) west",
            "Wall: (14, 23) south",
            "Wall: (14, 24) south",
            "Wall: (14, 25) south",
            "Wall: (15, 2) west",
            "Wall: (15, 2) south",
            "Wall: (15, 3) west",
            "Wall: (15, 5) west",
            "Wall: (15, 6) west",
            "Wall: (15, 7) west",
            "Wall: (15, 10) west",
            "Wall: (15, 11) south",
            "Wall: (15, 12) west",
            "Wall: (15, 12) south",
            "Wall: (15, 15) west",
            "Wall: (15, 16) west",
            "Wall: (15, 17) west",
            "Wall: (15, 19) west",
            "Wall: (15, 19) south",
            "Wall: (15, 20) west",
            "Wall: (15, 21) west",
            "Wall: (15, 23) south",
            "Wall: (15, 24) south",
            "Wall: (15, 25) west",
            "Wall: (16, 2) west",
            "Wall: (16, 3) west",
            "Wall: (16, 3) south",
            "Wall: (16, 4) west",
            "Wall: (16, 4) south",
            "Wall: (16, 5) west",
            "Wall: (16, 6) west",
            "Wall: (16, 7) west",
            "Wall: (16, 7) south",
            "Wall: (16, 8) south",
            "Wall: (16, 15) west",
            "Wall: (16, 15) south",
            "Wall: (16, 16) west",
            "Wall: (16, 17) west",
            "Wall: (16, 19) south",
            "Wall: (16, 20) west",
            "Wall: (16, 20) south",
            "Wall: (16, 21) west",
            "Wall: (16, 22) west",
            "Wall: (16, 24) west",
            "Wall: (16, 25) west",
            "Wall: (17, 2) south",
            "Wall: (17, 3) south",
            "Wall: (17, 5) south",
            "Wall: (17, 6) west",
            "Wall: (17, 8) west",
            "Wall: (17, 9) south",
            "Wall: (17, 10) west",
            "Wall: (17, 10) south",
            "Wall: (17, 11) south",
            "Wall: (17, 12) west",
            "Wall: (17, 12) south",
            "Wall: (17, 13) south",
            "Wall: (17, 14) west",
            "Wall: (17, 14) south",
            "Wall: (17, 16) south",
            "Wall: (17, 17) west",
            "Wall: (17, 17) south",
            "Wall: (17, 18) west",
            "Wall: (17, 19) south",
            "Wall: (17, 20) south",
            "Wall: (17, 21) west",
            "Wall: (17, 21) south",
            "Wall: (17, 22) west",
            "Wall: (17, 23) west",
            "Wall: (17, 24) west",
            "Wall: (17, 25) south",
            "Wall: (18, 2) south",
            "Wall: (18, 3) south",
            "Wall: (18, 4) west",
            "Wall: (18, 5) west",
            "Wall: (18, 6) west",
            "Wall: (18, 7) west",
            "Wall: (18, 9) south",
            "Wall: (18, 10) south",
            "Wall: (18, 11) south",
            "Wall: (18, 12) south",
            "Wall: (18, 13) south",
            "Wall: (18, 14) west",
            "Wall: (18, 15) west",
            "Wall: (18, 16) south",
            "Wall: (18, 17) west",
            "Wall: (18, 19) south",
            "Wall: (18, 21) south",
            "Wall: (18, 22) west",
            "Wall: (18, 23) west",
            "Wall: (18, 24) west",
            "Wall: (19, 2) south",
            "Wall: (19, 6) west",
            "Wall: (19, 6) south",
            "Wall: (19, 7) south",
            "Wall: (19, 9) south",
            "Wall: (19, 10) south",
            "Wall: (19, 11) south",
            "Wall: (19, 12) south",
            "Wall: (19, 13) south",
            "Wall: (19, 14) west",
            "Wall: (19, 14) south",
            "Wall: (19, 15) south",
            "Wall: (19, 16) west",
            "Wall: (19, 17) west",
            "Wall: (19, 18) south",
            "Wall: (19, 19) west",
            "Wall: (19, 19) south",
            "Wall: (19, 20) west",
            "Wall: (19, 20) south",
            "Wall: (19, 21) west",
            "Wall: (19, 21) south",
            "Wall: (19, 22) south",
            "Wall: (19, 23) west",
            "Wall: (19, 23) south",
            "Wall: (19, 24) west",
            "Wall: (19, 24) south",
            "Wall: (19, 25) west",
            "Wall: (19, 25) south",
            "Wall: (20, 2) west",
            "Wall: (20, 2) south",
            "Wall: (20, 3) west",
            "Wall: (20, 4) west",
            "Wall: (20, 5) west",
            "Wall: (20, 5) south",
            "Wall: (20, 6) south",
            "Wall: (20, 7) west",
            "Wall: (20, 9) south",
            "Wall: (20, 10) south",
            "Wall: (20, 11) south",
            "Wall: (20, 12) south",
            "Wall: (20, 13) south",
            "Wall: (20, 14) south",
            "Wall: (20, 15) west",
            "Wall: (20, 16) west",
            "Wall: (20, 18) south",
            "Wall: (20, 19) west",
            "Wall: (20, 19) south",
            "Wall: (20, 25) west",
            "Wall: (21, 1) west",
            "Wall: (21, 3) south",
            "Wall: (21, 4) south",
            "Wall: (21, 5) south",
            "Wall: (21, 8) west",
            "Wall: (21, 10) south",
            "Wall: (21, 11) south",
            "Wall: (21, 12) south",
            "Wall: (21, 13) south",
            "Wall: (21, 14) west",
            "Wall: (21, 15) south",
            "Wall: (21, 16) west",
            "Wall: (21, 16) south",
            "Wall: (21, 17) west",
            "Wall: (21, 19) west",
            "Wall: (21, 19) south",
            "Wall: (21, 25) west",
            "Wall: (22, 2) south",
            "Wall: (22, 3) south",
            "Wall: (22, 4) south",
            "Wall: (22, 5) west",
            "Wall: (22, 6) west",
            "Wall: (22, 8) west",
            "Wall: (22, 8) south",
            "Wall: (22, 10) south",
            "Wall: (22, 11) west",
            "Wall: (22, 13) west",
            "Wall: (22, 14) west",
            "Wall: (22, 15) west",
            "Wall: (22, 15) south",
            "Wall: (22, 16) south",
            "Wall: (22, 17) west",
            "Wall: (22, 17) south",
            "Wall: (22, 18) south",
            "Wall: (22, 19) west",
            "Wall: (22, 19) south",
            "Wall: (22, 22) west",
            "Wall: (22, 25) west",
            "Wall: (23, 2) south",
            "Wall: (23, 4) west",
            "Wall: (23, 5) west",
            "Wall: (23, 5) south",
            "Wall: (23, 6) west",
            "Wall: (23, 7) west",
            "Wall: (23, 7) south",
            "Wall: (23, 8) west",
            "Wall: (23, 9) west",
            "Wall: (23, 12) west",
            "Wall: (23, 12) south",
            "Wall: (23, 13) west",
            "Wall: (23, 16) south",
            "Wall: (23, 17) south",
            "Wall: (23, 18) south",
            "Wall: (23, 19) west",
            "Wall: (23, 19) south",
            "Wall: (23, 22) west",
            "Wall: (23, 25) west",
            "Wall: (24, 2) south",
            "Wall: (24, 3) south",
            "Wall: (24, 4) south",
            "Wall: (24, 6) west",
            "Wall: (24, 8) west",
            "Wall: (24, 8) south",
            "Wall: (24, 9) west",
            "Wall: (24, 10) west",
            "Wall: (24, 11) west",
            "Wall: (24, 13) west",
            "Wall: (24, 13) south",
            "Wall: (24, 15) west",
            "Wall: (24, 16) south",
            "Wall: (24, 18) south",
            "Wall: (24, 19) west",
            "Wall: (24, 19) south",
            "Wall: (24, 25) west",
            "Wall: (25, 2) south",
            "Wall: (25, 4) south",
            "Wall: (25, 6) west",
            "Wall: (25, 6) south",
            "Wall: (25, 7) west",
            "Wall: (25, 9) west",
            "Wall: (25, 10) west",
            "Wall: (25, 11) west",
            "Wall: (25, 12) west",
            "Wall: (25, 13) west",
            "Wall: (25, 14) west",
            "Wall: (25, 15) west",
            "Wall: (25, 17) south",
            "Wall: (25, 18) west",
            "Wall: (25, 19) west",
            "Wall: (25, 19) south",
            "Wall: (25, 20) south",
            "Wall: (25, 21) south",
            "Wall: (25, 22) south",
            "Wall: (25, 23) south",
            "Wall: (25, 24) south",
            "Wall: (25, 25) west",
            "Wall: (25, 25) south",
            "Color: (22, 22) RED",
            "Beeper: (22, 22) 1",
            "Karel: (1, 1) east",
            "",
            "BeeperBag: INFINITE",
            "Speed: 0.50"
    );
}