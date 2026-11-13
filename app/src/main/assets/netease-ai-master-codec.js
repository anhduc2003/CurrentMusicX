if(!Object.entries)Object.entries=function(o){return Object.keys(o).map(function(k){return [k,o[k]];});};
var NeteaseAiMaster = (function() {
  var __defProp = Object.defineProperty;
  var __getOwnPropDesc = Object.getOwnPropertyDescriptor;
  var __getOwnPropNames = Object.getOwnPropertyNames;
  var __hasOwnProp = Object.prototype.hasOwnProperty;
  var __export = function(target, all) {
    for (var name in all)
      __defProp(target, name, { get: all[name], enumerable: true });
  };
  var __copyProps = function(to, from, except, desc) {
    if (from && typeof from === "object" || typeof from === "function") {
      for (let key of __getOwnPropNames(from))
        if (!__hasOwnProp.call(to, key) && key !== except)
          __defProp(to, key, { get: function() {
            return from[key];
          }, enumerable: !(desc = __getOwnPropDesc(from, key)) || desc.enumerable });
    }
    return to;
  };
  var __toCommonJS = function(mod) {
    return __copyProps(__defProp({}, "__esModule", { value: true }), mod);
  };

  // .verification/sound-effects/ai-master/index.ts
  var index_exports = {};
  __export(index_exports, {
    composeEffect: function() {
      return composeEffect;
    }
  });

  // .verification/sound-effects/ai-master/logger-adapter.ts
  var logger = { info() {
  }, warn() {
  }, error() {
  }, stepStart() {
  }, stepEnd() {
  }, logRule() {
  }, logClosingRule() {
  }, logMergeRule() {
  } };

  // .verification/sound-effects/ai-master/validator.ts
  function parseJsonOrObject(data, typeName) {
    try {
      if (typeof data === "string") {
        var parsed = JSON.parse(data);
        if (!parsed || typeof parsed !== "object") {
          logger.error(`${typeName} phân tích thất bại: không phải đối tượng hợp lệ`, { data });
          return null;
        }
        return parsed;
      } else if (typeof data === "object" && data !== null) {
        return data;
      } else {
        logger.error(`${typeName} lỗi kiểu: phải là chuỗi hoặc đối tượng`, { data });
        return null;
      }
    } catch (error) {
      logger.error(`${typeName} phân tích thất bại: định dạng JSON không hợp lệ`, { data, error });
      return null;
    }
  }
  function validateInputs(params) {
    var errors = [];
    if (!params) {
      errors.push("Tham số kiểu ComposeEffectDataParams không được để trống");
      logger.warn("Xác thực đầu vào thất bại", { errors });
      return {
        isValid: false,
        errors
      };
    }
    if (!params.baseJson && !params.songJson) {
      errors.push("Cần ít nhất một trong baseJson và songJson");
      logger.warn("Xác thực đầu vào thất bại", { errors });
      return {
        isValid: false,
        errors
      };
    }
    if (params.songJson && params.diyJson) {
      var songEffect = parseJsonOrObject(params.songJson, "songJson");
      var diyEffect = parseJsonOrObject(params.diyJson, "diyJson");
      if (!songEffect) {
        errors.push("Định dạng songJson không đúng");
      }
      if (!diyEffect) {
        errors.push("Định dạng diyJson không đúng");
      }
      if (errors.length === 0) {
        logger.info("Xác thực đầu vào thành công");
      } else {
        logger.warn("Xác thực đầu vào thất bại", { errors });
      }
      return {
        isValid: errors.length === 0,
        errors
      };
    }
    if (params.baseJson && params.diyJson) {
      var baseEffect = parseJsonOrObject(params.baseJson, "baseJson");
      var diyEffect = parseJsonOrObject(params.diyJson, "diyJson");
      if (!baseEffect) {
        errors.push("Định dạng baseJson không đúng");
      }
      if (!diyEffect) {
        errors.push("Định dạng diyJson không đúng");
      }
      if (errors.length === 0) {
        logger.info("Xác thực đầu vào thành công");
      } else {
        logger.warn("Xác thực đầu vào thất bại", { errors });
      }
      return {
        isValid: errors.length === 0,
        errors
      };
    }
    if (params.supportStereo !== void 0 && params.supportStereo !== null) {
      if (typeof params.supportStereo !== "boolean") {
        errors.push("supportStereo phải là kiểu boolean");
      }
      if (errors.length === 0) {
        logger.info("Xác thực đầu vào thành công");
      } else {
        logger.warn("Xác thực đầu vào thất bại", { errors });
      }
      return {
        isValid: errors.length === 0,
        errors
      };
    }
    if (errors.length === 0) {
      logger.info("Xác thực đầu vào thành công");
    } else {
      logger.warn("Xác thực đầu vào thất bại", { errors });
    }
    return {
      isValid: errors.length === 0,
      errors
    };
  }

  // .verification/sound-effects/ai-master/closingRules.ts
  function processClosingRules(input) {
    logger.stepStart("Xử lý quy tắc tắt", "Xử lý logic tắt của diyJson theo quy tắc 1");
    var { baseJson, songJson, diyJson, supportStereo } = input;
    var filteredDiyJson = {};
    var closedKeys = [];
    if (supportStereo) {
      logger.logClosingRule("Tất cả hiệu ứng Diy", true, "supportStereo là true, tắt tất cả hiệu ứng diy");
      logger.stepEnd("Xử lý quy tắc tắt", {
        filteredDiyJson: {},
        closedKeys: Object.keys(diyJson),
        reason: "supportStereo=true"
      });
      return {
        baseJson,
        songJson,
        filteredDiyJson: {},
        closedKeys: Object.keys(diyJson)
      };
    }
    var sourceData = songJson || baseJson;
    var isSongMode = !!songJson;
    Object.entries(diyJson).forEach(function([key, value]) {
      var effectKey = key;
      switch (effectKey) {
        case "en_bass":
          if (shouldCloseBass(sourceData, isSongMode)) {
            closedKeys.push(key);
            logger.logClosingRule(key, true, "Khớp điều kiện tắt tăng cường dải trầm");
          } else {
            filteredDiyJson[effectKey] = value;
            logger.logClosingRule(key, false, "Không khớp điều kiện tắt tăng cường dải trầm");
          }
          break;
        case "en_treble":
          if (shouldCloseTreble(sourceData, isSongMode)) {
            closedKeys.push(key);
            logger.logClosingRule(key, true, "Khớp điều kiện tắt tăng cường dải cao");
          } else {
            filteredDiyJson[effectKey] = value;
            logger.logClosingRule(key, false, "Không khớp điều kiện tắt tăng cường dải cao");
          }
          break;
        case "en_vocal":
          if (shouldCloseVocal(sourceData, isSongMode)) {
            closedKeys.push(key);
            logger.logClosingRule(key, true, "Khớp điều kiện tắt tăng cường giọng hát");
          } else {
            filteredDiyJson[effectKey] = value;
            logger.logClosingRule(key, false, "Không khớp điều kiện tắt tăng cường giọng hát");
          }
          break;
        case "en_vibe":
        case "en_live":
          break;
        default:
          filteredDiyJson[effectKey] = value;
          logger.logClosingRule(key, false, "Loại hiệu ứng âm thanh không xác định, giữ nguyên mặc định");
      }
    });
    processVibeAndLive(diyJson, filteredDiyJson, closedKeys, sourceData, isSongMode);
    logger.stepEnd("Xử lý quy tắc tắt", {
      originalKeys: Object.keys(diyJson),
      filteredKeys: Object.keys(filteredDiyJson),
      closedKeys
    });
    return {
      baseJson,
      songJson,
      filteredDiyJson,
      closedKeys
    };
  }
  function shouldCloseBass(sourceData, isSongMode) {
    var _a, _b, _c;
    if (!sourceData) return false;
    if (!isSongMode) {
      logger.logRule("1a-base", true, "Hợp nhất baseJson + diyJson, tắt trực tiếp tăng cường dải trầm");
      return true;
    }
    var feature = sourceData.feature;
    var aev2 = sourceData.aev2;
    var drumRatio = (_a = feature == null ? void 0 : feature.vocal_drum_bass_other_ratio) == null ? void 0 : _a[1];
    if (drumRatio !== void 0 && drumRatio < 0.05) {
      logger.logRule("1a-1", true, `drum ratio (${drumRatio}) < 0.05`);
      return true;
    }
    var eqs = (_b = aev2 == null ? void 0 : aev2.eq) == null ? void 0 : _b.eqs;
    var lowRatio = (_c = feature == null ? void 0 : feature.low_mid_high_ratio) == null ? void 0 : _c[0];
    if (eqs && Array.isArray(eqs) && eqs.length >= 3 && lowRatio !== void 0) {
      var eqsFirst3Avg = (eqs[0] + eqs[1] + eqs[2]) / 3;
      var condition = eqsFirst3Avg + (1 - lowRatio) * 6;
      if (condition > 6) {
        logger.logRule("1a-2", true, `Trung bình ba đoạn đầu eqs (${eqsFirst3Avg}) + (1-low_ratio(${lowRatio})) * 6 = ${condition} > 6`);
        return true;
      }
    }
    logger.logRule("1a", false, "Không khớp điều kiện tắt tăng cường dải trầm", {
      drumRatio,
      eqsFirst3: eqs == null ? void 0 : eqs.slice(0, 3),
      lowRatio
    });
    return false;
  }
  function shouldCloseTreble(sourceData, isSongMode) {
    var _a, _b, _c;
    if (!sourceData) return false;
    var feature = sourceData.feature;
    var aev2 = sourceData.aev2;
    if (!isSongMode) {
      var eqs = (_b = (_a = sourceData == null ? void 0 : sourceData.effect) == null ? void 0 : _a.eq) == null ? void 0 : _b.eqs;
      if (eqs && Array.isArray(eqs) && eqs.length >= 3) {
        var lastIndex = eqs.length - 1;
        var eqsLast3Avg = (eqs[lastIndex] + eqs[lastIndex - 1] + eqs[lastIndex - 2]) / 3;
        if (eqsLast3Avg > 5) {
          logger.logRule("1b-base", true, `Hợp nhất baseJson + diyJson, trung bình ba đoạn cuối eqs (${eqsLast3Avg}) > 5`);
          return true;
        }
      }
      logger.logRule("1b-base", false, "Hợp nhất baseJson + diyJson, không có trường eqs hoặc không khớp điều kiện tắt, mặc định bật", {
        eqsLast3: eqs == null ? void 0 : eqs.slice(-3),
        hasEqs: !!eqs
      });
      return false;
    }
    var vocalMainFreq = feature == null ? void 0 : feature.vocal_main_freq;
    if (vocalMainFreq !== void 0 && vocalMainFreq > 600) {
      logger.logRule("1b-1", true, `vocal_main_freq (${vocalMainFreq}) > 600`);
      return true;
    }
    var spectralCentroid = feature == null ? void 0 : feature.audio_spectral_centroid;
    if (spectralCentroid !== void 0 && spectralCentroid > 4e3) {
      logger.logRule("1b-2", true, `audio_spectral_centroid (${spectralCentroid}) > 4000`);
      return true;
    }
    var eqs = (_c = aev2 == null ? void 0 : aev2.eq) == null ? void 0 : _c.eqs;
    if (eqs && Array.isArray(eqs) && eqs.length >= 3) {
      var lastIndex = eqs.length - 1;
      var eqsLast3Avg = (eqs[lastIndex] + eqs[lastIndex - 1] + eqs[lastIndex - 2]) / 3;
      if (eqsLast3Avg > 5) {
        logger.logRule("1b-3", true, `Trung bình ba đoạn cuối eqs (${eqsLast3Avg}) > 5`);
        return true;
      }
    }
    logger.logRule("1b", false, "Không khớp điều kiện tắt tăng cường dải cao", {
      vocalMainFreq,
      spectralCentroid,
      eqsLast3: eqs == null ? void 0 : eqs.slice(-3)
    });
    return false;
  }
  function shouldCloseVocal(sourceData, isSongMode) {
    var _a;
    if (!sourceData) return false;
    if (!isSongMode) {
      logger.logRule("1c-base", true, "Hợp nhất baseJson + diyJson, tắt trực tiếp tăng cường giọng hát");
      return true;
    }
    var feature = sourceData.feature;
    var vocalRatio = (_a = feature == null ? void 0 : feature.vocal_drum_bass_other_ratio) == null ? void 0 : _a[0];
    if (vocalRatio !== void 0 && vocalRatio < 0.2) {
      logger.logRule("1c", true, `vocal ratio (${vocalRatio}) < 0.2`);
      return true;
    }
    logger.logRule("1c", false, "Không khớp điều kiện tắt tăng cường giọng hát", { vocalRatio });
    return false;
  }
  function processVibeAndLive(originalDiyJson, filteredDiyJson, closedKeys, sourceData, isSongMode) {
    var _a, _b;
    var hasVibe = "en_vibe" in originalDiyJson;
    var hasLive = "en_live" in originalDiyJson;
    if (!hasVibe && !hasLive) {
      return;
    }
    if (!hasVibe || !hasLive) {
      if (hasVibe && originalDiyJson.en_vibe) {
        filteredDiyJson.en_vibe = originalDiyJson.en_vibe;
        logger.logClosingRule("en_vibe", false, "Chỉ có tăng cường không khí, giữ nguyên");
      }
      if (hasLive && originalDiyJson.en_live) {
        filteredDiyJson.en_live = originalDiyJson.en_live;
        logger.logClosingRule("en_live", false, "Chỉ có tăng cường cảm giác hiện trường, giữ nguyên");
      }
      return;
    }
    if (isSongMode) {
      var rvbOn = (_b = (_a = sourceData == null ? void 0 : sourceData.aev2) == null ? void 0 : _a.rvb) == null ? void 0 : _b.on;
      if (rvbOn !== true) {
        filteredDiyJson.en_live = originalDiyJson.en_live;
        closedKeys.push("en_vibe");
        logger.logClosingRule("en_vibe", true, "Trong songJson chưa bật tiếng vang, chọn tăng cường cảm giác hiện trường");
        logger.logClosingRule("en_live", false, "Trong songJson chưa bật tiếng vang, chọn tăng cường cảm giác hiện trường");
      } else {
        filteredDiyJson.en_vibe = originalDiyJson.en_vibe;
        closedKeys.push("en_live");
        logger.logClosingRule("en_vibe", false, "Trong songJson đã bật tiếng vang, chọn tăng cường không khí");
        logger.logClosingRule("en_live", true, "Trong songJson đã bật tiếng vang, chọn tăng cường không khí");
      }
    } else {
      filteredDiyJson.en_vibe = originalDiyJson.en_vibe;
      closedKeys.push("en_live");
      logger.logClosingRule("en_vibe", false, "Hợp nhất baseJson, chỉ bật tăng cường không khí");
      logger.logClosingRule("en_live", true, "Hợp nhất baseJson, chỉ bật tăng cường không khí");
    }
  }

  // .verification/sound-effects/ai-master/mergeRules.ts
  function countEnabledEffects(diyJson) {
    var count = 0;
    if (diyJson.en_bass) count++;
    if (diyJson.en_treble) count++;
    if (diyJson.en_vocal) count++;
    if (diyJson.en_vibe || diyJson.en_live) count++;
    return count;
  }
  function processBassAndTreble(mergedJson, diyJson, isSongMode) {
    var hasBass = !!diyJson.en_bass;
    var hasTreble = !!diyJson.en_treble;
    if (!hasBass && !hasTreble) {
      return;
    }
    logger.info("Quy tắc hợp nhất - Chồng BT: xử lý tăng cường dải trầm/dải cao", { hasBass, hasTreble });
    var mergedBt;
    if (hasBass && !hasTreble) {
      mergedBt = diyJson.en_bass.bt;
      logger.logMergeRule("Bass gán trực tiếp", "Chỉ có tăng cường dải trầm, dùng trực tiếp en_bass.bt", {}, mergedBt);
    } else if (!hasBass && hasTreble) {
      mergedBt = diyJson.en_treble.bt;
      logger.logMergeRule("Treble gán trực tiếp", "Chỉ có tăng cường dải cao, dùng trực tiếp en_treble.bt", {}, mergedBt);
    } else {
      mergedBt = { on: true };
      var bassBt = diyJson.en_bass.bt;
      mergedBt.bass = bassBt.bass || 0;
      mergedBt.bassFreq = bassBt.bassFreq || 0;
      mergedBt.bassQ = bassBt.bassQ || 0;
      var trebleBt = diyJson.en_treble.bt;
      mergedBt.treble = trebleBt.treble || 0;
      mergedBt.trebleFreq = trebleBt.trebleFreq || 0;
      mergedBt.trebleQ = trebleBt.trebleQ || 0;
      logger.logMergeRule("BT hợp nhất có chọn lọc", "Đồng thời có tăng cường dải trầm và dải cao, hợp nhất có chọn lọc", {}, mergedBt);
    }
    if (isSongMode) {
      if (!mergedJson.aev2) {
        mergedJson.aev2 = {};
      }
      mergedJson.aev2.bt = mergedBt;
    } else {
      if (!mergedJson.effect) {
        mergedJson.effect = {};
      }
      mergedJson.effect.bt = mergedBt;
    }
    logger.logMergeRule("BT cuối cùng", "Hoàn tất hợp nhất tăng cường dải trầm và dải cao", {}, mergedBt);
  }
  function processVocal(mergedJson, diyJson, isSongMode) {
    if (!diyJson.en_vocal) {
      return;
    }
    logger.info("Xử lý trường peq của tăng cường giọng hát");
    var peq = diyJson.en_vocal.peq;
    if (isSongMode) {
      if (!mergedJson.aev2) {
        mergedJson.aev2 = {};
      }
      mergedJson.aev2.peq = peq;
    } else {
      if (!mergedJson.effect) {
        mergedJson.effect = {};
      }
      mergedJson.effect.peq = peq;
    }
    logger.logMergeRule("Tăng cường giọng hát", "Thêm trường peq", {}, peq);
  }
  function processVibe(mergedJson, diyJson, isSongMode) {
    var _a;
    if (!diyJson.en_vibe) {
      return;
    }
    logger.info("Xử lý tăng cường không khí");
    var vibe = diyJson.en_vibe;
    if (isSongMode) {
      if (!mergedJson.aev2) {
        mergedJson.aev2 = {};
      }
      if (((_a = mergedJson.aev2.rvb) == null ? void 0 : _a.on) === true) {
        mergedJson.aev2.rvb.on = false;
        logger.logMergeRule("Không khí-tiếng vang", "Tắt tiếng vang ban đầu", { "rvb.on": true }, { "rvb.on": false });
      }
      mergedJson.aev2.se = vibe.se;
      logger.logMergeRule("Không khí-SE", "Thêm trường se", {}, vibe.se);
      if (vibe.cmp) {
        if (!mergedJson.aev2.cmp) {
          mergedJson.aev2.cmp = {
            attack: 0,
            on: false,
            // Cuối cùng sẽ điều chỉnh dựa trên giá trị pregain
            pregain: 0,
            release: 0,
            range: 0,
            ratio: 1,
            postgain: 0,
            threshold: 0
          };
          logger.logMergeRule("Giá trị mặc định CMP", "Đặt trường cmp mặc định", {}, mergedJson.aev2.cmp);
        }
        var originalPregain = mergedJson.aev2.cmp.pregain || 0;
        var additionalPregain = vibe.cmp.pregain || 0;
        mergedJson.aev2.cmp.pregain = originalPregain + additionalPregain;
        logger.logMergeRule(
          "Không khí-CMP",
          "Chồng pregain",
          { pregain: originalPregain },
          { pregain: originalPregain + additionalPregain }
        );
      }
    } else {
      if (!mergedJson.effect) {
        mergedJson.effect = {};
      }
      mergedJson.effect.se = vibe.se;
      logger.logMergeRule("Không khí-SE", "Thêm trường se vào effect", {}, vibe.se);
      if (vibe.cmp) {
        if (!mergedJson.effect.cmp) {
          mergedJson.effect.cmp = {
            attack: 0,
            on: false,
            // Cuối cùng sẽ điều chỉnh dựa trên giá trị pregain
            pregain: 0,
            release: 0,
            range: 0,
            ratio: 1,
            postgain: 0,
            threshold: 0
          };
          logger.logMergeRule("Giá trị mặc định CMP", "Đặt trường cmp mặc định", {}, mergedJson.effect.cmp);
        }
        var originalPregain = mergedJson.effect.cmp.pregain || 0;
        var additionalPregain = vibe.cmp.pregain || 0;
        mergedJson.effect.cmp.pregain = originalPregain + additionalPregain;
        logger.logMergeRule(
          "Không khí-CMP",
          "Chồng pregain vào effect",
          { pregain: originalPregain },
          { pregain: originalPregain + additionalPregain }
        );
      }
    }
  }
  function processLive(mergedJson, diyJson, isSongMode) {
    if (!diyJson.en_live) {
      return;
    }
    logger.info("Xử lý tăng cường cảm giác hiện trường");
    var live = diyJson.en_live;
    if (isSongMode) {
      if (!mergedJson.aev2) {
        mergedJson.aev2 = {};
      }
      var originalRvb = mergedJson.aev2.rvb;
      mergedJson.aev2.rvb = live.rvb;
      logger.logMergeRule("Tăng cường cảm giác hiện trường", "Thay thế trường rvb", originalRvb, live.rvb);
    } else {
      if (!mergedJson.effect) {
        mergedJson.effect = {};
      }
      var originalRvb = mergedJson.effect.rvb;
      mergedJson.effect.rvb = live.rvb;
      logger.logMergeRule("Tăng cường cảm giác hiện trường", "Thay thế trường effect.rvb", originalRvb, live.rvb);
    }
  }
  function adjustPregain(mergedJson, enabledEffectsCount, isSongMode) {
    if (enabledEffectsCount === 0) {
      return;
    }
    logger.info(`Điều chỉnh pregain, số lượng hiệu ứng đang bật: ${enabledEffectsCount}`);
    var pregainAdjustment = 0;
    if (enabledEffectsCount === 1) {
      pregainAdjustment = -0.5;
    } else if (enabledEffectsCount === 2) {
      pregainAdjustment = -1.2;
    } else if (enabledEffectsCount === 3) {
      pregainAdjustment = -2.1;
    } else if (enabledEffectsCount === 4) {
      pregainAdjustment = -3;
    }
    if (isSongMode) {
      if (!mergedJson.aev2) {
        mergedJson.aev2 = {};
      }
      if (!mergedJson.aev2.cmp) {
        mergedJson.aev2.cmp = {
          attack: 0,
          on: true,
          pregain: 0,
          release: 0,
          range: 0,
          ratio: 1,
          postgain: 0,
          threshold: 0
        };
        logger.logMergeRule("Giá trị mặc định CMP", "Đặt trường cmp mặc định", {}, mergedJson.aev2.cmp);
      }
      var originalPregain = mergedJson.aev2.cmp.pregain || 0;
      mergedJson.aev2.cmp.pregain = originalPregain + pregainAdjustment;
      logger.logMergeRule(
        "Điều chỉnh pregain",
        "Điều chỉnh aev2.cmp.pregain",
        { pregain: originalPregain },
        { pregain: mergedJson.aev2.cmp.pregain, adjustment: pregainAdjustment }
      );
    } else {
      if (!mergedJson.effect) {
        mergedJson.effect = {};
      }
      if (!mergedJson.effect.cmp) {
        mergedJson.effect.cmp = {
          attack: 0,
          on: true,
          pregain: 0,
          release: 0,
          range: 0,
          ratio: 1,
          postgain: 0,
          threshold: 0
        };
        logger.logMergeRule("Giá trị mặc định CMP", "Đặt trường effect.cmp mặc định", {}, mergedJson.effect.cmp);
      }
      var originalPregain = mergedJson.effect.cmp.pregain || 0;
      mergedJson.effect.cmp.pregain = originalPregain + pregainAdjustment;
      logger.logMergeRule(
        "Điều chỉnh pregain",
        "Điều chỉnh effect.cmp.pregain",
        { pregain: originalPregain },
        { pregain: mergedJson.effect.cmp.pregain, adjustment: pregainAdjustment }
      );
    }
  }
  function applyStereoAdjustment(mergedJson, isSongMode) {
    var _a, _b, _c, _d;
    logger.info("Áp dụng điều chỉnh âm thanh nổi, mọi phần tử mảng eqs nhân 0.8");
    if (isSongMode) {
      var eqs = (_b = (_a = mergedJson == null ? void 0 : mergedJson.aev2) == null ? void 0 : _a.eq) == null ? void 0 : _b.eqs;
      if (eqs && Array.isArray(eqs)) {
        var originalEqs = eqs.slice();
        mergedJson.aev2.eq.eqs = eqs.map(function(value) {
          return value * 0.8;
        });
        logger.logMergeRule(
          "Điều chỉnh âm thanh nổi",
          "aev2.eq.eqs nhân 0.8",
          { eqs: originalEqs },
          { eqs: mergedJson.aev2.eq.eqs }
        );
      }
    } else {
      var eqs = (_d = (_c = mergedJson == null ? void 0 : mergedJson.effect) == null ? void 0 : _c.eq) == null ? void 0 : _d.eqs;
      if (eqs && Array.isArray(eqs)) {
        var originalEqs = eqs.slice();
        mergedJson.effect.eq.eqs = eqs.map(function(value) {
          return value * 0.8;
        });
        logger.logMergeRule(
          "Điều chỉnh âm thanh nổi",
          "effect.eq.eqs nhân 0.8",
          { eqs: originalEqs },
          { eqs: mergedJson.effect.eq.eqs }
        );
      }
    }
  }
  function adjustCmpOnBasedOnPregain(mergedJson, isSongMode) {
    var _a, _b;
    if (isSongMode) {
      var cmp = (_a = mergedJson == null ? void 0 : mergedJson.aev2) == null ? void 0 : _a.cmp;
      if (cmp && typeof cmp.pregain === "number") {
        var originalOn = cmp.on;
        var newOn = cmp.pregain !== 0;
        if (originalOn !== newOn) {
          cmp.on = newOn;
          logger.logMergeRule(
            "Điều chỉnh trạng thái CMP",
            `pregain là ${cmp.pregain}, ${newOn ? "bật" : "tắt"} aev2.cmp`,
            { pregain: cmp.pregain, on: originalOn },
            { pregain: cmp.pregain, on: newOn }
          );
        }
      }
    } else {
      var cmp = (_b = mergedJson == null ? void 0 : mergedJson.effect) == null ? void 0 : _b.cmp;
      if (cmp && typeof cmp.pregain === "number") {
        var originalOn = cmp.on;
        var newOn = cmp.pregain !== 0;
        if (originalOn !== newOn) {
          cmp.on = newOn;
          logger.logMergeRule(
            "Điều chỉnh trạng thái CMP",
            `pregain là ${cmp.pregain}, ${newOn ? "bật" : "tắt"} effect.cmp`,
            { pregain: cmp.pregain, on: originalOn },
            { pregain: cmp.pregain, on: newOn }
          );
        }
      }
    }
  }
  function standardizeOutputStructure(mergedJson, isSongMode) {
    if (isSongMode) {
      return { aev2: mergedJson.aev2 };
    } else {
      if (mergedJson.effect) {
        logger.logMergeRule(
          "Chuẩn hóa cấu trúc",
          "Chuyển cấu trúc effect của baseJson sang cấu trúc aev2",
          { structure: "effect" },
          { structure: "aev2" }
        );
        return { aev2: mergedJson.effect };
      } else {
        logger.warn("Ở chế độ baseJson không tìm thấy trường effect, trả về cấu trúc aev2 rỗng");
        return { aev2: {} };
      }
    }
  }
  function processMergeRules(input) {
    logger.stepStart("Xử lý quy tắc hợp nhất", "Xử lý logic chồng diyJson theo quy tắc 2");
    var { baseJson, songJson, diyJson, supportStereo } = input;
    var mergedJson;
    var isSongMode = !!songJson;
    if (isSongMode) {
      mergedJson = JSON.parse(JSON.stringify(songJson));
      logger.info("Dùng songJson làm cơ sở hợp nhất");
    } else if (baseJson) {
      var baseKeys = Object.keys(baseJson);
      if (baseKeys.length === 1) {
        var baseKey = baseKeys[0];
        mergedJson = JSON.parse(JSON.stringify(baseJson[baseKey]));
        logger.info(`Dùng baseJson.${baseKey} làm cơ sở hợp nhất`);
      } else if (baseKeys.length > 1) {
        logger.error("baseJson chứa nhiều cấu hình hiệu ứng, không thể xác định dùng cái nào làm cơ sở hợp nhất", { baseKeys });
        mergedJson = {};
        return mergedJson;
      } else {
        mergedJson = {};
        logger.info("baseJson rỗng, dùng đối tượng rỗng làm cơ sở hợp nhất");
      }
    } else {
      mergedJson = {};
      logger.warn("Không có JSON cơ sở khả dụng, dùng đối tượng rỗng");
    }
    var enabledEffectsCount = countEnabledEffects(diyJson);
    logger.info(`Số lượng hiệu ứng tăng cường đang bật: ${enabledEffectsCount}`, Object.keys(diyJson));
    processBassAndTreble(mergedJson, diyJson, isSongMode);
    processVocal(mergedJson, diyJson, isSongMode);
    processVibe(mergedJson, diyJson, isSongMode);
    processLive(mergedJson, diyJson, isSongMode);
    adjustPregain(mergedJson, enabledEffectsCount, isSongMode);
    if (supportStereo) {
      applyStereoAdjustment(mergedJson, isSongMode);
    }
    adjustCmpOnBasedOnPregain(mergedJson, isSongMode);
    var standardizedJson = standardizeOutputStructure(mergedJson, isSongMode);
    logger.stepEnd("Xử lý quy tắc hợp nhất", { mergedJson: standardizedJson });
    return { mergedJson: standardizedJson };
  }

  // .verification/sound-effects/ai-master/index.ts
  function composeEffect(params) {
    logger.info("Bắt đầu xử lý hợp nhất hiệu ứng âm thanh", params);
    try {
      var validation = validateInputs(params);
      if (!validation.isValid) {
        logger.error("Xác thực đầu vào thất bại", validation.errors);
        return {
          mergedJson: {},
          usedDiyConfig: [],
          success: false,
          errorMsg: validation.errors.join("; "),
          isUseZoneEffect: false
        };
      }
      var { baseJson, songJson, diyJson, supportStereo } = params;
      logger.info("Xác thực đầu vào thành công, bắt đầu xử lý quy tắc hợp nhất");
      var baseEffect = null;
      if (typeof baseJson === "string") {
        baseEffect = baseJson.trim() === "" ? null : JSON.parse(baseJson);
      } else {
        baseEffect = baseJson;
      }
      var songEffect = null;
      if (typeof songJson === "string") {
        songEffect = songJson.trim() === "" ? null : JSON.parse(songJson);
      } else {
        songEffect = songJson;
      }
      var diyEffect = {};
      if (typeof diyJson === "string") {
        diyEffect = diyJson.trim() === "" ? {} : JSON.parse(diyJson);
      } else {
        diyEffect = diyJson;
      }
      var closingResult = processClosingRules({
        baseJson: baseEffect,
        songJson: songEffect,
        diyJson: diyEffect,
        supportStereo
      });
      logger.info("Hoàn tất xử lý quy tắc tắt", {
        originalDiyKeys: Object.keys(diyJson || {}),
        filteredDiyKeys: Object.keys(closingResult.filteredDiyJson),
        closedKeys: closingResult.closedKeys
      });
      var mergeResult = processMergeRules({
        baseJson: closingResult.baseJson,
        songJson: closingResult.songJson,
        diyJson: closingResult.filteredDiyJson,
        supportStereo
      });
      logger.info("Hoàn tất xử lý quy tắc hợp nhất", mergeResult);
      var usedDiyConfig = Object.keys(closingResult.filteredDiyJson);
      var isUseZoneEffect = checkZoneEffect(mergeResult.mergedJson);
      var result = {
        mergedJson: mergeResult.mergedJson,
        usedDiyConfig,
        success: true,
        errorMsg: "",
        isUseZoneEffect
      };
      logger.info("Hoàn tất xử lý hợp nhất hiệu ứng âm thanh", result);
      return result;
    } catch (error) {
      var errorMsg = error instanceof Error ? error.message : "Lỗi không xác định";
      logger.error("Bất thường khi xử lý hợp nhất hiệu ứng âm thanh", errorMsg);
      return {
        mergedJson: {},
        usedDiyConfig: [],
        success: false,
        errorMsg: `Xử lý hợp nhất hiệu ứng âm thanh thất bại: ${errorMsg}`,
        isUseZoneEffect: false
      };
    }
  }
  function checkZoneEffect(mergedJson) {
    var _a, _b;
    logger.info("Kiểm tra tình trạng sử dụng hiệu ứng không gian", mergedJson);
    try {
      var aev2 = mergedJson == null ? void 0 : mergedJson.aev2;
      if (!aev2) {
        logger.info("Không tìm thấy trường aev2, hiệu ứng không gian chưa được sử dụng");
        return false;
      }
      var isRvbOn = ((_a = aev2.rvb) == null ? void 0 : _a.on) === true;
      var isSeOn = ((_b = aev2.se) == null ? void 0 : _b.on) === true;
      var result = isRvbOn || isSeOn;
      logger.info("Kết quả kiểm tra hiệu ứng không gian", {
        "aev2.rvb.on": isRvbOn,
        "aev2.se.on": isSeOn,
        isUseZoneEffect: result
      });
      return result;
    } catch (error) {
      logger.error("Xảy ra lỗi khi kiểm tra hiệu ứng không gian", error);
      return false;
    }
  }
  return __toCommonJS(index_exports);
})();
