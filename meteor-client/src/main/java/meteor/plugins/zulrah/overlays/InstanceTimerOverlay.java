package meteor.plugins.zulrah.overlays;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import meteor.plugins.zulrah.ZulrahConfig;
import meteor.ui.overlay.OverlayPanel;
import meteor.ui.overlay.OverlayPosition;
import meteor.ui.overlay.OverlayPriority;
import meteor.ui.overlay.components.LineComponent;

public class InstanceTimerOverlay extends OverlayPanel {
   private final ZulrahConfig config;
   private Instant instanceTimer;

   @Inject
   private InstanceTimerOverlay(ZulrahConfig config) {
      this.config = config;
      this.setPriority(OverlayPriority.HIGH);
      this.setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
   }

   @Override
   public Dimension render(Graphics2D graphics) {
      if (config.instanceTimer() && instanceTimer != null) {
         Duration elapsed = Duration.between(instanceTimer, Instant.now());

         panelComponent.getChildren().add(LineComponent.builder().left("Instance Timer:").leftColor(Color.WHITE).right(String.format("%d:%02d", elapsed.getSeconds() / 60, elapsed.getSeconds())).rightColor(Color.GREEN).build());
         return super.render(graphics);
      } else {
         return null;
      }
   }

   public void setTimer() {
      instanceTimer = Instant.now();
   }

   public void resetTimer() {
      instanceTimer = null;
   }
}
