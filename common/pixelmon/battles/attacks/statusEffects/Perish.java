package pixelmon.battles.attacks.statusEffects;

import java.util.ArrayList;

import net.minecraft.src.DamageSource;
import pixelmon.comm.ChatHandler;
import pixelmon.entities.pixelmon.EntityPixelmon;

public class Perish extends StatusEffectBase {
int turnCount = 0;
	public Perish() {
		super(StatusEffectType.Perish, false, false, false);
		
	}
	@Override
	public void ApplyEffect(EntityPixelmon user, EntityPixelmon target, ArrayList<String> attackList) {
if (checkChance()){
		for (StatusEffectBase a : target.status)
			if (a.type == StatusEffectType.Perish) {
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), target.getName() + " has already heard the song!");
			return;
			}
		for (StatusEffectBase b : user.status)
			if (b.type == StatusEffectType.Perish) {
				ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getOwner()+" has already heard the song!");
		    return;
			}
		
			 user.status.add(new Perish());
				ChatHandler.sendChat(user.getOwner(), user.getName() + " heard the Perish Song!");
			 target.status.add(new Perish());
			    ChatHandler.sendChat(target.getOwner(), target.getName() + " heard the Perish Song!");
	
			    
		}
	
		
	}
	@Override
	public void turnTick(EntityPixelmon user, EntityPixelmon target){
		
			if(turnCount == 3){
				if (!target.isDead){
					target.attackEntityFrom(DamageSource.causeMobDamage(user), target.getHealth());
					ChatHandler.sendChat(target.getOwner(), user.getOwner(), "Perish Song struck " + target.getName() + "!");
				}
				if (!user.isDead){
				user.attackEntityFrom(DamageSource.causeMobDamage(user), target.getHealth());
				ChatHandler.sendChat(target.getOwner(), user.getOwner(), "Perish Song struck " + user.getName() + "!");
			}
			}
			turnCount++;
			
		}



	}
