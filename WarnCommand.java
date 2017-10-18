package b;

import a.Main;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public class WarnCommand extends Command{

	public WarnCommand() {
		super("경고","플레이어에게 경고를 추가합니다","/경고  추가|삭제|확인  닉네임  ");
		
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		try{
			switch (args[0]) {
			case "추가":
				if(!sender.isOp()){
					sender.sendMessage("해당명령어를 사용할 권한이 없습니다.");
					return false;
				}
				if(sender.isOp()){
					if(args.length<2){
						sender.sendMessage("/경고 추가 플레이어 횟수");
						return false;
					}else if(!Main.warning.exists(args[1].toLowerCase())){
						sender.sendMessage("존재하지 않는 플레이어입니다.");
						return false;
					}else if(args.length==3){
						int warn;
						try{
							warn=Integer.parseInt(args[2]);
						}catch(NumberFormatException nfex){
							sender.sendMessage("/경고 추가 플레이어 횟수\n횟수에 알맞은 값을 넣어주세요.ex)/경고 추가 steve 3");
							return false;}
						int i= Integer.parseInt(Main.warning.get(args[1]).toString());
						Main.warning.set(args[1],i+warn);
						sender.sendMessage("§o§3[경고] 경고를 주었습니다.");
						Main.warning.save();
						int iaf = Integer.parseInt(Main.warning.get(args[1]).toString());
						if(iaf>=5){
								 Main.instance.getServer().getNameBans().addBan(args[1]);
								 sender.sendMessage(TextFormat.RED+"경고 5회를 초과하여 벤처리 되었습니다.");
								 return true;
								 /*if(Main.instance.getServer().getPlayer(args[1]).isOnline()){
									 Main.instance.getServer().getPlayer(args[1]).kick("경고 5회를 초과하여 벤처리 되었습니다.");
									 return true;
									 }*/
								 }
                   
					}
				else{
					int i=Integer.parseInt(Main.warning.get(args[1]).toString());
  	 	 	 	 	 Main.warning.set(args[1],++i);
  	 	 	 	 	 sender.sendMessage("§o§3[경고] 경고를 주었습니다.");
  	 	 	 	 	 Main.warning.save();
  	 	 	 	int iaf = Integer.parseInt(Main.warning.get(args[1]).toString());
  	 	 	//Main.instance.getServer().getPlayer(args[1]).sendMessage
			//("관리진"+sender.getName()+"에 의해 경고 1회를 받았습니다.\n누적 경고횟수:"+iaf);
  	 	 	 	 	 if(iaf>=5){
  	 	 	 	 		 
  	 	 	 	  		 Main.instance.getServer().getNameBans().addBan(args[1]);
  	 	 	 	  		 sender.sendMessage(TextFormat.BLUE+"경고 5회를 초과하여 벤처리 되었습니다.");
  	 	 	 	 	 }
  	 	 	 	 	 }
				}
				break;

			case "삭제":
				if(!sender.isOp()){
					sender.sendMessage("해당명령어를 사용할 권한이 없습니다.");
					return false;}
				if(sender.isOp()){
					if(args.length<2){
						sender.sendMessage("/경고 삭제 플레이어");
						return false;
					}else if(!Main.warning.exists(args[1].toLowerCase())){
						sender.sendMessage("존재하지 않는 플레이어입니다.");
						return false;
					}else if(args.length==3){
						int i= Integer.parseInt(Main.warning.get(args[1]).toString());
							int warn;
							try{
								warn=Integer.parseInt(args[2]);
								}catch(NumberFormatException nfex){
									sender.sendMessage
									("/경고 삭제 플레이어 횟수\n횟수에 알맞은 값을 넣어주세요.ex)/경고 삭제 steve 3");
									return false;}
							if(i<=0){
								sender.sendMessage("§o§3[경고] 경고를 삭제하였습니다.");
								Main.warning.set(args[1],0);
								Main.warning.save();
								return false;
							}else{
							Main.warning.set(args[1].toLowerCase(),i-warn);
							sender.sendMessage("§o§3[경고] 경고를 삭제하였습니다.");
							Main.warning.save();}
							int iaf = Integer.parseInt(Main.warning.get(args[1]).toString());
							if(iaf<5){
								Main.instance.getServer().getNameBans().remove(args[1].toLowerCase());
								return true;
							}
							}
					else{
						int i=Integer.parseInt(Main.warning.get(args[1]).toString());
						if(i<=0){
							sender.sendMessage("§o§3[경고] 경고를 삭제하였습니다.");
							Main.warning.set(args[1],0);
							Main.warning.save();
							return false;}
						else{
						Main.warning.set(args[1],--i);
						sender.sendMessage("§o§3[경고] 경고를 삭제하였습니다.");
						Main.warning.save();
						int iaf = Integer.parseInt(Main.warning.get(args[1]).toString());
						if(iaf<5){
							Main.instance.getServer().getNameBans().remove(args[1].toLowerCase());
							return true;}
							}
						}
				}
				break;
			
			case "확인":
				if(args.length==1){
					if(!sender.isPlayer()){
					sender.sendMessage("플레이어만 사용 가능한 명령어 입니다.\n/경고 확인 플레이어");
					return false;
					}else{
					Player player = (Player)sender;
					String name = player.getName().toLowerCase();
					int warn = Integer.parseInt(Main.warning.get(name).toString());
					sender.sendMessage("=======WarningList========");
					sender.sendMessage("나의 경고 횟수:"+warn+"회");
					return true;
					}
				}else if(!Main.warning.exists(args[1].toLowerCase())){
					sender.sendMessage("존재하지 않는 플레이어입니다.");
					return false;
				}else if(args.length==2){
					int warn = Integer.parseInt(Main.warning.get(args[1]).toString());
					sender.sendMessage(args[1]+"의 경고 횟수:"+warn+"회");	
					return false;
					}break;
			}
			
		}catch(ArrayIndexOutOfBoundsException ex){
			sender.sendMessage(this.getUsage());
			return false;
		}
		return false;
	}
	

}
