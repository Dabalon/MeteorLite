/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package meteor.game;

import static net.runelite.api.Constants.CLIENT_DEFAULT_ZOOM;
import static net.runelite.api.ItemID.AGILITY_CAPE;
import static net.runelite.api.ItemID.AGILITY_CAPET;
import static net.runelite.api.ItemID.AGILITY_CAPET_13341;
import static net.runelite.api.ItemID.AGILITY_CAPE_13340;
import static net.runelite.api.ItemID.BOOTS_OF_LIGHTNESS;
import static net.runelite.api.ItemID.BOOTS_OF_LIGHTNESS_89;
import static net.runelite.api.ItemID.COINS_995;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_11861;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_13589;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_13590;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_13601;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_13602;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_13613;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_13614;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_13625;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_13626;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_13637;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_13638;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_13677;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_13678;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_21076;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_21078;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_24758;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_24760;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_25084;
import static net.runelite.api.ItemID.GRACEFUL_BOOTS_25086;
import static net.runelite.api.ItemID.GRACEFUL_CAPE;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_11853;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_13581;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_13582;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_13593;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_13594;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_13605;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_13606;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_13617;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_13618;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_13629;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_13630;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_13669;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_13670;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_21064;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_21066;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_24746;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_24748;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_25072;
import static net.runelite.api.ItemID.GRACEFUL_CAPE_25074;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_11859;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_13587;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_13588;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_13599;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_13600;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_13611;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_13612;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_13623;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_13624;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_13635;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_13636;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_13675;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_13676;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_21073;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_21075;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_24755;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_24757;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_25081;
import static net.runelite.api.ItemID.GRACEFUL_GLOVES_25083;
import static net.runelite.api.ItemID.GRACEFUL_HOOD;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_11851;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_13579;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_13580;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_13591;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_13592;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_13603;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_13604;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_13615;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_13616;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_13627;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_13628;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_13667;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_13668;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_21061;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_21063;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_24743;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_24745;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_25069;
import static net.runelite.api.ItemID.GRACEFUL_HOOD_25071;
import static net.runelite.api.ItemID.GRACEFUL_LEGS;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_11857;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_13585;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_13586;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_13597;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_13598;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_13609;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_13610;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_13621;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_13622;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_13633;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_13634;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_13673;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_13674;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_21070;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_21072;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_24752;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_24754;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_25078;
import static net.runelite.api.ItemID.GRACEFUL_LEGS_25080;
import static net.runelite.api.ItemID.GRACEFUL_TOP;
import static net.runelite.api.ItemID.GRACEFUL_TOP_11855;
import static net.runelite.api.ItemID.GRACEFUL_TOP_13583;
import static net.runelite.api.ItemID.GRACEFUL_TOP_13584;
import static net.runelite.api.ItemID.GRACEFUL_TOP_13595;
import static net.runelite.api.ItemID.GRACEFUL_TOP_13596;
import static net.runelite.api.ItemID.GRACEFUL_TOP_13607;
import static net.runelite.api.ItemID.GRACEFUL_TOP_13608;
import static net.runelite.api.ItemID.GRACEFUL_TOP_13619;
import static net.runelite.api.ItemID.GRACEFUL_TOP_13620;
import static net.runelite.api.ItemID.GRACEFUL_TOP_13631;
import static net.runelite.api.ItemID.GRACEFUL_TOP_13632;
import static net.runelite.api.ItemID.GRACEFUL_TOP_13671;
import static net.runelite.api.ItemID.GRACEFUL_TOP_13672;
import static net.runelite.api.ItemID.GRACEFUL_TOP_21067;
import static net.runelite.api.ItemID.GRACEFUL_TOP_21069;
import static net.runelite.api.ItemID.GRACEFUL_TOP_24749;
import static net.runelite.api.ItemID.GRACEFUL_TOP_24751;
import static net.runelite.api.ItemID.GRACEFUL_TOP_25075;
import static net.runelite.api.ItemID.GRACEFUL_TOP_25077;
import static net.runelite.api.ItemID.GRANITE_MAUL_24225;
import static net.runelite.api.ItemID.GRANITE_MAUL_24227;
import static net.runelite.api.ItemID.MAX_CAPE;
import static net.runelite.api.ItemID.MAX_CAPE_13342;
import static net.runelite.api.ItemID.PENANCE_GLOVES;
import static net.runelite.api.ItemID.PENANCE_GLOVES_10554;
import static net.runelite.api.ItemID.PLATINUM_TOKEN;
import static net.runelite.api.ItemID.SPOTTED_CAPE;
import static net.runelite.api.ItemID.SPOTTED_CAPE_10073;
import static net.runelite.api.ItemID.SPOTTIER_CAPE;
import static net.runelite.api.ItemID.SPOTTIER_CAPE_10074;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.Value;
import meteor.callback.ClientThread;
import meteor.config.RuneLiteConfig;
import meteor.eventbus.EventBus;
import meteor.eventbus.Subscribe;
import meteor.util.AsyncBufferedImage;
import net.runelite.api.Client;
import net.runelite.api.Constants;
import net.runelite.api.GameState;
import net.runelite.api.ItemComposition;
import net.runelite.api.SpritePixels;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.PostItemComposition;
import net.runelite.http.api.item.ItemClient;
import net.runelite.http.api.item.ItemPrice;
import net.runelite.http.api.item.ItemStats;
import okhttp3.OkHttpClient;
import org.sponge.util.Logger;

