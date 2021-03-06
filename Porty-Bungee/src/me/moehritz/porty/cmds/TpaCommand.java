package me.moehritz.porty.cmds;

import me.moehritz.porty.Messages;
import me.moehritz.porty.Porty;
import me.moehritz.porty.TextUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class TpaCommand extends BasePortyCommand
{

	public TpaCommand()
	{
		super("tpa", "porty.cmd.tpa", new String[] { "tpask" });
	}

	@Override
	public String[] getHelpText()
	{
		return Messages.getMessages("tpa_help");
	}

	@Override
	public void executeCommand(CommandSender sender, String[] args)
	{
		if (!(sender instanceof ProxiedPlayer))
		{
			sendMessage(sender, Messages.getMessage("console_warning", "&7You can not use this command as console or comandblocks"));
			return;
		}

		if (args.length == 1)
		{
			ProxiedPlayer fromPlayer = (ProxiedPlayer) sender;

			String targetName = args[0];
			ProxiedPlayer targetPlayer = ProxyServer.getInstance().getPlayer(targetName);

			if (targetPlayer == null)
			{
				sendMessages(sender, TextUtil.applyTag("<player>", targetName, Messages.getMessage("player_not_found", "&7Can´t find the player &e<player>&7.")));
				return;
			}

			Porty.getApi().getTeleportRequestHandler().addTpaRequest(fromPlayer, targetPlayer);
			sendMessage(fromPlayer, Messages.getMessage("tpa_request_sent", "Your request has been sent"));
			sendMessages(targetPlayer, TextUtil.applyTag("<player>", fromPlayer.getName(), Messages.getMessage("tpa", "&7The player &e<player>&7 asks you to teleport to you. Use &e/tpaccept&7 or &e/tpdeny&7 in order to respond to it.")));
		}
		else
		{
			sendWrongUsage(sender);
		}
	}
}
