package meteor.plugins.zulrah.rotationutils;

import java.util.Optional;
import javax.annotation.Nullable;
import net.runelite.api.Prayer;
import meteor.plugins.zulrah.ZulrahPlugin;
import meteor.plugins.zulrah.constants.StandLocation;
import meteor.plugins.zulrah.constants.ZulrahType;

public class ZulrahData {
   @Nullable
   private final ZulrahPhase current;
   @Nullable
   private final ZulrahPhase next;

   public ZulrahData(@Nullable ZulrahPhase current, @Nullable ZulrahPhase next) {
      this.current = current;
      this.next = next;
   }

   public Optional<ZulrahPhase>  getCurrentPhase() {
      return Optional.ofNullable(this.current);
   }

   public Optional<ZulrahPhase>  getNextPhase() {
      return Optional.ofNullable(this.next);
   }

   public Optional<ZulrahNpc> getCurrentZulrahNpc() {
      return this.current == null ? Optional.empty() : Optional.ofNullable(this.current.getZulrahNpc());
   }

   public Optional<ZulrahNpc>  getNextZulrahNpc() {
      return this.next == null ? Optional.empty() : Optional.ofNullable(this.next.getZulrahNpc());
   }

   public Optional<StandLocation> getCurrentDynamicStandLocation() {
      if (this.current == null) {
         return Optional.empty();
      } else if (this.current.getZulrahNpc().getType() == ZulrahType.MELEE) {
         switch(this.current.getAttributes().getStandLocation()) {
         case NORTHEAST_TOP:
            return ZulrahPlugin.isFlipStandLocation() ? Optional.of(StandLocation.NORTHEAST_BOTTOM) : Optional.of(this.current.getAttributes().getStandLocation());
         case WEST:
            return ZulrahPlugin.isFlipStandLocation() ? Optional.of(StandLocation.NORTHWEST_BOTTOM) : Optional.of(this.current.getAttributes().getStandLocation());
         default:
            return Optional.of(this.current.getAttributes().getStandLocation());
         }
      } else {
         return Optional.of(this.current.getAttributes().getStandLocation());
      }
   }

   public Optional<StandLocation> getNextStandLocation() {
      return this.next == null ? Optional.empty() : Optional.of(this.next.getAttributes().getStandLocation());
   }

   public Optional<StandLocation> getCurrentStallLocation() {
      return this.current == null ? Optional.empty() : Optional.ofNullable(this.current.getAttributes().getStallLocation());
   }

   public Optional<StandLocation> getNextStallLocation() {
      return this.next == null ? Optional.empty() : Optional.ofNullable(this.next.getAttributes().getStallLocation());
   }

   public Optional<Prayer> getCurrentPhasePrayer() {
      if (ZulrahPlugin.isZulrahReset()) {
         return Optional.of(Prayer.PROTECT_FROM_MISSILES);
      } else if (this.current != null && this.current.getAttributes().getPrayer() != null) {
         Prayer phasePrayer = this.current.getAttributes().getPrayer();
         Prayer invertedPhasePrayer = phasePrayer == Prayer.PROTECT_FROM_MAGIC ? Prayer.PROTECT_FROM_MISSILES : Prayer.PROTECT_FROM_MAGIC;
         return this.isJad() ? (ZulrahPlugin.isFlipPhasePrayer() ? Optional.of(invertedPhasePrayer) : Optional.of(phasePrayer)) : Optional.of(phasePrayer);
      } else {
         return Optional.empty();
      }
   }

   public boolean standLocationsMatch() {
      return this.getCurrentDynamicStandLocation().isPresent() && this.getNextStandLocation().isPresent() && ((StandLocation)this.getCurrentDynamicStandLocation().get()).equals(this.getNextStandLocation().get());
   }

   public boolean stallLocationsMatch() {
      return this.isPhasesNotNull() && this.current.getAttributes().getStallLocation() != null && this.next.getAttributes().getStallLocation() != null && this.current.getAttributes().getStallLocation().equals(this.next.getAttributes().getStallLocation());
   }

   public boolean isJad() {
      return this.current != null && this.current.getZulrahNpc().isJad();
   }

   private boolean isPhasesNotNull() {
      return this.current != null && this.next != null;
   }
}