@Singleton
public class ItemManager {

  // Worn items with weight reducing property have a different worn and inventory ItemID
  private static final ImmutableMap<Integer, Integer> WORN_ITEMS = ImmutableMap
      .<Integer, Integer>builder().
          put(BOOTS_OF_LIGHTNESS_89, BOOTS_OF_LIGHTNESS).
          put(PENANCE_GLOVES_10554, PENANCE_GLOVES).

          put(GRACEFUL_HOOD_11851, GRACEFUL_HOOD).
          put(GRACEFUL_CAPE_11853, GRACEFUL_CAPE).
          put(GRACEFUL_TOP_11855, GRACEFUL_TOP).
          put(GRACEFUL_LEGS_11857, GRACEFUL_LEGS).
          put(GRACEFUL_GLOVES_11859, GRACEFUL_GLOVES).
          put(GRACEFUL_BOOTS_11861, GRACEFUL_BOOTS).
          put(GRACEFUL_HOOD_13580, GRACEFUL_HOOD_13579).
          put(GRACEFUL_CAPE_13582, GRACEFUL_CAPE_13581).
          put(GRACEFUL_TOP_13584, GRACEFUL_TOP_13583).
          put(GRACEFUL_LEGS_13586, GRACEFUL_LEGS_13585).
          put(GRACEFUL_GLOVES_13588, GRACEFUL_GLOVES_13587).
          put(GRACEFUL_BOOTS_13590, GRACEFUL_BOOTS_13589).
          put(GRACEFUL_HOOD_13592, GRACEFUL_HOOD_13591).
          put(GRACEFUL_CAPE_13594, GRACEFUL_CAPE_13593).
          put(GRACEFUL_TOP_13596, GRACEFUL_TOP_13595).
          put(GRACEFUL_LEGS_13598, GRACEFUL_LEGS_13597).
          put(GRACEFUL_GLOVES_13600, GRACEFUL_GLOVES_13599).
          put(GRACEFUL_BOOTS_13602, GRACEFUL_BOOTS_13601).
          put(GRACEFUL_HOOD_13604, GRACEFUL_HOOD_13603).
          put(GRACEFUL_CAPE_13606, GRACEFUL_CAPE_13605).
          put(GRACEFUL_TOP_13608, GRACEFUL_TOP_13607).
          put(GRACEFUL_LEGS_13610, GRACEFUL_LEGS_13609).
          put(GRACEFUL_GLOVES_13612, GRACEFUL_GLOVES_13611).
          put(GRACEFUL_BOOTS_13614, GRACEFUL_BOOTS_13613).
          put(GRACEFUL_HOOD_13616, GRACEFUL_HOOD_13615).
          put(GRACEFUL_CAPE_13618, GRACEFUL_CAPE_13617).
          put(GRACEFUL_TOP_13620, GRACEFUL_TOP_13619).
          put(GRACEFUL_LEGS_13622, GRACEFUL_LEGS_13621).
          put(GRACEFUL_GLOVES_13624, GRACEFUL_GLOVES_13623).
          put(GRACEFUL_BOOTS_13626, GRACEFUL_BOOTS_13625).
          put(GRACEFUL_HOOD_13628, GRACEFUL_HOOD_13627).
          put(GRACEFUL_CAPE_13630, GRACEFUL_CAPE_13629).
          put(GRACEFUL_TOP_13632, GRACEFUL_TOP_13631).
          put(GRACEFUL_LEGS_13634, GRACEFUL_LEGS_13633).
          put(GRACEFUL_GLOVES_13636, GRACEFUL_GLOVES_13635).
          put(GRACEFUL_BOOTS_13638, GRACEFUL_BOOTS_13637).
          put(GRACEFUL_HOOD_13668, GRACEFUL_HOOD_13667).
          put(GRACEFUL_CAPE_13670, GRACEFUL_CAPE_13669).
          put(GRACEFUL_TOP_13672, GRACEFUL_TOP_13671).
          put(GRACEFUL_LEGS_13674, GRACEFUL_LEGS_13673).
          put(GRACEFUL_GLOVES_13676, GRACEFUL_GLOVES_13675).
          put(GRACEFUL_BOOTS_13678, GRACEFUL_BOOTS_13677).
          put(GRACEFUL_HOOD_21063, GRACEFUL_HOOD_21061).
          put(GRACEFUL_CAPE_21066, GRACEFUL_CAPE_21064).
          put(GRACEFUL_TOP_21069, GRACEFUL_TOP_21067).
          put(GRACEFUL_LEGS_21072, GRACEFUL_LEGS_21070).
          put(GRACEFUL_GLOVES_21075, GRACEFUL_GLOVES_21073).
          put(GRACEFUL_BOOTS_21078, GRACEFUL_BOOTS_21076).
          put(GRACEFUL_HOOD_24745, GRACEFUL_HOOD_24743).
          put(GRACEFUL_CAPE_24748, GRACEFUL_CAPE_24746).
          put(GRACEFUL_TOP_24751, GRACEFUL_TOP_24749).
          put(GRACEFUL_LEGS_24754, GRACEFUL_LEGS_24752).
          put(GRACEFUL_GLOVES_24757, GRACEFUL_GLOVES_24755).
          put(GRACEFUL_BOOTS_24760, GRACEFUL_BOOTS_24758).
          put(GRACEFUL_HOOD_25071, GRACEFUL_HOOD_25069).
          put(GRACEFUL_CAPE_25074, GRACEFUL_CAPE_25072).
          put(GRACEFUL_TOP_25077, GRACEFUL_TOP_25075).
          put(GRACEFUL_LEGS_25080, GRACEFUL_LEGS_25078).
          put(GRACEFUL_GLOVES_25083, GRACEFUL_GLOVES_25081).
          put(GRACEFUL_BOOTS_25086, GRACEFUL_BOOTS_25084).

