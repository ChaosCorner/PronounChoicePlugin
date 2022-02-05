package xyz.chaoscorner.pronounchoice.models;

import java.util.UUID;

public record PlayerPronouns(UUID id, Pronouns primary, Pronouns secondary) {}
