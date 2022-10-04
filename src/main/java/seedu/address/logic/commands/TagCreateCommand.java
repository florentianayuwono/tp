package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Creates a tag in the tagList.
 */
public class TagCreateCommand extends Command {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = TagCommand.COMMAND_WORD
            + COMMAND_WORD + ": Creates a tag to the address book. "
            + "Parameters: "
            + PREFIX_TAG + "TAG...\n"
            + "Example: " + TagCommand.COMMAND_WORD
            + COMMAND_WORD + " "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New tag added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This tag already exists in the address book";

    private final Tag toCreate;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public TagCreateCommand(Tag tag) {
        requireNonNull(tag);
        this.toCreate = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTag(toCreate)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addTag(toCreate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toCreate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagCreateCommand // instanceof handles nulls
                && toCreate.equals(((TagCreateCommand) other).toCreate));
    }
}