          put(MAX_CAPE_13342, MAX_CAPE).

          put(SPOTTED_CAPE_10073, SPOTTED_CAPE).
          put(SPOTTIER_CAPE_10074, SPOTTIER_CAPE).

          put(AGILITY_CAPET_13341, AGILITY_CAPET).
          put(AGILITY_CAPE_13340, AGILITY_CAPE).
          build();
  private final Client client;
  private final ClientThread clientThread;
  private final ItemClient itemClient;
  private final RuneLiteConfig runeLiteConfig;
  private final LoadingCache<ImageKey, AsyncBufferedImage> itemImages;
  private final LoadingCache<Integer, ItemComposition> itemCompositions;
  private final LoadingCache<OutlineKey, BufferedImage> itemOutlines;
  Logger log = new Logger("ItemManager");
  private Map<Integer, ItemPrice> itemPrices = Collections.emptyMap();
  private Map<Integer, ItemStats> itemStats = Collections.emptyMap();

  @Inject
  public ItemManager(Client client, ScheduledExecutorService scheduledExecutorService,
      ClientThread clientThread,
      OkHttpClient okHttpClient, EventBus eventBus, RuneLiteConfig runeLiteConfig) {
    this.client = client;
    this.clientThread = clientThread;
    this.itemClient = new ItemClient(okHttpClient);
    this.runeLiteConfig = runeLiteConfig;

    loadPrices();
    loadStats();

    itemImages = CacheBuilder.newBuilder()
        .maximumSize(128L)
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build(new CacheLoader<ImageKey, AsyncBufferedImage>() {
          @Override
          public AsyncBufferedImage load(ImageKey key) {
            return loadImage(key.itemId, key.itemQuantity, key.stackable);
          }
        });

    itemCompositions = CacheBuilder.newBuilder()
        .maximumSize(1024L)
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build(new CacheLoader<Integer, ItemComposition>() {
          @Override
          public ItemComposition load(Integer key) {
            return client.getItemComposition(key);
          }
        });

    itemOutlines = CacheBuilder.newBuilder()
        .maximumSize(128L)
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build(new CacheLoader<OutlineKey, BufferedImage>() {
          @Override
          public BufferedImage load(OutlineKey key) {
            return loadItemOutline(key.itemId, key.itemQuantity, key.outlineColor);
          }
        });

    eventBus.register(this);
  }

  private void loadPrices() {
    try {
      ItemPrice[] prices = itemClient.getPrices();
      if (prices != null) {
        ImmutableMap.Builder<Integer, ItemPrice> map = ImmutableMap
            .builder();
        for (ItemPrice price : prices) {
          map.put(price.getId(), price);
        }
        itemPrices = map.build();
      }

      log.debug("Loaded " + itemPrices.size() + " prices");
    } catch (Exception e) {
      log.warn("error loading prices!");
      e.printStackTrace();
    }
  }

  private void loadStats() {
    try {
      final Map<Integer, ItemStats> stats = itemClient.getStats();
      if (stats != null) {
        itemStats = ImmutableMap.copyOf(stats);
      }

      log.debug("Loaded " + itemStats.size() + " stats");
    } catch (Exception e) {
      log.warn("error loading stats!");
      e.printStackTrace();
    }
  }

  @Subscribe
  public void onGameStateChanged(final GameStateChanged event) {
    if (event.getGameState() == GameState.HOPPING
        || event.getGameState() == GameState.LOGIN_SCREEN) {
      itemCompositions.invalidateAll();
    }
  }

  @Subscribe
  public void onPostItemComposition(PostItemComposition event) {
    itemCompositions.put(event.getItemComposition().getId(), event.getItemComposition());
  }

  /**
   * Invalidates internal item manager item composition cache (but not client item composition
   * cache)
   *
   * @see Client#getItemCompositionCache()
   */
  public void invalidateItemCompositionCache() {
    itemCompositions.invalidateAll();
  }

  /**
   * Look up an item's price
   *
   * @param itemID item id
   * @return item price
   */
  public int getItemPrice(int itemID) {
    return getItemPriceWithSource(itemID, runeLiteConfig.useWikiItemPrices());
  }

  /**
   * Look up an item's price
   *
   * @param itemID       item id
   * @param useWikiPrice use the actively traded/wiki price
   * @return item price
   */
  public int getItemPriceWithSource(int itemID, boolean useWikiPrice) {
    if (itemID == COINS_995) {
      return 1;
    }
    if (itemID == PLATINUM_TOKEN) {
      return 1000;
    }

    ItemComposition itemComposition = getItemComposition(itemID);
    if (itemComposition.getNote() != -1) {
      itemID = itemComposition.getLinkedNoteId();
    }
    itemID = WORN_ITEMS.getOrDefault(itemID, itemID);

    int price = 0;

    final Collection<ItemMapping> mappedItems = ItemMapping.map(itemID);

    if (mappedItems == null) {
      final ItemPrice ip = itemPrices.get(itemID);

      if (ip != null) {
        price = useWikiPrice && ip.getWikiPrice() > 0 ? ip.getWikiPrice() : ip.getPrice();
      }
    } else {
      for (final ItemMapping mappedItem : mappedItems) {
        price += getItemPriceWithSource(mappedItem.getTradeableItem(), useWikiPrice) * mappedItem
            .getQuantity();
      }
    }

    return price;
  }

  public int getAlchValue(ItemComposition composition) {
    if (composition.getId() == COINS_995) {
      return 1;
    }
    if (composition.getId() == PLATINUM_TOKEN) {
      return 1000;
    }

    return Math.max(1, composition.getHaPrice());
  }

  public int getRepairValue(int itemId) {
    return getRepairValue(itemId, false);
  }

  private int getRepairValue(int itemId, boolean fullValue) {
    final ItemReclaimCost b = ItemReclaimCost.of(itemId);

    if (b != null) {
      if (fullValue || b.getItemID() == GRANITE_MAUL_24225 || b.getItemID() == GRANITE_MAUL_24227) {
        return b.getValue();
      }
      return (int) (b.getValue() * (75.0f / 100.0f));
    }

    return 0;
  }

  /**
   * Look up an item's stats
   *
   * @param itemId item id
   * @return item stats
   */
  @Nullable
  public ItemStats getItemStats(int itemId, boolean allowNote) {
    ItemComposition itemComposition = getItemComposition(itemId);

    if (itemComposition == null || itemComposition.getName() == null || (!allowNote
        && itemComposition.getNote() != -1)) {
      return null;
    }

    return itemStats.get(canonicalize(itemId));
  }

  /**
   * Search for tradeable items based on item name
   *
   * @param itemName item name
   * @return
   */
  public List<ItemPrice> search(String itemName) {
    itemName = itemName.toLowerCase();

    List<ItemPrice> result = new ArrayList<>();
    for (ItemPrice itemPrice : itemPrices.values()) {
      final String name = itemPrice.getName();
      if (name.toLowerCase().contains(itemName)) {
        result.add(itemPrice);
      }
    }
    return result;
  }

  /**
   * Look up an item's composition
   *
   * @param itemId item id
   * @return item composition
   */
  @Nonnull
  public ItemComposition getItemComposition(int itemId) {
    assert client.isClientThread() : "getItemComposition must be called on client thread";
    return itemCompositions.getUnchecked(itemId);
  }

  /**
   * Get an item's un-noted, un-placeholdered ID
   */
  public int canonicalize(int itemID) {
    ItemComposition itemComposition = getItemComposition(itemID);

    if (itemComposition.getNote() != -1) {
      return itemComposition.getLinkedNoteId();
    }

    if (itemComposition.getPlaceholderTemplateId() != -1) {
      return itemComposition.getPlaceholderId();
    }

    return WORN_ITEMS.getOrDefault(itemID, itemID);
  }

  /**
   * Loads item sprite from game, makes transparent, and generates image
   *
   * @param itemId
   * @return
   */
  private AsyncBufferedImage loadImage(int itemId, int quantity, boolean stackable) {
    AsyncBufferedImage img = new AsyncBufferedImage(Constants.ITEM_SPRITE_WIDTH,
        Constants.ITEM_SPRITE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    clientThread.invoke(() ->
    {
      if (client.getGameState().ordinal() < GameState.LOGIN_SCREEN.ordinal()) {
        return false;
      }
      SpritePixels sprite = client
          .createItemSprite(itemId, quantity, 1, SpritePixels.DEFAULT_SHADOW_COLOR,
              stackable ? 1 : 0, false, CLIENT_DEFAULT_ZOOM);
      if (sprite == null) {
        return false;
      }
      sprite.toBufferedImage(img);
      img.loaded();
      return true;
    });
    return img;
  }

  /**
   * Get item sprite image as BufferedImage.
   * <p>
   * This method may return immediately with a blank image if not called on the game thread. The
   * image will be filled in later. If this is used for a UI label/button, it should be added using
   * AsyncBufferedImage::addTo to ensure it is painted properly
   *
   * @param itemId
   * @return
   */
  public AsyncBufferedImage getImage(int itemId) {
    return getImage(itemId, 1, false);
  }

  /**
   * Get item sprite image as BufferedImage.
   * <p>
   * This method may return immediately with a blank image if not called on the game thread. The
   * image will be filled in later. If this is used for a UI label/button, it should be added using
   * AsyncBufferedImage::addTo to ensure it is painted properly
   *
   * @param itemId
   * @param quantity
   * @return
   */
  public AsyncBufferedImage getImage(int itemId, int quantity, boolean stackable) {
    try {
      return itemImages.get(new ImageKey(itemId, quantity, stackable));
    } catch (ExecutionException ex) {
      return null;
    }
  }

  /**
   * Create item sprite and applies an outline.
   *
   * @param itemId       item id
   * @param itemQuantity item quantity
   * @param outlineColor outline color
   * @return image
   */
  private BufferedImage loadItemOutline(final int itemId, final int itemQuantity,
      final Color outlineColor) {
    final SpritePixels itemSprite = client
        .createItemSprite(itemId, itemQuantity, 1, 0, 0, false, CLIENT_DEFAULT_ZOOM);
    return itemSprite.toBufferedOutline(outlineColor);
  }

  /**
   * Get item outline with a specific color.
   *
   * @param itemId       item id
   * @param itemQuantity item quantity
   * @param outlineColor outline color
   * @return image
   */
  public BufferedImage getItemOutline(final int itemId, final int itemQuantity,
      final Color outlineColor) {
    try {
      return itemOutlines.get(new OutlineKey(itemId, itemQuantity, outlineColor));
    } catch (ExecutionException e) {
      return null;
    }
  }

  @Value
  private static class ImageKey {

    private final int itemId;
    private final int itemQuantity;
    private final boolean stackable;
  }

  @Value
  private static class OutlineKey {

    private final int itemId;
    private final int itemQuantity;
    private final Color outlineColor;
  }
}
