function originalConstruct(fn,args){while(args.length&&args[args.length-1]===undefined)args.pop();return Reflect.construct(fn,args);}
var global = (function(){return this})(); var factories = {}, cache = {};
factories[872]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r3 = a1;
        r1 = a5;
        r4 = a6;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: l, environment: r2
            _fun9232: for(var _fun9232_ip = 0; ; ) switch(_fun9232_ip) {
case 0:
                r2 = a0;
                r1 = ':';
                r2 = r2 + r1;
                r1 = a1;
                r2 = r2 + r1;
                r1 = _closure1_slot1;
                r1 = r1.MODEL64_TEMPLATE_DATA;
                r1 = r1[r2];
                r0 = null;
                if(!(r0 != r1)) { _fun9232_ip = 44; continue _fun9232 }
case 41:
                r0 = r1;
case 44:
                return r0;
            }
        };
        var _closure1_slot2 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: t, environment: r2
            _fun9233: for(var _fun9233_ip = 0; ; ) switch(_fun9233_ip) {
case 0:
                r0 = a0;
                r2 = null;
                r1 = undefined;
                if(!(r2 != r0)) { _fun9233_ip = 16; continue _fun9233 }
case 11:
                r1 = r0.on;
case 16:
                r2 = 'boolean';
                r1 = typeof r1;
                if(!(r2 === r1)) { _fun9233_ip = 34; continue _fun9233 }
case 27:
                r0 = r0.on;
                return r0;
case 34:
                r0 = global;
                r2 = r0.Error;
                r1 = a1;
                r0 = ' Bị thiếu hoặc không phải boolean';
                r3 = r1 + r0;
                r1 = r2.prototype;
                r1 = Object.create(r1, {constructor: {value: r2}});
                r4 = r1;
                r0 = originalConstruct(r2, [r3, r2]);
                r0 = r0 instanceof Object ? r0 : r1;
                throw r0;
            }
        };
        var _closure1_slot3 = r0;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: u, environment: r2
            _fun9234: for(var _fun9234_ip = 0; ; ) switch(_fun9234_ip) {
case 0:
                r0 = a0;
                r5 = undefined;
                r4 = _closure1_slot3;
                r3 = r0.rvb;
                r2 = 'template.rvb.on';
                r3 = r4.bind(r5)(r3, r2);
                r4 = r0.dly;
                r6 = null;
                r2 = undefined;
                if(!(r6 != r4)) { _fun9234_ip = 47; continue _fun9234 }
case 42:
                r2 = r4.on;
case 47:
                r4 = 'boolean';
                r2 = typeof r2;
                r2 = r4 === r2;
                if(!r2) { _fun9234_ip = 72; continue _fun9234 }
case 61:
                r4 = r0.dly;
                r2 = r4.on;
case 72:
                r4 = _closure1_slot3;
                r1 = r0.se;
                r0 = 'template.se.on';
                r1 = r4.bind(r5)(r1, r0);
                r0 = {};
                r0['reverb'] = r3;
                r0['delay'] = r2;
                r0['stereo_enhance'] = r1;
                return r0;
            }
        };
        var _closure1_slot4 = r0;
        r0 = global;
        r7 = r0.Object;
        r6 = r7.defineProperty;
        r5 = {};
        r0 = true;
        r5['value'] = r0;
        r0 = '__esModule';
        r0 = r6.bind(r7)(r1, r0, r5);
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9235: for(var _fun9235_ip = 0; ; ) switch(_fun9235_ip) {
case 0:
                r6 = a0;
                r4 = a1;
                r1 = _closure1_slot2;
                r3 = undefined;
                r1 = r1.bind(r3)(r6, r4);
                if(r1) { _fun9235_ip = 74; continue _fun9235 }
case 24:
                r2 = global;
                r5 = r2.Error;
                r2 = 'missing model64 template: ';
                r6 = r2 + r6;
                r2 = ':';
                r2 = r6 + r2;
                r8 = r2 + r4;
                r4 = r5.prototype;
                r4 = Object.create(r4, {constructor: {value: r5}});
                r9 = r4;
                r2 = originalConstruct(r5, [r8, r7]);
                r2 = r2 instanceof Object ? r2 : r4;
                throw r2;
case 74:
                r2 = _closure1_slot0;
                r2 = r2.deepClone;
                r2 = r2.bind(r3)(r1);
                r0 = _closure1_slot4;
                r1 = r0.bind(r3)(r1);
                r0 = {};
                r0['effectParams'] = r2;
                r0['moduleSwitches'] = r1;
                return r0;
            }
        };
        r1['resolveModel64Template'] = r0;
        r0 = 0;
        r1 = r4[r0];
        r0 = undefined;
        r1 = r3.bind(r0)(r1);
        var _closure1_slot0 = r1;
        r1 = 1;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot1 = r1;
        return r0;
    };
factories[873]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r5 = a1;
        r3 = a5;
        r6 = a6;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: u, environment: r2
            _fun9237: for(var _fun9237_ip = 0; ; ) switch(_fun9237_ip) {
case 0:
                r1 = a0;
                r6 = 'unknown';
                r5 = undefined;
                r2 = undefined;
                r3 = arguments.length;
                r0 = 1;
                r4 = r6;
                if(!(r3 > r0)) { _fun9237_ip = 39; continue _fun9237 }
case 24:
                r3 = arguments[r0];
                r4 = r6;
                if(!(r5 !== r3)) { _fun9237_ip = 39; continue _fun9237 }
case 35:
                r4 = arguments[r0];
case 39:
                r0 = global;
                r0 = r0.Error;
                r0 = r1 instanceof r0;
                if(!r0) { _fun9237_ip = 62; continue _fun9237 }
case 54:
                r0 = r1.message;
                if(r0) { _fun9237_ip = 95; continue _fun9237 }
case 62:
                r3 = 'string';
                r2 = typeof r1;
                r0 = r4;
                if(!(r3 === r2)) { _fun9237_ip = 93; continue _fun9237 }
case 76:
                r3 = r1.length;
                r2 = 0;
                r0 = r4;
                if(!(r3 > r2)) { _fun9237_ip = 93; continue _fun9237 }
case 90:
                r0 = r1;
case 93:
                _fun9237_ip = 100; continue _fun9237;
case 95:
                r0 = r1.message;
case 100:
                return r0;
            }
        };
        var _closure1_slot2 = r1;
        r0 = 0;
        r4 = r6[r0];
        r0 = undefined;
        r4 = r5.bind(r0)(r4);
        r7 = global;
        r10 = r7.Object;
        r9 = r10.defineProperty;
        r8 = {};
        r7 = true;
        r8['value'] = r7;
        r7 = '__esModule';
        r7 = r9.bind(r10)(r3, r7, r8);
        r3['RANGE_EPSILON'] = r0;
        r7 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9238: for(var _fun9238_ip = 0; ; ) switch(_fun9238_ip) {
case 0:
                r0 = a0;
                r2 = 'number';
                r1 = typeof r0;
                if(!(r2 === r1)) { _fun9238_ip = 38; continue _fun9238 }
case 14:
                r1 = global;
                r2 = r1.Number;
                r1 = r2.isFinite;
                r1 = r1.bind(r2)(r0);
                if(!r1) { _fun9238_ip = 38; continue _fun9238 }
case 36:
                return r0;
case 38:
                r0 = global;
                r2 = r0.Error;
                r1 = a1;
                r0 = ' Phải là giá trị hữu hạn';
                r3 = r1 + r0;
                r1 = r2.prototype;
                r1 = Object.create(r1, {constructor: {value: r2}});
                r4 = r1;
                r0 = originalConstruct(r2, [r3, r2]);
                r0 = r0 instanceof Object ? r0 : r1;
                throw r0;
            }
        };
        r3['assertFiniteNumber'] = r7;
        r7 = function(a0, a1, a2) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            r0 = global;
            r3 = r0.Math;
            r2 = r3.min;
            r5 = r0.Math;
            r4 = r5.max;
            r1 = a0;
            r0 = a1;
            r1 = r4.bind(r5)(r1, r0);
            r0 = a2;
            r0 = r2.bind(r3)(r1, r0);
            return r0;
        };
        r3['clamp'] = r7;
        r7 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            r0 = global;
            r2 = r0.JSON;
            r1 = r2.parse;
            r4 = r0.JSON;
            r3 = r4.stringify;
            r0 = a0;
            r0 = r3.bind(r4)(r0);
            r0 = r1.bind(r2)(r0);
            return r0;
        };
        r3['deepClone'] = r7;
        r7 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: n, environment: r2
            _fun9241: for(var _fun9241_ip = 0; ; ) switch(_fun9241_ip) {
case 0:
                r4 = a0;
                r1 = a1;
                r2 = null;
                r0 = undefined;
                r13 = undefined;
                r14 = undefined;
                r7 = undefined;
                r5 = undefined;
                r15 = undefined;
                r16 = undefined;
                if(!(r4 !== r1)) { _fun9241_ip = 436; continue _fun9241 }
case 29:
                if(!(r2 != r4)) { _fun9241_ip = 404; continue _fun9241 }
case 36:
                if(!(r2 != r1)) { _fun9241_ip = 404; continue _fun9241 }
case 43:
                r8 = 'object';
                r3 = typeof r4;
                if(!(r8 === r3)) { _fun9241_ip = 404; continue _fun9241 }
case 57:
                r3 = typeof r1;
                if(!(r8 === r3)) { _fun9241_ip = 404; continue _fun9241 }
case 67:
                r3 = global;
                r11 = r3.Array;
                r8 = r11.isArray;
                r8 = r8.bind(r11)(r4);
                if(!r8) { _fun9241_ip = 110; continue _fun9241 }
case 88:
                r11 = r3.Array;
                r8 = r11.isArray;
                r8 = r8.bind(r11)(r1);
                if(r8) { _fun9241_ip = 312; continue _fun9241 }
case 110:
                r13 = r4;
                r14 = r1;
                r17 = r3.Set;
                r19 = new Array(0);
                r18 = r19.concat;
                r8 = _closure1_slot0;
                r11 = r8.default;
                r20 = r3.Object;
                r8 = r20.keys;
                r8 = r8.bind(r20)(r4);
                r11 = r11.bind(r0)(r8);
                r8 = _closure1_slot0;
                r20 = r8.default;
                r21 = r3.Object;
                r8 = r21.keys;
                r8 = r8.bind(r21)(r1);
                r8 = r20.bind(r0)(r8);
                r23 = r18.bind(r19)(r11, r8);
                r11 = r17.prototype;
                r11 = Object.create(r11, {constructor: {value: r17}});
                r24 = r11;
                r8 = originalConstruct(r17, [r23, r22]);
                r17 = r8 instanceof Object ? r8 : r11;
                r7 = {};
                r5 = false;
                r8 = r17;
                var __iterator_values_1=Array.from(r8), __iterator_index_1=0; r11={return:function(){}}; r8=function(){if(__iterator_index_1>=__iterator_values_1.length){r11=undefined;return {value:undefined}}return {value:__iterator_values_1[__iterator_index_1++]}};
case 230:
                r20 = r8().value;
                r17 = r11;
                if(!(r17 !== r0)) { _fun9241_ip = 299; continue _fun9241 }
case 241: // try_start_0
                r15 = r20;
                r19 = _closure1_slot3;
                r17 = r13;
                r18 = r17[r20];
                r17 = r14;
                r17 = r17[r20];
                r17 = r19.bind(r0)(r18, r17);
                r16 = r17;
                if(!(r0 !== r17)) { _fun9241_ip = 290; continue _fun9241 }
case 275:
                r19 = r7;
                r18 = r15;
                r17 = r16;
                r19[r18] = r17;
                r5 = true;
case 290: // try_end0
                _fun9241_ip = 230; continue _fun9241;
case 292: // catch_target0
                CatchBlockStart(arg_register=8);
                r11.return();
                throw r8;
case 299:
                r8 = r5;
                r5 = undefined;
                if(!r8) { _fun9241_ip = 310; continue _fun9241 }
case 307:
                r5 = r7;
case 310:
                return r5;
case 312:
                r8 = r3.Math;
                r7 = r8.max;
                r5 = r4.length;
                r3 = r1.length;
                r8 = r7.bind(r8)(r5, r3);
                r5 = new Array(0);
                r3 = false;
                r6 = false;
                r10 = 0;
                if(!(r10 < r8)) { _fun9241_ip = 394; continue _fun9241 }
case 356:
                r13 = _closure1_slot3;
                r12 = r4[r10];
                r11 = r1[r10];
                r11 = r13.bind(r0)(r12, r11);
                if(!(r0 !== r11)) { _fun9241_ip = 384; continue _fun9241 }
case 378:
                r5[r10] = r11;
                r3 = true;
case 384:
                r10 = r10 + 1;
                r6 = r3;
                if(r10 < r8) { _fun9241_ip = 356; continue _fun9241 }
case 394:
                r3 = undefined;
                if(!r6) { _fun9241_ip = 402; continue _fun9241 }
case 399:
                r3 = r5;
case 402:
                return r3;
case 404:
                r3 = null;
                if(!(r2 != r4)) { _fun9241_ip = 413; continue _fun9241 }
case 410:
                r3 = r4;
case 413:
                r2 = null;
                if(!(r2 != r1)) { _fun9241_ip = 422; continue _fun9241 }
case 419:
                r2 = r1;
case 422:
                r1 = {};
                r1['_from'] = r3;
                r1['_to'] = r2;
                return r1;
case 436:
                return r0;
            }
        };
        var _closure1_slot3 = r7;
        r3['deepDiff'] = r7;
        r7 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9242: for(var _fun9242_ip = 0; ; ) switch(_fun9242_ip) {
case 0:
                r1 = a0;
                r3 = a1;
                r4 = 'number';
                r2 = typeof r1;
                r0 = r3;
                if(!(r4 === r2)) { _fun9242_ip = 48; continue _fun9242 }
case 20:
                r2 = global;
                r4 = r2.Number;
                r2 = r4.isFinite;
                r2 = r2.bind(r4)(r1);
                r0 = r3;
                if(!r2) { _fun9242_ip = 48; continue _fun9242 }
case 45:
                r0 = r1;
case 48:
                return r0;
            }
        };
        r3['finiteOr'] = r7;
        r7 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            r0 = global;
            r2 = r0.Math;
            r1 = r2.abs;
            r3 = a0;
            r0 = a1;
            r0 = r3 - r0;
            r1 = r1.bind(r2)(r0);
            r0 = _closure1_slot1;
            r0 = r1 < r0;
            return r0;
        };
        r3['floatEqual'] = r7;
        r7 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            r1 = _closure1_slot1;
            r0 = a1;
            r1 = r0 + r1;
            r0 = a0;
            r0 = r0 > r1;
            return r0;
        };
        r3['floatGt'] = r7;
        r7 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            r1 = _closure1_slot1;
            r0 = a1;
            r1 = r0 - r1;
            r0 = a0;
            r0 = r0 >= r1;
            return r0;
        };
        r3['floatGte'] = r7;
        r7 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            r0 = global;
            r2 = r0.Math;
            r1 = r2.abs;
            r0 = a0;
            r1 = r1.bind(r2)(r0);
            r0 = _closure1_slot1;
            r0 = r1 < r0;
            return r0;
        };
        r3['floatIsZero'] = r7;
        r7 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            r1 = _closure1_slot1;
            r0 = a1;
            r1 = r0 - r1;
            r0 = a0;
            r0 = r0 < r1;
            return r0;
        };
        r3['floatLt'] = r7;
        r7 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9248: for(var _fun9248_ip = 0; ; ) switch(_fun9248_ip) {
case 0:
                r5 = 'unknown';
                r4 = undefined;
                r1 = undefined;
                r2 = arguments.length;
                r0 = 2;
                r3 = r5;
                if(!(r2 > r0)) { _fun9248_ip = 36; continue _fun9248 }
case 21:
                r2 = arguments[r0];
                r3 = r5;
                if(!(r4 !== r2)) { _fun9248_ip = 36; continue _fun9248 }
case 32:
                r3 = arguments[r0];
case 36:
                r1 = a0;
                r0 = ': ';
                r1 = r1 + r0;
                r2 = _closure1_slot2;
                r0 = a1;
                r0 = r2.bind(r4)(r0, r3);
                r0 = r1 + r0;
                return r0;
            }
        };
        r3['formatStepError'] = r7;
        r3['getErrorText'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            r3 = a0;
            r2 = r3.reduce;
            r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r0
                r1 = a0;
                r0 = a1;
                r0 = r1 * r0;
                return r0;
            };
            r0 = 1;
            r0 = r2.bind(r3)(r1, r0);
            return r0;
        };
        r3['getTensorSize'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9251: for(var _fun9251_ip = 0; ; ) switch(_fun9251_ip) {
case 0:
                r2 = a0;
                r1 = 'number';
                r0 = typeof r2;
                r0 = r1 === r0;
                if(!r0) { _fun9251_ip = 36; continue _fun9251 }
case 17:
                r1 = global;
                r3 = r1.Number;
                r1 = r3.isInteger;
                r0 = r1.bind(r3)(r2);
case 36:
                if(!r0) { _fun9251_ip = 45; continue _fun9251 }
case 39:
                r1 = 0;
                r0 = r2 > r1;
case 45:
                return r0;
            }
        };
        r3['isPositiveInteger'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9252: for(var _fun9252_ip = 0; ; ) switch(_fun9252_ip) {
case 0:
                r1 = a0;
                r4 = 'number';
                r2 = typeof r1;
                r0 = 0;
                if(!(r4 === r2)) { _fun9252_ip = 43; continue _fun9252 }
case 16:
                r2 = global;
                r4 = r2.Number;
                r2 = r4.isFinite;
                r2 = r2.bind(r4)(r1);
                r0 = 0;
                if(!r2) { _fun9252_ip = 43; continue _fun9252 }
case 40:
                r0 = r1;
case 43:
                return r0;
            }
        };
        r3['optionalFiniteNumber'] = r1;
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9253: for(var _fun9253_ip = 0; ; ) switch(_fun9253_ip) {
case 0:
                r0 = a0;
                r1 = null;
                if(!(r1 != r0)) { _fun9253_ip = 11; continue _fun9253 }
case 9:
                return r0;
case 11:
                r0 = global;
                r2 = r0.Error;
                r1 = a1;
                r0 = ' Bị thiếu';
                r3 = r1 + r0;
                r1 = r2.prototype;
                r1 = Object.create(r1, {constructor: {value: r2}});
                r4 = r1;
                r0 = originalConstruct(r2, [r3, r2]);
                r0 = r0 instanceof Object ? r0 : r1;
                throw r0;
            }
        };
        r3['requireField'] = r1;
        r1 = 1;
        r1 = r6[r1];
        r1 = r5.bind(r0)(r1);
        r1 = r4.bind(r0)(r1);
        var _closure1_slot0 = r1;
        r1 = 0.0001;
        r3['RANGE_EPSILON'] = r1;
        var _closure1_slot1 = r1;
        return r0;
    };
factories[874]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r67 = a1;
        r2 = a5;
        r68 = a6;
        r0 = 0;
        r1 = r68[r0];
        r0 = undefined;
        r66 = r67.bind(r0)(r1);
        r1 = global;
        r5 = r1.Object;
        r4 = r5.defineProperty;
        r3 = {};
        r1 = true;
        r3['value'] = r1;
        r1 = '__esModule';
        r1 = r4.bind(r5)(r2, r1, r3);
        r2['MODEL64_TEMPLATE_DATA'] = r0;
        r1 = 1;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r65 = r66.bind(r0)(r1);
        r1 = 2;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r64 = r66.bind(r0)(r1);
        r1 = 3;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r63 = r66.bind(r0)(r1);
        r1 = 4;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r62 = r66.bind(r0)(r1);
        r1 = 5;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r61 = r66.bind(r0)(r1);
        r1 = 6;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r60 = r66.bind(r0)(r1);
        r1 = 7;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r59 = r66.bind(r0)(r1);
        r1 = 8;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r58 = r66.bind(r0)(r1);
        r1 = 9;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r57 = r66.bind(r0)(r1);
        r1 = 10;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r56 = r66.bind(r0)(r1);
        r1 = 11;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r55 = r66.bind(r0)(r1);
        r1 = 12;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r54 = r66.bind(r0)(r1);
        r1 = 13;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r53 = r66.bind(r0)(r1);
        r1 = 14;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r52 = r66.bind(r0)(r1);
        r1 = 15;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r51 = r66.bind(r0)(r1);
        r1 = 16;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r50 = r66.bind(r0)(r1);
        r1 = 17;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r49 = r66.bind(r0)(r1);
        r1 = 18;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r48 = r66.bind(r0)(r1);
        r1 = 19;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r47 = r66.bind(r0)(r1);
        r1 = 20;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r46 = r66.bind(r0)(r1);
        r1 = 21;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r45 = r66.bind(r0)(r1);
        r1 = 22;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r44 = r66.bind(r0)(r1);
        r1 = 23;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r43 = r66.bind(r0)(r1);
        r1 = 24;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r42 = r66.bind(r0)(r1);
        r1 = 25;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r41 = r66.bind(r0)(r1);
        r1 = 26;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r40 = r66.bind(r0)(r1);
        r1 = 27;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r39 = r66.bind(r0)(r1);
        r1 = 28;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r38 = r66.bind(r0)(r1);
        r1 = 29;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r37 = r66.bind(r0)(r1);
        r1 = 30;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r36 = r66.bind(r0)(r1);
        r1 = 31;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r35 = r66.bind(r0)(r1);
        r1 = 32;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r34 = r66.bind(r0)(r1);
        r1 = 33;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r33 = r66.bind(r0)(r1);
        r1 = 34;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r32 = r66.bind(r0)(r1);
        r1 = 35;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r31 = r66.bind(r0)(r1);
        r1 = 36;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r30 = r66.bind(r0)(r1);
        r1 = 37;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r29 = r66.bind(r0)(r1);
        r1 = 38;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r28 = r66.bind(r0)(r1);
        r1 = 39;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r27 = r66.bind(r0)(r1);
        r1 = 40;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r26 = r66.bind(r0)(r1);
        r1 = 41;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r25 = r66.bind(r0)(r1);
        r1 = 42;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r24 = r66.bind(r0)(r1);
        r1 = 43;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r23 = r66.bind(r0)(r1);
        r1 = 44;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r22 = r66.bind(r0)(r1);
        r1 = 45;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r21 = r66.bind(r0)(r1);
        r1 = 46;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r20 = r66.bind(r0)(r1);
        r1 = 47;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r19 = r66.bind(r0)(r1);
        r1 = 48;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r18 = r66.bind(r0)(r1);
        r1 = 49;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r17 = r66.bind(r0)(r1);
        r1 = 50;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r16 = r66.bind(r0)(r1);
        r1 = 51;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r15 = r66.bind(r0)(r1);
        r1 = 52;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r14 = r66.bind(r0)(r1);
        r1 = 53;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r13 = r66.bind(r0)(r1);
        r1 = 54;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r12 = r66.bind(r0)(r1);
        r1 = 55;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r11 = r66.bind(r0)(r1);
        r1 = 56;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r10 = r66.bind(r0)(r1);
        r1 = 57;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r9 = r66.bind(r0)(r1);
        r1 = 58;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r8 = r66.bind(r0)(r1);
        r1 = 59;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r7 = r66.bind(r0)(r1);
        r1 = 60;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r6 = r66.bind(r0)(r1);
        r1 = 61;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r5 = r66.bind(r0)(r1);
        r1 = 62;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r4 = r66.bind(r0)(r1);
        r1 = 63;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r3 = r66.bind(r0)(r1);
        r1 = 64;
        r1 = r68[r1];
        r1 = r67.bind(r0)(r1);
        r1 = r66.bind(r0)(r1);
        r66 = r65.default;
        r65 = r64.default;
        r64 = r63.default;
        r63 = r62.default;
        r62 = r61.default;
        r61 = r60.default;
        r60 = r59.default;
        r59 = r58.default;
        r58 = r57.default;
        r57 = r56.default;
        r56 = r55.default;
        r55 = r54.default;
        r54 = r53.default;
        r53 = r52.default;
        r52 = r51.default;
        r51 = r50.default;
        r50 = r49.default;
        r49 = r48.default;
        r48 = r47.default;
        r47 = r46.default;
        r46 = r45.default;
        r45 = r44.default;
        r44 = r43.default;
        r43 = r42.default;
        r42 = r41.default;
        r41 = r40.default;
        r40 = r39.default;
        r39 = r38.default;
        r38 = r37.default;
        r37 = r36.default;
        r36 = r35.default;
        r35 = r34.default;
        r34 = r33.default;
        r33 = r32.default;
        r32 = r31.default;
        r31 = r30.default;
        r30 = r29.default;
        r29 = r28.default;
        r28 = r27.default;
        r27 = r26.default;
        r26 = r25.default;
        r25 = r24.default;
        r24 = r23.default;
        r23 = r22.default;
        r22 = r21.default;
        r21 = r20.default;
        r20 = r19.default;
        r19 = r18.default;
        r18 = r17.default;
        r17 = r16.default;
        r16 = r15.default;
        r15 = r14.default;
        r14 = r13.default;
        r13 = r12.default;
        r12 = r11.default;
        r11 = r10.default;
        r10 = r9.default;
        r9 = r8.default;
        r8 = r7.default;
        r7 = r6.default;
        r6 = r5.default;
        r5 = r4.default;
        r4 = r3.default;
        r3 = r1.default;
        r1 = {};
        r1['bitter:folk-family'] = r66;
        r1['bitter:instrumental-family'] = r65;
        r1['bitter:pop-family'] = r64;
        r1['bitter:rhythm-family'] = r63;
        r1['calm:folk-family'] = r62;
        r1['calm:instrumental-family'] = r61;
        r1['calm:pop-family'] = r60;
        r1['calm:rhythm-family'] = r59;
        r1['confident:folk-family'] = r58;
        r1['confident:instrumental-family'] = r57;
        r1['confident:pop-family'] = r56;
        r1['confident:rhythm-family'] = r55;
        r1['default:folk-family'] = r54;
        r1['default:instrumental-family'] = r53;
        r1['default:pop-family'] = r52;
        r1['default:rhythm-family'] = r51;
        r1['depressed:folk-family'] = r50;
        r1['depressed:instrumental-family'] = r49;
        r1['depressed:pop-family'] = r48;
        r1['depressed:rhythm-family'] = r47;
        r1['ecstatic:folk-family'] = r46;
        r1['ecstatic:instrumental-family'] = r45;
        r1['ecstatic:pop-family'] = r44;
        r1['ecstatic:rhythm-family'] = r43;
        r1['energetic:folk-family'] = r42;
        r1['energetic:instrumental-family'] = r41;
        r1['energetic:pop-family'] = r40;
        r1['energetic:rhythm-family'] = r39;
        r1['healing:folk-family'] = r38;
        r1['healing:instrumental-family'] = r37;
        r1['healing:pop-family'] = r36;
        r1['healing:rhythm-family'] = r35;
        r1['joyful:folk-family'] = r34;
        r1['joyful:instrumental-family'] = r33;
        r1['joyful:pop-family'] = r32;
        r1['joyful:rhythm-family'] = r31;
        r1['lonely:folk-family'] = r30;
        r1['lonely:instrumental-family'] = r29;
        r1['lonely:pop-family'] = r28;
        r1['lonely:rhythm-family'] = r27;
        r1['nostalgic:folk-family'] = r26;
        r1['nostalgic:instrumental-family'] = r25;
        r1['nostalgic:pop-family'] = r24;
        r1['nostalgic:rhythm-family'] = r23;
        r1['passionate:folk-family'] = r22;
        r1['passionate:instrumental-family'] = r21;
        r1['passionate:pop-family'] = r20;
        r1['passionate:rhythm-family'] = r19;
        r1['relaxed:folk-family'] = r18;
        r1['relaxed:instrumental-family'] = r17;
        r1['relaxed:pop-family'] = r16;
        r1['relaxed:rhythm-family'] = r15;
        r1['romantic:folk-family'] = r14;
        r1['romantic:instrumental-family'] = r13;
        r1['romantic:pop-family'] = r12;
        r1['romantic:rhythm-family'] = r11;
        r1['sad:folk-family'] = r10;
        r1['sad:instrumental-family'] = r9;
        r1['sad:pop-family'] = r8;
        r1['sad:rhythm-family'] = r7;
        r1['sweet:folk-family'] = r6;
        r1['sweet:instrumental-family'] = r5;
        r1['sweet:pop-family'] = r4;
        r1['sweet:rhythm-family'] = r3;
        r2['MODEL64_TEMPLATE_DATA'] = r1;
        return r0;
    };
factories[875]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r11 = {};
        r1 = false;
        r11['on'] = r1;
        r0 = [0.4, 0.4, 0.8, 1.3, 0.8, 0];
        r2 = -0.3;
        r0[6] = r2;
        r2 = -0.8;
        r0[7] = r2;
        r3 = -1.5;
        r0[8] = r3;
        r0[9] = r2;
        r10 = {};
        r2 = true;
        r10['on'] = r2;
        r10['eqs'] = r0;
        r0 = new Array(9);
        r2 = {'band': 0, 'type': 6, 'gain': 1.8, 'freq': 400, 'q': 0.5, 'on': true};
        r0[0] = r2;
        r2 = {'band': 1, 'type': 6, 'gain': 1, 'freq': 2500, 'q': 1, 'on': true};
        r0[1] = r2;
        r2 = {'band': 2, 'type': 6, 'gain': 1, 'freq': 750, 'q': 0.7, 'on': true};
        r0[2] = r2;
        r2 = {'band': 3, 'type': 5, 'gain': -0.8, 'freq': 4500, 'q': 1, 'on': true};
        r0[3] = r2;
        r2 = {'on': false, 'band': 4};
        r0[4] = r2;
        r2 = {'on': false, 'band': 5};
        r0[5] = r2;
        r2 = {'on': false, 'band': 6};
        r0[6] = r2;
        r2 = {'on': false, 'band': 7};
        r0[7] = r2;
        r2 = {'on': false, 'band': 8};
        r0[8] = r2;
        r9 = {'on': true, 'gain': -1};
        r9['f'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['cmp'] = r11;
        r1['eq'] = r10;
        r1['peq'] = r9;
        r1['rvb'] = r8;
        r1['limiter'] = r7;
        r7 = {'on': true, 'presence': 0, 'stereoizer': 1.8, 'sshaper': true, 'ambience': 0};
        r1['se'] = r7;
        r1['bt'] = r6;
        r1['rotate'] = r5;
        r5 = {'on': true, 'upmix': 1, 'lronoff': 0, 'lrangle': 10, 'otheronoff': 0, 'global': -0.1, 'center': 0.8, 'lfe': 0, 'fl': -0.3, 'fr': -0.3, 'left': 0, 'right': 0, 'rl': 0, 'rr': 0, 'ul': 0, 'ur': 0};
        r1['spspatial'] = r5;
        r1['alc'] = r4;
        r1['chorus'] = r3;
        r1['flanger'] = r2;
        r1['dly'] = r0;
        r0 = 'pop tình sầu';
        r1['effect_name'] = r0;
        r0 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'alc'];
        r1['order'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[876]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r11 = {};
        r1 = false;
        r11['on'] = r1;
        r0 = [0.5, 0.5, 1, 1.5, 0.8, 0];
        r2 = -0.6;
        r0[6] = r2;
        r3 = -0.8;
        r0[7] = r3;
        r3 = -1.4;
        r0[8] = r3;
        r0[9] = r2;
        r10 = {};
        r2 = true;
        r10['on'] = r2;
        r10['eqs'] = r0;
        r0 = new Array(9);
        r2 = {'band': 0, 'type': 6, 'gain': 0.5, 'freq': 420, 'q': 1, 'on': true};
        r0[0] = r2;
        r2 = {'band': 1, 'type': 6, 'gain': 1.5, 'freq': 1800, 'q': 0.7, 'on': true};
        r0[1] = r2;
        r2 = {'band': 2, 'type': 5, 'gain': -0.7, 'freq': 3500, 'q': 0.6, 'on': true};
        r0[2] = r2;
        r2 = {'on': false, 'band': 3};
        r0[3] = r2;
        r2 = {'on': false, 'band': 4};
        r0[4] = r2;
        r2 = {'on': false, 'band': 5};
        r0[5] = r2;
        r2 = {'on': false, 'band': 6};
        r0[6] = r2;
        r2 = {'on': false, 'band': 7};
        r0[7] = r2;
        r2 = {'on': false, 'band': 8};
        r0[8] = r2;
        r9 = {'on': true, 'gain': -0.5};
        r9['f'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['cmp'] = r11;
        r1['eq'] = r10;
        r1['peq'] = r9;
        r1['rvb'] = r8;
        r1['limiter'] = r7;
        r7 = {'on': true, 'presence': 0, 'stereoizer': 2.8, 'sshaper': true, 'ambience': 0};
        r1['se'] = r7;
        r1['bt'] = r6;
        r1['rotate'] = r5;
        r5 = {'on': true, 'upmix': 1, 'lronoff': 0, 'lrangle': 10, 'otheronoff': 0, 'global': 0, 'center': 0.3, 'lfe': 0, 'fl': -0.2, 'fr': -0.2, 'left': 0, 'right': 0, 'rl': 0, 'rr': 0, 'ul': 0, 'ur': 0};
        r1['spspatial'] = r5;
        r1['alc'] = r4;
        r1['chorus'] = r3;
        r1['flanger'] = r2;
        r1['dly'] = r0;
        r0 = 'nhạc không lời tình sầu';
        r1['effect_name'] = r0;
        r0 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'alc'];
        r1['order'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[877]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r11 = {};
        r1 = false;
        r11['on'] = r1;
        r0 = [0.6, 0.6, 1.1, 1.6, 0.6, 0];
        r2 = -0.4;
        r0[6] = r2;
        r2 = -1;
        r0[7] = r2;
        r3 = -2;
        r0[8] = r3;
        r0[9] = r2;
        r10 = {'eqs': null, 'on': true, 'useable': true};
        r10['eqs'] = r0;
        r0 = new Array(9);
        r2 = {'band': 0, 'freq': 450, 'gain': 2, 'on': true, 'q': 0.6, 'type': 6};
        r0[0] = r2;
        r2 = {'band': 1, 'freq': 2200, 'gain': 0.8, 'on': true, 'q': 1.2, 'type': 6};
        r0[1] = r2;
        r2 = {'band': 2, 'freq': 800, 'gain': 1.2, 'on': true, 'q': 0.8, 'type': 6};
        r0[2] = r2;
        r2 = {'band': 3, 'freq': 4000, 'gain': -1, 'on': true, 'q': 1, 'type': 5};
        r0[3] = r2;
        r2 = {'on': false, 'band': 4};
        r0[4] = r2;
        r2 = {'on': false, 'band': 5};
        r0[5] = r2;
        r2 = {'on': false, 'band': 6};
        r0[6] = r2;
        r2 = {'on': false, 'band': 7};
        r0[7] = r2;
        r2 = {'on': false, 'band': 8};
        r0[8] = r2;
        r9 = {'f': null, 'gain': -1.25, 'on': true, 'useable': true};
        r9['f'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['cmp'] = r11;
        r1['eq'] = r10;
        r1['peq'] = r9;
        r1['rvb'] = r8;
        r1['limiter'] = r7;
        r7 = {'ambience': 0, 'on': true, 'presence': 0, 'sshaper': true, 'stereoizer': 1.5, 'useable': true};
        r1['se'] = r7;
        r1['bt'] = r6;
        r1['rotate'] = r5;
        r5 = {'center': 1, 'fl': -0.4, 'fr': -0.4, 'global': -0.1, 'left': 0, 'lfe': 0, 'lrangle': 10, 'lronoff': 0, 'on': true, 'otheronoff': 0, 'right': 0, 'rl': 0, 'rr': 0, 'ul': 0, 'upmix': 1, 'ur': 0, 'useable': true};
        r1['spspatial'] = r5;
        r1['alc'] = r4;
        r1['chorus'] = r3;
        r1['flanger'] = r2;
        r1['dly'] = r0;
        r0 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'alc'];
        r1['order'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[878]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r11 = {};
        r1 = false;
        r11['on'] = r1;
        r0 = [0.8, 0.8, 0.8, 1.2, 0.4, 0];
        r2 = -0.5;
        r0[6] = r2;
        r2 = -1;
        r0[7] = r2;
        r2 = -1.8;
        r0[8] = r2;
        r2 = -0.8;
        r0[9] = r2;
        r10 = {};
        r2 = true;
        r10['on'] = r2;
        r10['eqs'] = r0;
        r0 = new Array(9);
        r2 = {'band': 0, 'type': 6, 'gain': 1.5, 'freq': 500, 'q': 0.7, 'on': true};
        r0[0] = r2;
        r2 = {'band': 1, 'type': 6, 'gain': 0.6, 'freq': 2000, 'q': 1.5, 'on': true};
        r0[1] = r2;
        r2 = {'band': 2, 'type': 6, 'gain': 1, 'freq': 850, 'q': 0.9, 'on': true};
        r0[2] = r2;
        r2 = {'band': 3, 'type': 5, 'gain': -1, 'freq': 3500, 'q': 1, 'on': true};
        r0[3] = r2;
        r2 = {'on': false, 'band': 4};
        r0[4] = r2;
        r2 = {'on': false, 'band': 5};
        r0[5] = r2;
        r2 = {'on': false, 'band': 6};
        r0[6] = r2;
        r2 = {'on': false, 'band': 7};
        r0[7] = r2;
        r2 = {'on': false, 'band': 8};
        r0[8] = r2;
        r9 = {'on': true, 'gain': -1};
        r9['f'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['cmp'] = r11;
        r1['eq'] = r10;
        r1['peq'] = r9;
        r1['rvb'] = r8;
        r1['limiter'] = r7;
        r7 = {'on': true, 'presence': 0, 'stereoizer': 1, 'sshaper': true, 'ambience': 0};
        r1['se'] = r7;
        r1['bt'] = r6;
        r1['rotate'] = r5;
        r5 = {'on': true, 'upmix': 1, 'lronoff': 0, 'lrangle': 10, 'otheronoff': 0, 'global': -0.1, 'center': 1, 'lfe': 0, 'fl': -0.3, 'fr': -0.3, 'left': 0, 'right': 0, 'rl': 0, 'rr': 0, 'ul': 0, 'ur': 0};
        r1['spspatial'] = r5;
        r1['alc'] = r4;
        r1['chorus'] = r3;
        r1['flanger'] = r2;
        r1['dly'] = r0;
        r0 = 'nhịp điệu tình sầu';
        r1['effect_name'] = r0;
        r0 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'alc'];
        r1['order'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[879]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(9);
        r2 = {'gain': 1.6, 'freq': 90, 'band': 0, 'type': 6, 'on': true, 'q': 4.5};
        r1[0] = r2;
        r2 = {'gain': -1.5, 'freq': 480, 'band': 1, 'type': 6, 'on': true, 'q': 2};
        r1[1] = r2;
        r2 = {'gain': 0.5, 'freq': 3500, 'band': 2, 'type': 6, 'on': true, 'q': 2};
        r1[2] = r2;
        r2 = {'gain': -0.4, 'freq': 1500, 'band': 3, 'type': 6, 'on': true, 'q': 2};
        r1[3] = r2;
        r2 = {'gain': 0.1, 'freq': 9000, 'band': 4, 'type': 5, 'on': true, 'q': 0.1};
        r1[4] = r2;
        r2 = {'gain': 0.5, 'freq': 6000, 'band': 5, 'type': 6, 'on': true, 'q': 1.5};
        r1[5] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[6] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[7] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[8] = r2;
        r3 = {'f': null, 'gain': 0, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r14 = {};
        r14['on'] = r0;
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 2, 'gain': -8, 'freq': 700, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 2, 'gain': -8, 'freq': 7000, 'curve': 2, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 0, 'band': 3, 'q': 0.5, 'gain': -4, 'freq': 8000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r13 = {};
        r13['f'] = r0;
        r1 = true;
        r13['on'] = r1;
        r0 = {};
        r15 = {'pdelay': 80, 'hfdamping': 0.5, 'rshape': 100, 'density': 50, 'swidth': 160, 'q': 10, 'diffusion': 80, 'on': true, 'dtime': 2};
        r0['rvb'] = r15;
        r0['on'] = r1;
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': 0};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -30, 'rvb': -27};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Trung tính';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 0, 'sshaper': true, 'ambience': 5, 'stereoizer': 0.2, 'on': true};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[880]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(9);
        r2 = {'gain': 1.6, 'freq': 90, 'band': 0, 'type': 6, 'on': true, 'q': 4.5};
        r1[0] = r2;
        r2 = {'gain': -1.5, 'freq': 480, 'band': 1, 'type': 6, 'on': true, 'q': 2};
        r1[1] = r2;
        r2 = {'gain': 0.8, 'freq': 3500, 'band': 2, 'type': 6, 'on': true, 'q': 2};
        r1[2] = r2;
        r2 = {'gain': -0.4, 'freq': 1500, 'band': 3, 'type': 6, 'on': true, 'q': 2};
        r1[3] = r2;
        r2 = {'gain': 0.1, 'freq': 9000, 'band': 4, 'type': 5, 'on': true, 'q': 0.1};
        r1[4] = r2;
        r2 = {'gain': 0.5, 'freq': 6000, 'band': 5, 'type': 6, 'on': true, 'q': 1.5};
        r1[5] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[6] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[7] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[8] = r2;
        r3 = {'f': null, 'gain': 0, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r14 = {};
        r14['on'] = r0;
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 2, 'gain': -8, 'freq': 700, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 2, 'gain': -8, 'freq': 7000, 'curve': 2, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 0, 'band': 3, 'q': 0.5, 'gain': -4, 'freq': 8000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r13 = {};
        r13['f'] = r0;
        r1 = true;
        r13['on'] = r1;
        r0 = {};
        r15 = {'pdelay': 80, 'hfdamping': 0.5, 'rshape': 100, 'density': 50, 'swidth': 160, 'q': 10, 'diffusion': 80, 'on': true, 'dtime': 2};
        r0['rvb'] = r15;
        r0['on'] = r1;
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': 0};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -30, 'rvb': -24};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Trung tính';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 0.1, 'sshaper': true, 'ambience': 5, 'on': true, 'stereoizer': 0.2};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[881]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(9);
        r2 = {'on': true, 'freq': 90, 'band': 0, 'type': 6, 'q': 4.5, 'gain': 1.6};
        r1[0] = r2;
        r2 = {'on': true, 'freq': 480, 'band': 1, 'type': 6, 'q': 2, 'gain': -1.5};
        r1[1] = r2;
        r2 = {'on': true, 'freq': 3500, 'band': 2, 'type': 6, 'q': 2, 'gain': 1};
        r1[2] = r2;
        r2 = {'on': true, 'freq': 1500, 'band': 3, 'type': 6, 'q': 2, 'gain': -0.4};
        r1[3] = r2;
        r2 = {'on': true, 'freq': 9000, 'band': 4, 'type': 5, 'q': 0.1, 'gain': 0.1};
        r1[4] = r2;
        r2 = {'on': true, 'freq': 6000, 'band': 5, 'type': 6, 'q': 1.5, 'gain': 0.5};
        r1[5] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[6] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[7] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[8] = r2;
        r3 = {'f': null, 'gain': 0, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r14 = {};
        r14['on'] = r0;
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 2, 'gain': -8, 'freq': 700, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 2, 'gain': -8, 'freq': 7000, 'curve': 2, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 0, 'band': 3, 'q': 0.5, 'gain': -4, 'freq': 8000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r13 = {};
        r13['f'] = r0;
        r1 = true;
        r13['on'] = r1;
        r0 = {};
        r15 = {'pdelay': 80, 'hfdamping': 0.5, 'rshape': 100, 'density': 50, 'swidth': 160, 'q': 10, 'diffusion': 80, 'on': true, 'dtime': 2};
        r0['rvb'] = r15;
        r0['on'] = r1;
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': 0};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -30, 'rvb': -26};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Trung tính';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 0, 'sshaper': true, 'stereoizer': 0.2, 'on': true, 'ambience': 5};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[882]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(9);
        r2 = {'gain': 1.6, 'freq': 90, 'band': 0, 'type': 6, 'on': true, 'q': 4.5};
        r1[0] = r2;
        r2 = {'gain': -1.5, 'freq': 480, 'band': 1, 'type': 6, 'on': true, 'q': 2};
        r1[1] = r2;
        r2 = {'gain': 0.8, 'freq': 3500, 'band': 2, 'type': 6, 'on': true, 'q': 2};
        r1[2] = r2;
        r2 = {'gain': -0.4, 'freq': 1500, 'band': 3, 'type': 6, 'on': true, 'q': 2};
        r1[3] = r2;
        r2 = {'gain': 0.1, 'freq': 9000, 'band': 4, 'type': 5, 'on': true, 'q': 0.1};
        r1[4] = r2;
        r2 = {'gain': 0.5, 'freq': 6000, 'band': 5, 'type': 6, 'on': true, 'q': 1.5};
        r1[5] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[6] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[7] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[8] = r2;
        r3 = {'f': null, 'gain': 0, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r14 = {};
        r14['on'] = r0;
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 2, 'gain': -8, 'freq': 700, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 2, 'gain': -8, 'freq': 7000, 'curve': 2, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 0, 'band': 3, 'q': 0.5, 'gain': -4, 'freq': 8000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r13 = {};
        r13['f'] = r0;
        r1 = true;
        r13['on'] = r1;
        r0 = {};
        r15 = {'pdelay': 80, 'hfdamping': 0.5, 'rshape': 100, 'density': 50, 'swidth': 160, 'q': 10, 'diffusion': 80, 'on': true, 'dtime': 2};
        r0['rvb'] = r15;
        r0['on'] = r1;
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': 0};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -30, 'rvb': -28};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Trung tính';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 0, 'sshaper': true, 'ambience': 5, 'stereoizer': 0.2, 'on': true};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[883]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r1 = new Array(9);
        r2 = {'on': true, 'freq': 35, 'band': 0, 'q': 0.8, 'type': 1, 'gain': 0};
        r1[0] = r2;
        r2 = {'on': true, 'freq': 250, 'band': 1, 'q': 1, 'type': 6, 'gain': -0.2};
        r1[1] = r2;
        r2 = {'on': true, 'freq': 3000, 'band': 2, 'q': 1, 'type': 6, 'gain': 1.8};
        r1[2] = r2;
        r2 = {'on': true, 'freq': 5000, 'band': 3, 'q': 0.9, 'type': 6, 'gain': 0.8};
        r1[3] = r2;
        r2 = {'band': 4, 'on': false};
        r1[4] = r2;
        r2 = {'band': 5, 'on': false};
        r1[5] = r2;
        r2 = {'band': 6, 'on': false};
        r1[6] = r2;
        r2 = {'band': 7, 'on': false};
        r1[7] = r2;
        r2 = {'band': 8, 'on': false};
        r1[8] = r2;
        r4 = {'f': null, 'gain': 0, 'on': true};
        r4['f'] = r1;
        r3 = {};
        r3['on'] = r0;
        r2 = {};
        r2['on'] = r0;
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 0.8, 'gain': -2, 'freq': 180, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 0.8, 'gain': -1, 'freq': 2500, 'curve': 1, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 3, 'band': 3, 'q': 0.7, 'gain': -2, 'freq': 7000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r13 = {};
        r13['f'] = r0;
        r1 = true;
        r13['on'] = r1;
        r0 = {};
        r14 = {'pdelay': 15, 'hfdamping': 4.5, 'rshape': 52, 'density': 52, 'swidth': 95, 'q': 4, 'diffusion': 56, 'on': true, 'dtime': 0.7};
        r0['rvb'] = r14;
        r0['on'] = r1;
        r14 = {'pattern': 13, 'rsize': 8, 'on': true, 'sdelay': 6};
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': -96.3};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -28, 'rvb': -32};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': -96.3, 'front': 0, 'rear': -5, 'center': -1};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r7 = {'treble': 0.8, 'trebleFreq': 6000, 'on': true, 'bass': 0.8, 'trebleQ': 0.7, 'bassQ': 0.7, 'bassFreq': 200};
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Tự tin - phong cách Trung Hoa';
        r1['effect_name'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1, 'sshaper': true, 'ambience': 1, 'on': true, 'stereoizer': 1.2};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[884]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r1 = false;
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r0 = new Array(9);
        r2 = {'on': true, 'freq': 35, 'band': 0, 'q': 0.8, 'type': 1, 'gain': 0};
        r0[0] = r2;
        r2 = {'on': true, 'freq': 250, 'band': 1, 'q': 1, 'type': 6, 'gain': -0.4};
        r0[1] = r2;
        r2 = {'on': true, 'freq': 3000, 'band': 2, 'q': 1, 'type': 6, 'gain': 1.4};
        r0[2] = r2;
        r2 = {'on': true, 'freq': 5000, 'band': 3, 'q': 0.9, 'type': 6, 'gain': 0.6};
        r0[3] = r2;
        r2 = {'band': 4, 'on': false};
        r0[4] = r2;
        r2 = {'band': 5, 'on': false};
        r0[5] = r2;
        r2 = {'band': 6, 'on': false};
        r0[6] = r2;
        r2 = {'band': 7, 'on': false};
        r0[7] = r2;
        r2 = {'band': 8, 'on': false};
        r0[8] = r2;
        r4 = {'f': null, 'gain': 0, 'on': true};
        r4['f'] = r0;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r7 = {'treble': 0.4, 'trebleFreq': 6000, 'on': true, 'bass': 0.2, 'trebleQ': 0.7, 'bassQ': 0.7, 'bassFreq': 200};
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Tự tin - cổ điển';
        r1['effect_name'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1, 'sshaper': true, 'stereoizer': 1, 'on': true, 'ambience': 1};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[885]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r1 = new Array(9);
        r2 = {'on': true, 'freq': 35, 'band': 0, 'q': 0.8, 'type': 1, 'gain': 0};
        r1[0] = r2;
        r2 = {'on': true, 'freq': 250, 'band': 1, 'q': 1, 'type': 6, 'gain': -0.4};
        r1[1] = r2;
        r2 = {'on': true, 'freq': 3000, 'band': 2, 'q': 1, 'type': 6, 'gain': 2.4};
        r1[2] = r2;
        r2 = {'on': true, 'freq': 5000, 'band': 3, 'q': 0.9, 'type': 6, 'gain': 1};
        r1[3] = r2;
        r2 = {'band': 4, 'on': false};
        r1[4] = r2;
        r2 = {'band': 5, 'on': false};
        r1[5] = r2;
        r2 = {'band': 6, 'on': false};
        r1[6] = r2;
        r2 = {'band': 7, 'on': false};
        r1[7] = r2;
        r2 = {'band': 8, 'on': false};
        r1[8] = r2;
        r4 = {'f': null, 'gain': 0, 'on': true};
        r4['f'] = r1;
        r3 = {};
        r3['on'] = r0;
        r2 = {};
        r2['on'] = r0;
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 0.8, 'gain': -2, 'freq': 180, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 0.8, 'gain': -1, 'freq': 2500, 'curve': 1, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 3, 'band': 3, 'q': 0.7, 'gain': -2, 'freq': 7000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r13 = {};
        r13['f'] = r0;
        r1 = true;
        r13['on'] = r1;
        r0 = {};
        r14 = {'pdelay': 15, 'hfdamping': 4.5, 'rshape': 52, 'density': 52, 'swidth': 95, 'q': 4, 'diffusion': 56, 'on': true, 'dtime': 0.7};
        r0['rvb'] = r14;
        r0['on'] = r1;
        r14 = {'pattern': 13, 'rsize': 8, 'on': true, 'sdelay': 6};
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': -96.3};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -28, 'rvb': -32};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': -96.3, 'front': 0, 'rear': -5, 'center': -1};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r7 = {'treble': 1.2, 'trebleFreq': 6000, 'on': true, 'bass': 0.8, 'trebleQ': 0.7, 'bassQ': 0.7, 'bassFreq': 200};
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Tự tin - giảm đục âm';
        r1['effect_name'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1.5, 'sshaper': true, 'ambience': 1, 'on': true, 'stereoizer': 1.2};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[886]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r1 = new Array(9);
        r2 = {'on': true, 'freq': 35, 'band': 0, 'q': 0.8, 'type': 1, 'gain': 0};
        r1[0] = r2;
        r2 = {'on': true, 'freq': 250, 'band': 1, 'q': 1, 'type': 6, 'gain': -0.6};
        r1[1] = r2;
        r2 = {'on': true, 'freq': 3000, 'band': 2, 'q': 1, 'type': 6, 'gain': 2.4};
        r1[2] = r2;
        r2 = {'on': true, 'freq': 5000, 'band': 3, 'q': 0.9, 'type': 6, 'gain': 1.2};
        r1[3] = r2;
        r2 = {'band': 4, 'on': false};
        r1[4] = r2;
        r2 = {'band': 5, 'on': false};
        r1[5] = r2;
        r2 = {'band': 6, 'on': false};
        r1[6] = r2;
        r2 = {'band': 7, 'on': false};
        r1[7] = r2;
        r2 = {'band': 8, 'on': false};
        r1[8] = r2;
        r4 = {'f': null, 'gain': 0, 'on': true};
        r4['f'] = r1;
        r3 = {};
        r3['on'] = r0;
        r2 = {};
        r2['on'] = r0;
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 0.8, 'gain': -2, 'freq': 180, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 0.8, 'gain': -1, 'freq': 2500, 'curve': 1, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 3, 'band': 3, 'q': 0.7, 'gain': -2, 'freq': 7000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r13 = {};
        r13['f'] = r0;
        r1 = true;
        r13['on'] = r1;
        r0 = {};
        r14 = {'pdelay': 15, 'hfdamping': 4.5, 'rshape': 52, 'density': 52, 'swidth': 95, 'q': 4, 'diffusion': 56, 'on': true, 'dtime': 0.6};
        r0['rvb'] = r14;
        r0['on'] = r1;
        r14 = {'pattern': 13, 'rsize': 8, 'on': true, 'sdelay': 6};
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': -96.3};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -28, 'rvb': -32};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': -96.3, 'front': 0, 'rear': -5, 'center': -1};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r7 = {'treble': 1.2, 'trebleFreq': 6000, 'on': true, 'bass': 0.4, 'trebleQ': 0.7, 'bassQ': 0.7, 'bassFreq': 200};
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Tự tin - nhạc điện tử';
        r1['effect_name'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 2, 'sshaper': true, 'ambience': 1, 'on': true, 'stereoizer': 1.5};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[887]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r14 = {};
        r1 = false;
        r14['on'] = r1;
        r13 = {};
        r13['on'] = r1;
        r12 = {};
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r0 = new Array(9);
        r2 = {'on': true, 'freq': 80, 'band': 0, 'type': 10, 'q': 1, 'gain': 0.5};
        r0[0] = r2;
        r2 = {'on': true, 'freq': 460, 'band': 1, 'type': 6, 'q': 3.5, 'gain': -0.5};
        r0[1] = r2;
        r2 = {'on': true, 'freq': 4000, 'band': 2, 'type': 8, 'q': 2, 'gain': 0.4};
        r0[2] = r2;
        r2 = {'on': true, 'freq': 8500, 'band': 3, 'type': 6, 'q': 0.8, 'gain': 0.4};
        r0[3] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[4] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[5] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[6] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[7] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[8] = r2;
        r5 = {'f': null, 'gain': 0, 'on': true};
        r5['f'] = r0;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r16 = {};
        r16['on'] = r1;
        r0 = new Array(3);
        r2 = {'insert': 3, 'band': 1, 'q': 2, 'gain': -9, 'freq': 700, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r2;
        r2 = {'insert': 3, 'band': 2, 'q': 2, 'gain': -8, 'freq': 7800, 'curve': 2, 'name': 'Bộ lọc 2'};
        r0[1] = r2;
        r2 = {'insert': 0, 'band': 3, 'q': 0.5, 'gain': -4, 'freq': 8000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r2;
        r15 = {};
        r15['f'] = r0;
        r0 = true;
        r15['on'] = r0;
        r2 = {};
        r17 = {'pdelay': 80, 'hfdamping': 0.5, 'rshape': 100, 'density': 50, 'swidth': 180, 'q': 10, 'diffusion': 100, 'on': true, 'dtime': 2};
        r2['rvb'] = r17;
        r2['on'] = r0;
        r2['er'] = r16;
        r2['tc'] = r15;
        r15 = {'center': 0, 'lfe': 0};
        r2['il'] = r15;
        r15 = {'dry': 0, 'er': -30, 'rvb': -30};
        r2['ol'] = r15;
        r2['useable'] = r0;
        r0 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r2['rl'] = r0;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r14;
        r1['alc'] = r13;
        r1['noise'] = r12;
        r1['chorus'] = r11;
        r1['eq'] = r10;
        r1['cmp'] = r9;
        r1['bt'] = r8;
        r1['flanger'] = r7;
        r1['spspatial'] = r6;
        r1['peq'] = r5;
        r1['limiter'] = r4;
        r1['dly'] = r3;
        r3 = 'Cơ bản';
        r1['effect_name'] = r3;
        r3 = ['cmp', 'eq', 'peq', 'rvb', 'se', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'effect_name', 'noise'];
        r1['order'] = r3;
        r1['rvb'] = r2;
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[888]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r14 = {};
        r1 = false;
        r14['on'] = r1;
        r13 = {};
        r13['on'] = r1;
        r12 = {};
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r0 = new Array(9);
        r2 = {'on': true, 'freq': 80, 'band': 0, 'type': 10, 'q': 1, 'gain': 0.5};
        r0[0] = r2;
        r2 = {'on': true, 'freq': 460, 'band': 1, 'type': 6, 'q': 3.5, 'gain': -0.9};
        r0[1] = r2;
        r2 = {'on': true, 'freq': 4000, 'band': 2, 'type': 8, 'q': 2, 'gain': -0.4};
        r0[2] = r2;
        r2 = {'on': true, 'freq': 8500, 'band': 3, 'type': 6, 'q': 0.8, 'gain': 0.4};
        r0[3] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[4] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[5] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[6] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[7] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[8] = r2;
        r5 = {'f': null, 'gain': 0, 'on': true};
        r5['f'] = r0;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r16 = {};
        r16['on'] = r1;
        r0 = new Array(3);
        r2 = {'insert': 3, 'band': 1, 'q': 2, 'gain': -9, 'freq': 700, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r2;
        r2 = {'insert': 3, 'band': 2, 'q': 2, 'gain': -8, 'freq': 7800, 'curve': 2, 'name': 'Bộ lọc 2'};
        r0[1] = r2;
        r2 = {'insert': 0, 'band': 3, 'q': 0.5, 'gain': -4, 'freq': 8000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r2;
        r15 = {};
        r15['f'] = r0;
        r0 = true;
        r15['on'] = r0;
        r2 = {};
        r17 = {'pdelay': 80, 'hfdamping': 0.5, 'rshape': 100, 'density': 50, 'swidth': 180, 'q': 10, 'diffusion': 100, 'on': true, 'dtime': 2};
        r2['rvb'] = r17;
        r2['on'] = r0;
        r2['er'] = r16;
        r2['tc'] = r15;
        r15 = {'center': 0, 'lfe': 0};
        r2['il'] = r15;
        r15 = {'dry': 0, 'er': -30, 'rvb': -27};
        r2['ol'] = r15;
        r2['useable'] = r0;
        r0 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r2['rl'] = r0;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r14;
        r1['alc'] = r13;
        r1['noise'] = r12;
        r1['chorus'] = r11;
        r1['eq'] = r10;
        r1['cmp'] = r9;
        r1['bt'] = r8;
        r1['flanger'] = r7;
        r1['spspatial'] = r6;
        r1['peq'] = r5;
        r1['limiter'] = r4;
        r1['dly'] = r3;
        r3 = 'Cơ bản';
        r1['effect_name'] = r3;
        r3 = ['cmp', 'eq', 'peq', 'rvb', 'se', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'effect_name', 'noise'];
        r1['order'] = r3;
        r1['rvb'] = r2;
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[889]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r13 = {};
        r1 = false;
        r13['on'] = r1;
        r12 = {};
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r0 = new Array(9);
        r2 = {'on': true, 'freq': 80, 'band': 0, 'type': 10, 'q': 1, 'gain': 0.5};
        r0[0] = r2;
        r2 = {'on': true, 'freq': 460, 'band': 1, 'type': 6, 'q': 3.5, 'gain': -0.5};
        r0[1] = r2;
        r2 = {'on': true, 'freq': 4000, 'band': 2, 'type': 8, 'q': 2, 'gain': 0.4};
        r0[2] = r2;
        r2 = {'on': true, 'freq': 8500, 'band': 3, 'type': 6, 'q': 0.8, 'gain': 0.4};
        r0[3] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[4] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[5] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[6] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[7] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[8] = r2;
        r5 = {'f': null, 'gain': 0, 'on': true};
        r5['f'] = r0;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r15 = {};
        r15['on'] = r1;
        r0 = new Array(3);
        r2 = {'insert': 3, 'band': 1, 'q': 2, 'gain': -9, 'freq': 700, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r2;
        r2 = {'insert': 3, 'band': 2, 'q': 2, 'gain': -8, 'freq': 7800, 'curve': 2, 'name': 'Bộ lọc 2'};
        r0[1] = r2;
        r2 = {'insert': 0, 'band': 3, 'q': 0.5, 'gain': -4, 'freq': 8000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r2;
        r14 = {};
        r14['f'] = r0;
        r0 = true;
        r14['on'] = r0;
        r2 = {};
        r16 = {'pdelay': 80, 'hfdamping': 0.5, 'rshape': 100, 'density': 50, 'swidth': 180, 'q': 10, 'diffusion': 100, 'on': true, 'dtime': 2};
        r2['rvb'] = r16;
        r2['on'] = r0;
        r2['er'] = r15;
        r2['tc'] = r14;
        r14 = {'center': 0, 'lfe': 0};
        r2['il'] = r14;
        r14 = {'dry': 0, 'er': -30, 'rvb': -30};
        r2['ol'] = r14;
        r2['useable'] = r0;
        r0 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r2['rl'] = r0;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r13;
        r1['alc'] = r12;
        r1['chorus'] = r11;
        r1['eq'] = r10;
        r1['cmp'] = r9;
        r1['bt'] = r8;
        r1['flanger'] = r7;
        r1['spspatial'] = r6;
        r1['peq'] = r5;
        r1['limiter'] = r4;
        r1['dly'] = r3;
        r3 = 'default';
        r1['effect_name'] = r3;
        r3 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r3;
        r1['rvb'] = r2;
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[890]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r14 = {};
        r1 = false;
        r14['on'] = r1;
        r13 = {};
        r13['on'] = r1;
        r12 = {};
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r0 = new Array(9);
        r2 = {'on': true, 'freq': 80, 'band': 0, 'type': 10, 'q': 1, 'gain': 0.5};
        r0[0] = r2;
        r2 = {'on': true, 'freq': 460, 'band': 1, 'type': 6, 'q': 3.5, 'gain': -0.5};
        r0[1] = r2;
        r2 = {'on': true, 'freq': 4000, 'band': 2, 'type': 8, 'q': 2, 'gain': 0.4};
        r0[2] = r2;
        r2 = {'on': true, 'freq': 8500, 'band': 3, 'type': 6, 'q': 0.8, 'gain': 0.4};
        r0[3] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[4] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[5] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[6] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[7] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[8] = r2;
        r5 = {'f': null, 'gain': 0, 'on': true};
        r5['f'] = r0;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r16 = {};
        r16['on'] = r1;
        r0 = new Array(3);
        r2 = {'insert': 3, 'band': 1, 'q': 2, 'gain': -9, 'freq': 700, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r2;
        r2 = {'insert': 3, 'band': 2, 'q': 2, 'gain': -8, 'freq': 7800, 'curve': 2, 'name': 'Bộ lọc 2'};
        r0[1] = r2;
        r2 = {'insert': 0, 'band': 3, 'q': 0.5, 'gain': -4, 'freq': 8000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r2;
        r15 = {};
        r15['f'] = r0;
        r0 = true;
        r15['on'] = r0;
        r2 = {};
        r17 = {'pdelay': 80, 'hfdamping': 0.5, 'rshape': 100, 'density': 50, 'swidth': 180, 'q': 10, 'diffusion': 100, 'on': true, 'dtime': 2};
        r2['rvb'] = r17;
        r2['on'] = r0;
        r2['er'] = r16;
        r2['tc'] = r15;
        r15 = {'center': 0, 'lfe': 0};
        r2['il'] = r15;
        r15 = {'dry': 0, 'er': -30, 'rvb': -30};
        r2['ol'] = r15;
        r2['useable'] = r0;
        r0 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r2['rl'] = r0;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r14;
        r1['alc'] = r13;
        r1['noise'] = r12;
        r1['chorus'] = r11;
        r1['eq'] = r10;
        r1['cmp'] = r9;
        r1['bt'] = r8;
        r1['flanger'] = r7;
        r1['spspatial'] = r6;
        r1['peq'] = r5;
        r1['limiter'] = r4;
        r1['dly'] = r3;
        r3 = 'Đảm bảo';
        r1['effect_name'] = r3;
        r3 = ['cmp', 'eq', 'peq', 'rvb', 'se', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'effect_name', 'noise'];
        r1['order'] = r3;
        r1['rvb'] = r2;
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[891]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(9);
        r2 = {'on': true, 'freq': 90, 'band': 0, 'type': 6, 'q': 3.5, 'gain': 1};
        r1[0] = r2;
        r2 = {'on': true, 'freq': 480, 'band': 1, 'type': 6, 'q': 2, 'gain': -1.5};
        r1[1] = r2;
        r2 = {'on': true, 'freq': 3500, 'band': 2, 'type': 6, 'q': 2, 'gain': -2.2};
        r1[2] = r2;
        r2 = {'on': true, 'freq': 1500, 'band': 3, 'type': 6, 'q': 2, 'gain': -1};
        r1[3] = r2;
        r2 = {'on': true, 'freq': 9000, 'band': 4, 'type': 5, 'q': 0.1, 'gain': 0.2};
        r1[4] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[5] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[6] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[7] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[8] = r2;
        r3 = {'f': null, 'gain': -1, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 2, 'gain': -5, 'freq': 700, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 2, 'gain': -8, 'freq': 7800, 'curve': 2, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 0, 'band': 3, 'q': 0.5, 'gain': -4, 'freq': 8000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r13 = {};
        r13['f'] = r0;
        r1 = true;
        r13['on'] = r1;
        r0 = {};
        r14 = {'pdelay': 80, 'hfdamping': 0.5, 'rshape': 100, 'density': 50, 'swidth': 180, 'q': 10, 'diffusion': 100, 'on': true, 'dtime': 3};
        r0['rvb'] = r14;
        r0['on'] = r1;
        r14 = {'pattern': 15, 'rsize': 100, 'sdelay': 50, 'on': true};
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': 0};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -30, 'rvb': -30};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Cấu hình hiệu ứng âm thanh 3-24 10:16';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc', 'effect_name'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 5, 'sshaper': true, 'stereoizer': 0.2, 'on': true, 'ambience': 5};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[892]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(9);
        r2 = {'on': true, 'freq': 90, 'band': 0, 'type': 6, 'q': 3.5, 'gain': 1};
        r1[0] = r2;
        r2 = {'on': true, 'freq': 480, 'band': 1, 'type': 6, 'q': 2, 'gain': -2};
        r1[1] = r2;
        r2 = {'on': true, 'freq': 3500, 'band': 2, 'type': 6, 'q': 2, 'gain': -2.4};
        r1[2] = r2;
        r2 = {'on': true, 'freq': 1500, 'band': 3, 'type': 6, 'q': 2, 'gain': -1};
        r1[3] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[4] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[5] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[6] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[7] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[8] = r2;
        r3 = {'f': null, 'gain': -1, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 2, 'gain': -5, 'freq': 700, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 2, 'gain': -8, 'freq': 7800, 'curve': 2, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 0, 'band': 3, 'q': 0.5, 'gain': -4, 'freq': 8000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r13 = {};
        r13['f'] = r0;
        r1 = true;
        r13['on'] = r1;
        r0 = {};
        r14 = {'pdelay': 80, 'hfdamping': 0.5, 'rshape': 100, 'density': 50, 'swidth': 180, 'q': 10, 'diffusion': 100, 'on': true, 'dtime': 3};
        r0['rvb'] = r14;
        r0['on'] = r1;
        r14 = {'pattern': 15, 'rsize': 100, 'sdelay': 50, 'on': true};
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': 0};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -30, 'rvb': -27};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Cấu hình hiệu ứng âm thanh 3-24 10:16';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc', 'effect_name'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 6, 'sshaper': true, 'stereoizer': 0.2, 'on': true, 'ambience': 5};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[893]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(9);
        r2 = {'on': true, 'freq': 90, 'band': 0, 'type': 6, 'q': 3.5, 'gain': 1};
        r1[0] = r2;
        r2 = {'on': true, 'freq': 480, 'band': 1, 'type': 6, 'q': 2, 'gain': -1.5};
        r1[1] = r2;
        r2 = {'on': true, 'freq': 3500, 'band': 2, 'type': 6, 'q': 2, 'gain': -2};
        r1[2] = r2;
        r2 = {'on': true, 'freq': 1500, 'band': 3, 'type': 6, 'q': 2, 'gain': -1};
        r1[3] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[4] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[5] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[6] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[7] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[8] = r2;
        r3 = {'f': null, 'gain': -1, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 2, 'gain': -5, 'freq': 700, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 2, 'gain': -8, 'freq': 7800, 'curve': 2, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 0, 'band': 3, 'q': 0.5, 'gain': -4, 'freq': 8000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r13 = {};
        r13['f'] = r0;
        r1 = true;
        r13['on'] = r1;
        r0 = {};
        r14 = {'pdelay': 80, 'hfdamping': 0.5, 'rshape': 100, 'density': 50, 'swidth': 180, 'q': 10, 'diffusion': 100, 'on': true, 'dtime': 3};
        r0['rvb'] = r14;
        r0['on'] = r1;
        r14 = {'pattern': 15, 'rsize': 100, 'sdelay': 50, 'on': true};
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': 0};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -30, 'rvb': -25};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Âm';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc', 'effect_name'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 7, 'sshaper': true, 'stereoizer': 0.2, 'useable': true, 'on': true, 'ambience': 5};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[894]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(9);
        r2 = {'on': true, 'freq': 90, 'band': 0, 'type': 6, 'q': 3.5, 'gain': 1};
        r1[0] = r2;
        r2 = {'on': true, 'freq': 480, 'band': 1, 'type': 6, 'q': 2, 'gain': -1.5};
        r1[1] = r2;
        r2 = {'on': true, 'freq': 3500, 'band': 2, 'type': 6, 'q': 2, 'gain': -2};
        r1[2] = r2;
        r2 = {'on': true, 'freq': 1500, 'band': 3, 'type': 6, 'q': 2, 'gain': -1};
        r1[3] = r2;
        r2 = {'on': true, 'freq': 9000, 'band': 4, 'type': 5, 'q': 0.1, 'gain': -0.4};
        r1[4] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[5] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[6] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[7] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[8] = r2;
        r3 = {'f': null, 'gain': -1, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 2, 'gain': -5, 'freq': 700, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 2, 'gain': -8, 'freq': 7800, 'curve': 2, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 0, 'band': 3, 'q': 0.5, 'gain': -4, 'freq': 8000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r13 = {};
        r13['f'] = r0;
        r1 = true;
        r13['on'] = r1;
        r0 = {};
        r14 = {'pdelay': 80, 'hfdamping': 0.5, 'rshape': 100, 'density': 50, 'swidth': 180, 'q': 10, 'diffusion': 100, 'on': true, 'dtime': 3};
        r0['rvb'] = r14;
        r0['on'] = r1;
        r14 = {'pattern': 15, 'rsize': 100, 'sdelay': 50, 'on': true};
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': 0};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -30, 'rvb': -32};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Cấu hình hiệu ứng âm thanh 3-24 10:16';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc', 'effect_name'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 5, 'sshaper': true, 'stereoizer': 0.2, 'on': true, 'ambience': 5};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[895]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r11 = {};
        r1 = false;
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r0 = new Array(8);
        r2 = {'gain': 1.6, 'freq': 90, 'band': 0, 'type': 6, 'q': 4.5, 'on': true};
        r0[0] = r2;
        r2 = {'gain': -1.5, 'freq': 480, 'band': 1, 'type': 6, 'q': 2, 'on': true};
        r0[1] = r2;
        r2 = {'gain': 1, 'freq': 3500, 'band': 2, 'type': 6, 'q': 2, 'on': true};
        r0[2] = r2;
        r2 = {'band': 3, 'on': false};
        r0[3] = r2;
        r2 = {'gain': 0.2, 'freq': 9000, 'band': 4, 'type': 5, 'q': 0.1, 'on': true};
        r0[4] = r2;
        r2 = {'band': 5, 'on': false};
        r0[5] = r2;
        r2 = {'band': 6, 'on': false};
        r0[6] = r2;
        r2 = {'band': 7, 'on': false};
        r0[7] = r2;
        r3 = {'f': null, 'gain': -0.2, 'on': true};
        r3['f'] = r0;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r11;
        r1['alc'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = '2';
        r1['effect_name'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 0, 'sshaper': true, 'ambience': 5, 'on': true, 'stereoizer': 0.1};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[896]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r11 = {};
        r1 = false;
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r0 = new Array(8);
        r2 = {'gain': 1.6, 'freq': 90, 'band': 0, 'type': 6, 'q': 4.5, 'on': true};
        r0[0] = r2;
        r2 = {'gain': -1.5, 'freq': 480, 'band': 1, 'type': 6, 'q': 2, 'on': true};
        r0[1] = r2;
        r2 = {'gain': 1, 'freq': 3500, 'band': 2, 'type': 6, 'q': 2, 'on': true};
        r0[2] = r2;
        r2 = {'band': 3, 'on': false};
        r0[3] = r2;
        r2 = {'gain': 0.2, 'freq': 9000, 'band': 4, 'type': 5, 'q': 0.1, 'on': true};
        r0[4] = r2;
        r2 = {'band': 5, 'on': false};
        r0[5] = r2;
        r2 = {'band': 6, 'on': false};
        r0[6] = r2;
        r2 = {'band': 7, 'on': false};
        r0[7] = r2;
        r3 = {'f': null, 'gain': -0.2, 'on': true};
        r3['f'] = r0;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r11;
        r1['alc'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = '2';
        r1['effect_name'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1, 'sshaper': true, 'ambience': 5, 'on': true, 'stereoizer': 0.2};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[897]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r1 = false;
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r0 = new Array(9);
        r2 = {'on': true, 'freq': 90, 'band': 0, 'type': 6, 'q': 4.5, 'gain': 1.6};
        r0[0] = r2;
        r2 = {'on': true, 'freq': 480, 'band': 1, 'type': 6, 'q': 2, 'gain': -1.5};
        r0[1] = r2;
        r2 = {'on': true, 'freq': 3500, 'band': 2, 'type': 6, 'q': 2, 'gain': 0.5};
        r0[2] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[3] = r2;
        r2 = {'on': true, 'freq': 9000, 'band': 4, 'type': 5, 'q': 0.1, 'gain': 0.2};
        r0[4] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[5] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[6] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[7] = r2;
        r2 = {};
        r2['on'] = r1;
        r0[8] = r2;
        r3 = {'f': null, 'gain': 0, 'on': true};
        r3['f'] = r0;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0.5, 'infiniteRatio': true, 'ratio': 2, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = '2';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 0.5, 'sshaper': true, 'ambience': 5, 'on': true, 'stereoizer': 0.2};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[898]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r11 = {};
        r1 = false;
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r0 = new Array(8);
        r2 = {'gain': 1.6, 'freq': 90, 'band': 0, 'type': 6, 'q': 4.5, 'on': true};
        r0[0] = r2;
        r2 = {'gain': -1.5, 'freq': 480, 'band': 1, 'type': 6, 'q': 2, 'on': true};
        r0[1] = r2;
        r2 = {'gain': 1, 'freq': 3500, 'band': 2, 'type': 6, 'q': 2, 'on': true};
        r0[2] = r2;
        r2 = {'band': 3, 'on': false};
        r0[3] = r2;
        r2 = {'gain': 0.2, 'freq': 9000, 'band': 4, 'type': 5, 'q': 0.1, 'on': true};
        r0[4] = r2;
        r2 = {'band': 5, 'on': false};
        r0[5] = r2;
        r2 = {'band': 6, 'on': false};
        r0[6] = r2;
        r2 = {'band': 7, 'on': false};
        r0[7] = r2;
        r3 = {'f': null, 'gain': -0.2, 'on': true};
        r3['f'] = r0;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r11;
        r1['alc'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = '2';
        r1['effect_name'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 0.5, 'sshaper': true, 'ambience': 5, 'on': true, 'stereoizer': 0.3};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[899]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r1 = [0, 0, 0, 0, 0, 0.6, 0.6, 0];
        r2 = -0.4;
        r1[8] = r2;
        r2 = -1;
        r1[9] = r2;
        r8 = {};
        r8['eqs'] = r1;
        r1 = true;
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r2 = new Array(9);
        r3 = {'gain': 0.5, 'freq': 63, 'band': 0, 'type': 6, 'on': true, 'q': 2};
        r2[0] = r3;
        r3 = {'gain': 3, 'freq': 1000, 'band': 1, 'type': 6, 'on': true, 'q': 2};
        r2[1] = r3;
        r3 = {'gain': 2, 'freq': 2000, 'band': 2, 'type': 6, 'on': true, 'q': 2};
        r2[2] = r3;
        r3 = {'gain': 2, 'freq': 4000, 'band': 3, 'type': 6, 'on': true, 'q': 2};
        r2[3] = r3;
        r3 = {'gain': 1.5, 'freq': 8000, 'band': 4, 'type': 5, 'on': true, 'q': 2};
        r2[4] = r3;
        r3 = {'gain': 2.5, 'freq': 16000, 'band': 5, 'type': 5, 'on': true, 'q': 2};
        r2[5] = r3;
        r3 = {};
        r3['on'] = r0;
        r2[6] = r3;
        r3 = {};
        r3['on'] = r0;
        r2[7] = r3;
        r3 = {};
        r3['on'] = r0;
        r2[8] = r3;
        r3 = {'f': null, 'gain': 0.5, 'on': true};
        r3['f'] = r2;
        r2 = {};
        r2['on'] = r0;
        r13 = {};
        r13['on'] = r0;
        r0 = {};
        r14 = {'pdelay': 55, 'swidth': 110, 'hfdamping': 5.5, 'density': 48, 'rshape': 38, 'q': 4, 'diffusion': 55, 'dtime': 0.9};
        r0['rvb'] = r14;
        r0['on'] = r1;
        r14 = {'pattern': 10, 'on': true, 'sdelay': 8, 'rsize': 45};
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': 0};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -30, 'rvb': -24.2};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Dân ca sôi động';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'alc', 'effect_name', 'noise'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1.5, 'sshaper': false, 'ambience': 5, 'on': true, 'stereoizer': 3};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[900]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(9);
        r2 = {'gain': 0.5, 'freq': 63, 'band': 0, 'type': 6, 'on': true, 'q': 2};
        r1[0] = r2;
        r2 = {'gain': 3, 'freq': 1000, 'band': 1, 'type': 6, 'on': true, 'q': 2};
        r1[1] = r2;
        r2 = {'gain': 2, 'freq': 2000, 'band': 2, 'type': 6, 'on': true, 'q': 2};
        r1[2] = r2;
        r2 = {'gain': 2, 'freq': 4000, 'band': 3, 'type': 6, 'on': true, 'q': 2};
        r1[3] = r2;
        r2 = {'gain': 1.5, 'freq': 8000, 'band': 4, 'type': 5, 'on': true, 'q': 2};
        r1[4] = r2;
        r2 = {'gain': 2.5, 'freq': 16000, 'band': 5, 'type': 5, 'on': true, 'q': 2};
        r1[5] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[6] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[7] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[8] = r2;
        r3 = {'f': null, 'gain': 0.5, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r13 = {};
        r13['on'] = r0;
        r0 = {};
        r1 = {'pdelay': 55, 'hfdamping': 5.5, 'rshape': 38, 'density': 48, 'swidth': 130, 'q': 4, 'diffusion': 60, 'on': true, 'dtime': 1.2};
        r0['rvb'] = r1;
        r1 = true;
        r0['on'] = r1;
        r14 = {'pattern': 10, 'on': true, 'sdelay': 8, 'rsize': 45};
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': 0};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -30, 'rvb': -23};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Nhạc không lời sôi động';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1.5, 'sshaper': false, 'ambience': 5, 'on': true, 'stereoizer': 5};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[901]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(9);
        r2 = {'on': true, 'freq': 63, 'band': 0, 'type': 6, 'q': 2, 'gain': 0.5};
        r1[0] = r2;
        r2 = {'on': true, 'freq': 1000, 'band': 1, 'type': 6, 'q': 2, 'gain': 3};
        r1[1] = r2;
        r2 = {'on': true, 'freq': 2000, 'band': 2, 'type': 6, 'q': 2, 'gain': 2};
        r1[2] = r2;
        r2 = {'on': true, 'freq': 4000, 'band': 3, 'type': 6, 'q': 2, 'gain': 2};
        r1[3] = r2;
        r2 = {'on': true, 'freq': 8000, 'band': 4, 'type': 5, 'q': 2, 'gain': 1.5};
        r1[4] = r2;
        r2 = {'on': true, 'freq': 16000, 'band': 5, 'type': 5, 'q': 2, 'gain': 2.5};
        r1[5] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[6] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[7] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[8] = r2;
        r3 = {'f': null, 'gain': 0.5, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r13 = {};
        r13['on'] = r0;
        r0 = {};
        r1 = {'pdelay': 55, 'swidth': 110, 'hfdamping': 5.5, 'density': 48, 'rshape': 38, 'q': 4, 'diffusion': 55, 'dtime': 0.9};
        r0['rvb'] = r1;
        r1 = true;
        r0['on'] = r1;
        r14 = {'pattern': 10, 'rsize': 45, 'sdelay': 8, 'on': true};
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': 0};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -32, 'rvb': -26};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Sôi động';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'alc', 'effect_name', 'noise'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1.5, 'sshaper': false, 'stereoizer': 4, 'on': true, 'ambience': 5};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[902]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r0 = false;
        r12['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r1 = [0, 1, 0, 0, 0, 0, 0, 0, 0.8, 0.8];
        r8['eqs'] = r1;
        r1 = true;
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r2 = new Array(9);
        r3 = {'gain': 0.5, 'freq': 63, 'band': 0, 'type': 6, 'on': true, 'q': 2};
        r2[0] = r3;
        r3 = {'gain': 3, 'freq': 1000, 'band': 1, 'type': 6, 'on': true, 'q': 2};
        r2[1] = r3;
        r3 = {'gain': 2, 'freq': 2000, 'band': 2, 'type': 6, 'on': true, 'q': 2};
        r2[2] = r3;
        r3 = {'gain': 2, 'freq': 4000, 'band': 3, 'type': 6, 'on': true, 'q': 2};
        r2[3] = r3;
        r3 = {'gain': 1.5, 'freq': 8000, 'band': 4, 'type': 5, 'on': true, 'q': 2};
        r2[4] = r3;
        r3 = {'gain': 2.5, 'freq': 16000, 'band': 5, 'type': 5, 'on': true, 'q': 2};
        r2[5] = r3;
        r3 = {};
        r3['on'] = r0;
        r2[6] = r3;
        r3 = {};
        r3['on'] = r0;
        r2[7] = r3;
        r3 = {};
        r3['on'] = r0;
        r2[8] = r3;
        r3 = {'f': null, 'gain': 0.5, 'on': true};
        r3['f'] = r2;
        r2 = {};
        r2['on'] = r0;
        r13 = {};
        r13['on'] = r0;
        r0 = {};
        r14 = {'pdelay': 55, 'hfdamping': 5.5, 'rshape': 38, 'density': 48, 'swidth': 110, 'q': 4, 'diffusion': 55, 'on': true, 'dtime': 0.7};
        r0['rvb'] = r14;
        r0['on'] = r1;
        r14 = {'pattern': 10, 'on': true, 'sdelay': 8, 'rsize': 45};
        r0['er'] = r14;
        r0['tc'] = r13;
        r13 = {'center': 0, 'lfe': 0};
        r0['il'] = r13;
        r13 = {'dry': 0, 'er': -30, 'rvb': -28};
        r0['ol'] = r13;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Nhịp điệu sôi động';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1.5, 'sshaper': false, 'ambience': 5, 'on': true, 'stereoizer': 2.5};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[903]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r11 = {};
        r0 = false;
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r1 = [0, 0, 0, 0.6, 0.4, 1, 1, 0, 0, 0];
        r7['eqs'] = r1;
        r1 = true;
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r2 = new Array(9);
        r3 = {'on': true, 'freq': 63, 'band': 0, 'type': 6, 'q': 2, 'gain': 0};
        r2[0] = r3;
        r3 = {'on': true, 'freq': 125, 'band': 1, 'type': 6, 'q': 2, 'gain': 1};
        r2[1] = r3;
        r3 = {'on': true, 'freq': 250, 'band': 2, 'type': 6, 'q': 2, 'gain': 1.5};
        r2[2] = r3;
        r3 = {'on': true, 'freq': 500, 'band': 3, 'type': 6, 'q': 2, 'gain': 1.2};
        r2[3] = r3;
        r3 = {'on': true, 'freq': 1000, 'band': 4, 'type': 6, 'q': 2, 'gain': 1.8};
        r2[4] = r3;
        r3 = {'on': true, 'freq': 2000, 'band': 5, 'type': 6, 'q': 2, 'gain': 1.5};
        r2[5] = r3;
        r3 = {};
        r3['on'] = r0;
        r2[6] = r3;
        r3 = {};
        r3['on'] = r0;
        r2[7] = r3;
        r3 = {};
        r3['on'] = r0;
        r2[8] = r3;
        r3 = {'f': null, 'gain': -1, 'on': true};
        r3['f'] = r2;
        r2 = {};
        r2['on'] = r0;
        r12 = {};
        r12['on'] = r0;
        r0 = {};
        r13 = {'pdelay': 110, 'hfdamping': 4, 'rshape': 50, 'density': 37, 'swidth': 100, 'q': 5, 'diffusion': 60, 'on': true, 'dtime': 1.1};
        r0['rvb'] = r13;
        r0['on'] = r1;
        r13 = {'pattern': 12, 'on': true, 'sdelay': 25, 'rsize': 55};
        r0['er'] = r13;
        r0['tc'] = r12;
        r12 = {'center': 0, 'lfe': 0};
        r0['il'] = r12;
        r12 = {'dry': 0, 'er': -30, 'rvb': -27};
        r0['ol'] = r12;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r11;
        r1['alc'] = r10;
        r1['noise'] = r9;
        r1['chorus'] = r8;
        r1['eq'] = r7;
        r1['cmp'] = r6;
        r6 = {'treble': 1.5, 'trebleFreq': 6000, 'on': true, 'bass': 1, 'trebleQ': 0.5, 'bassQ': 0.5, 'useable': true, 'bassFreq': 200};
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'core': true, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Dân ca chữa lành';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc', 'effect_name'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1.2, 'sshaper': false, 'stereoizer': 4.5, 'on': true, 'ambience': 4};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[904]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r11 = {};
        r0 = false;
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(9);
        r2 = {'on': true, 'freq': 63, 'band': 0, 'type': 6, 'q': 2, 'gain': 0};
        r1[0] = r2;
        r2 = {'on': true, 'freq': 125, 'band': 1, 'type': 6, 'q': 2, 'gain': 1};
        r1[1] = r2;
        r2 = {'on': true, 'freq': 250, 'band': 2, 'type': 6, 'q': 2, 'gain': 1.5};
        r1[2] = r2;
        r2 = {'on': true, 'freq': 500, 'band': 3, 'type': 6, 'q': 2, 'gain': 1.2};
        r1[3] = r2;
        r2 = {'on': true, 'freq': 1000, 'band': 4, 'type': 6, 'q': 2, 'gain': 1.8};
        r1[4] = r2;
        r2 = {'on': true, 'freq': 2000, 'band': 5, 'type': 6, 'q': 2, 'gain': 1.5};
        r1[5] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[6] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[7] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[8] = r2;
        r3 = {'f': null, 'gain': -1, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r12 = {};
        r12['on'] = r0;
        r0 = {};
        r1 = {'pdelay': 110, 'hfdamping': 4, 'rshape': 50, 'density': 37, 'swidth': 118, 'q': 5, 'diffusion': 68, 'on': true, 'dtime': 1.4};
        r0['rvb'] = r1;
        r1 = true;
        r0['on'] = r1;
        r13 = {'pattern': 12, 'rsize': 55, 'on': true, 'sdelay': 25};
        r0['er'] = r13;
        r0['tc'] = r12;
        r12 = {'center': 0, 'lfe': 0};
        r0['il'] = r12;
        r12 = {'dry': 0, 'er': -30, 'rvb': -24};
        r0['ol'] = r12;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r11;
        r1['alc'] = r10;
        r1['noise'] = r9;
        r1['chorus'] = r8;
        r1['eq'] = r7;
        r1['cmp'] = r6;
        r6 = {'treble': 1.5, 'trebleFreq': 6000, 'on': true, 'bass': 1, 'trebleQ': 0.5, 'bassQ': 0.5, 'useable': true, 'bassFreq': 200};
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'core': true, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Chữa lành';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc', 'effect_name'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1.2, 'sshaper': false, 'ambience': 4, 'stereoizer': 6, 'on': true};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[905]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r11 = {};
        r0 = false;
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(9);
        r2 = {'on': true, 'freq': 63, 'band': 0, 'type': 6, 'q': 2, 'gain': 0};
        r1[0] = r2;
        r2 = {'on': true, 'freq': 125, 'band': 1, 'type': 6, 'q': 2, 'gain': 1};
        r1[1] = r2;
        r2 = {'on': true, 'freq': 250, 'band': 2, 'type': 6, 'q': 2, 'gain': 1.5};
        r1[2] = r2;
        r2 = {'on': true, 'freq': 500, 'band': 3, 'type': 6, 'q': 2, 'gain': 1.2};
        r1[3] = r2;
        r2 = {'on': true, 'freq': 1000, 'band': 4, 'type': 6, 'q': 2, 'gain': 1.8};
        r1[4] = r2;
        r2 = {'on': true, 'freq': 2000, 'band': 5, 'type': 6, 'q': 2, 'gain': 1.5};
        r1[5] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[6] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[7] = r2;
        r2 = {};
        r2['on'] = r0;
        r1[8] = r2;
        r3 = {'f': null, 'gain': -1, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r12 = {};
        r12['on'] = r0;
        r0 = {};
        r1 = {'pdelay': 110, 'hfdamping': 4, 'rshape': 50, 'density': 37, 'swidth': 100, 'q': 5, 'diffusion': 60, 'on': true, 'dtime': 1.1};
        r0['rvb'] = r1;
        r1 = true;
        r0['on'] = r1;
        r13 = {'pattern': 12, 'on': true, 'sdelay': 25, 'rsize': 55};
        r0['er'] = r13;
        r0['tc'] = r12;
        r12 = {'center': 0, 'lfe': 0};
        r0['il'] = r12;
        r12 = {'dry': 0, 'er': -30, 'rvb': -26};
        r0['ol'] = r12;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r11;
        r1['alc'] = r10;
        r1['noise'] = r9;
        r1['chorus'] = r8;
        r1['eq'] = r7;
        r1['cmp'] = r6;
        r6 = {'treble': 1.5, 'trebleFreq': 6000, 'on': true, 'bass': 1, 'trebleQ': 0.5, 'bassQ': 0.5, 'useable': true, 'bassFreq': 200};
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'core': true, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
         r2 = 'Chữa lành';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc', 'effect_name'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1.2, 'sshaper': false, 'ambience': 4, 'stereoizer': 5, 'on': true};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[906]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r11 = {};
        r0 = false;
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r1 = [0, 1, 0, 0, 0, 0, 0, 0, 0, 0];
        r7['eqs'] = r1;
        r1 = true;
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r2 = new Array(9);
        r3 = {'on': true, 'freq': 63, 'band': 0, 'type': 6, 'q': 2, 'gain': 0};
        r2[0] = r3;
        r3 = {'on': true, 'freq': 125, 'band': 1, 'type': 6, 'q': 2, 'gain': 1};
        r2[1] = r3;
        r3 = {'on': true, 'freq': 250, 'band': 2, 'type': 6, 'q': 2, 'gain': 1.5};
        r2[2] = r3;
        r3 = {'on': true, 'freq': 500, 'band': 3, 'type': 6, 'q': 2, 'gain': 1.2};
        r2[3] = r3;
        r3 = {'on': true, 'freq': 1000, 'band': 4, 'type': 6, 'q': 2, 'gain': 1.8};
        r2[4] = r3;
        r3 = {'on': true, 'freq': 2000, 'band': 5, 'type': 6, 'q': 2, 'gain': 1.5};
        r2[5] = r3;
        r3 = {};
        r3['on'] = r0;
        r2[6] = r3;
        r3 = {};
        r3['on'] = r0;
        r2[7] = r3;
        r3 = {};
        r3['on'] = r0;
        r2[8] = r3;
        r3 = {'f': null, 'gain': -1, 'on': true};
        r3['f'] = r2;
        r2 = {};
        r2['on'] = r0;
        r12 = {};
        r12['on'] = r0;
        r0 = {};
        r13 = {'pdelay': 110, 'hfdamping': 4, 'rshape': 50, 'density': 37, 'swidth': 85, 'q': 5, 'diffusion': 50, 'on': true, 'dtime': 0.8};
        r0['rvb'] = r13;
        r0['on'] = r1;
        r13 = {'pattern': 12, 'on': true, 'sdelay': 25, 'rsize': 55};
        r0['er'] = r13;
        r0['tc'] = r12;
        r12 = {'center': 0, 'lfe': 0};
        r0['il'] = r12;
        r12 = {'dry': 0, 'er': -30, 'rvb': -27};
        r0['ol'] = r12;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r11;
        r1['alc'] = r10;
        r1['noise'] = r9;
        r1['chorus'] = r8;
        r1['eq'] = r7;
        r1['cmp'] = r6;
        r6 = {'treble': 1.5, 'trebleFreq': 6000, 'on': true, 'bass': 1, 'trebleQ': 0.5, 'bassQ': 0.5, 'useable': true, 'bassFreq': 200};
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.08, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
         r2 = 'Chữa lành';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc', 'effect_name'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1.2, 'sshaper': false, 'stereoizer': 4, 'on': true, 'ambience': 4};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[907]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r1 = false;
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r0 = new Array(10);
        r2 = -1;
        r0[0] = r2;
        r3 = -0.5;
        r0[1] = r3;
        r3 = 0;
        r0[2] = r3;
        r0[3] = r2;
        r0[4] = r2;
        r2 = 1;
        r0[5] = r2;
        r2 = 1.5;
        r0[6] = r2;
        r2 = 2;
        r0[7] = r2;
        r3 = 2.5;
        r0[8] = r3;
        r0[9] = r2;
        r8 = {};
        r8['eqs'] = r0;
        r0 = true;
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r8 = {'attack': 0.025, 'on': true, 'pregain': 0, 'release': 0.12, 'range': 0, 'ratio': 1.5, 'postgain': 0, 'threshold': -12};
        r1['cmp'] = r8;
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r1['limiter'] = r3;
        r1['dly'] = r2;
         r2 = 'Vui tươiv2-folk se';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'peq', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'eq', 'rvb', 'se', 'bt', 'noise', 'effect_name'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 4, 'sshaper': false, 'stereoizer': 3, 'on': true, 'ambience': 1};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[908]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r1 = false;
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r0 = new Array(10);
        r2 = -1.5;
        r0[0] = r2;
        r2 = -1;
        r0[1] = r2;
        r2 = 0;
        r0[2] = r2;
        r2 = -1.2;
        r0[3] = r2;
        r0[4] = r2;
        r2 = 1.2;
        r0[5] = r2;
        r2 = 1.8;
        r0[6] = r2;
        r2 = 2.2;
        r0[7] = r2;
        r2 = 2.8;
        r0[8] = r2;
        r2 = 2.5;
        r0[9] = r2;
        r8 = {};
        r8['eqs'] = r0;
        r0 = true;
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r8 = {'attack': 0.03, 'on': true, 'pregain': 0, 'release': 0.15, 'range': 0, 'ratio': 1.2, 'postgain': 0, 'threshold': -8};
        r1['cmp'] = r8;
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r1['limiter'] = r3;
        r1['dly'] = r2;
         r2 = 'Vui tươiv2-instrumental se';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc', 'effect_name'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 2, 'sshaper': false, 'stereoizer': 6, 'on': true, 'ambience': 1};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[909]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r1 = false;
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r0 = new Array(10);
        r2 = -2;
        r0[0] = r2;
        r2 = -1.5;
        r0[1] = r2;
        r2 = -0.5;
        r0[2] = r2;
        r0[3] = r2;
        r2 = 0;
        r0[4] = r2;
        r2 = 1.5;
        r0[5] = r2;
        r2 = 2;
        r0[6] = r2;
        r2 = 2.5;
        r0[7] = r2;
        r0[8] = r2;
        r0[9] = r2;
        r8 = {};
        r8['eqs'] = r0;
        r0 = true;
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r8 = {'attack': 0.015, 'on': true, 'pregain': 0, 'release': 0.08, 'range': 0, 'ratio': 2.5, 'postgain': 1, 'threshold': -15};
        r1['cmp'] = r8;
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r1['limiter'] = r3;
        r1['dly'] = r2;
         r2 = 'Vui tươi-pop V2 se+pre';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 2, 'sshaper': false, 'ambience': 1, 'on': true, 'stereoizer': 3};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[910]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r1 = false;
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r0 = new Array(10);
        r2 = -0.5;
        r0[0] = r2;
        r2 = 0;
        r0[1] = r2;
        r2 = 0.5;
        r0[2] = r2;
        r2 = -2;
        r0[3] = r2;
        r0[4] = r2;
        r2 = 1.8;
        r0[5] = r2;
        r2 = 2.5;
        r0[6] = r2;
        r2 = 2.8;
        r0[7] = r2;
        r2 = 3.8;
        r0[8] = r2;
        r2 = 3.2;
        r0[9] = r2;
        r8 = {};
        r8['eqs'] = r0;
        r0 = true;
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r12;
        r1['alc'] = r11;
        r1['noise'] = r10;
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r8 = {'attack': 0.008, 'on': true, 'pregain': 0, 'release': 0.04, 'range': 0, 'ratio': 3, 'postgain': 2, 'threshold': -18};
        r1['cmp'] = r8;
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r1['limiter'] = r3;
        r1['dly'] = r2;
         r2 = 'Vui tươiv2-rhythm se';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'peq', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'eq', 'rvb', 'se', 'bt', 'noise', 'effect_name'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 2, 'sshaper': false, 'ambience': 1, 'on': true, 'stereoizer': 3};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[911]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r10 = {};
        r0 = false;
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r1 = [0, 0, 0, 0.5, 0, 1, 1, 0.6, 0, 0];
        r8['eqs'] = r1;
        r1 = true;
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r2 = new Array(8);
        r3 = {'q': 2, 'freq': 31, 'band': 0, 'type': 6, 'on': true, 'gain': 1.5};
        r2[0] = r3;
        r3 = {'q': 2, 'freq': 63, 'band': 1, 'type': 6, 'on': true, 'gain': 2};
        r2[1] = r3;
        r3 = {'q': 2, 'freq': 125, 'band': 2, 'type': 6, 'on': true, 'gain': 1};
        r2[2] = r3;
        r3 = {'q': 2, 'freq': 250, 'band': 3, 'type': 6, 'on': true, 'gain': 0.5};
        r2[3] = r3;
        r3 = {'q': 2, 'freq': 1000, 'band': 4, 'type': 6, 'on': true, 'gain': 1};
        r2[4] = r3;
        r3 = {'q': 2, 'freq': 2000, 'band': 5, 'type': 6, 'on': true, 'gain': -0.5};
        r2[5] = r3;
        r3 = {'q': 2, 'freq': 4000, 'band': 6, 'type': 6, 'on': true, 'gain': -1.5};
        r2[6] = r3;
        r3 = {'q': 2, 'freq': 8000, 'band': 7, 'type': 5, 'on': true, 'gain': -2};
        r2[7] = r3;
        r3 = {'f': null, 'gain': 0, 'on': true};
        r3['f'] = r2;
        r2 = {};
        r2['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r0 = {};
        r12 = {'pdelay': 135, 'hfdamping': 3.5, 'rshape': 58, 'density': 72, 'swidth': 130, 'q': 5, 'diffusion': 42, 'on': true, 'dtime': 1.2};
        r0['rvb'] = r12;
        r0['on'] = r1;
        r12 = {'pattern': 15, 'on': true, 'sdelay': 38, 'rsize': 75};
        r0['er'] = r12;
        r0['tc'] = r11;
        r11 = {'center': 0, 'lfe': 0};
        r0['il'] = r11;
        r11 = {'dry': 0, 'er': -30, 'rvb': -25};
        r0['ol'] = r11;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r10;
        r1['alc'] = r9;
        r9 = {'mix': 0.25, 'voices': 2, 'delay': 15, 'depth': 0.4, 'on': true, 'rate': 0.3};
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'lonely-pop';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'peq', 'rvb', 'se', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'effect_name'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1.5, 'sshaper': true, 'ambience': 5, 'on': true, 'stereoizer': 3};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[912]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r11 = {};
        r0 = false;
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(8);
        r2 = {'gain': 1.5, 'freq': 31, 'band': 0, 'q': 2, 'type': 6, 'on': true};
        r1[0] = r2;
        r2 = {'gain': 2, 'freq': 63, 'band': 1, 'q': 2, 'type': 6, 'on': true};
        r1[1] = r2;
        r2 = {'gain': 1, 'freq': 125, 'band': 2, 'q': 2, 'type': 6, 'on': true};
        r1[2] = r2;
        r2 = {'gain': 0.5, 'freq': 250, 'band': 3, 'q': 2, 'type': 6, 'on': true};
        r1[3] = r2;
        r2 = {'gain': 1, 'freq': 1000, 'band': 4, 'q': 2, 'type': 6, 'on': true};
        r1[4] = r2;
        r2 = {'gain': -0.5, 'freq': 2000, 'band': 5, 'q': 2, 'type': 6, 'on': true};
        r1[5] = r2;
        r2 = {'gain': -1.5, 'freq': 4000, 'band': 6, 'q': 2, 'type': 6, 'on': true};
        r1[6] = r2;
        r2 = {'gain': -2, 'freq': 8000, 'band': 7, 'q': 2, 'type': 5, 'on': true};
        r1[7] = r2;
        r3 = {'f': null, 'gain': 0, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r12 = {};
        r12['on'] = r0;
        r0 = {};
        r1 = {'pdelay': 135, 'hfdamping': 3.5, 'rshape': 58, 'density': 72, 'swidth': 140, 'q': 5, 'diffusion': 48, 'on': true, 'dtime': 1.5};
        r0['rvb'] = r1;
        r1 = true;
        r0['on'] = r1;
        r13 = {'pattern': 15, 'rsize': 75, 'on': true, 'sdelay': 38};
        r0['er'] = r13;
        r0['tc'] = r12;
        r12 = {'center': 0, 'lfe': 0};
        r0['il'] = r12;
        r12 = {'dry': 0, 'er': -30, 'rvb': -23};
        r0['ol'] = r12;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r11;
        r1['alc'] = r10;
        r1['noise'] = r9;
        r9 = {'mix': 0.25, 'voices': 2, 'delay': 15, 'depth': 0.4, 'on': true, 'rate': 0.3};
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'lonely-pop';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'peq', 'rvb', 'se', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'effect_name', 'noise'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1.5, 'sshaper': true, 'stereoizer': 4.5, 'on': true, 'ambience': 6};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[913]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r11 = {};
        r0 = false;
        r11['on'] = r0;
        r10 = {};
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(8);
        r2 = {'q': 2, 'freq': 31, 'band': 0, 'type': 6, 'on': true, 'gain': 1.5};
        r1[0] = r2;
        r2 = {'q': 2, 'freq': 63, 'band': 1, 'type': 6, 'on': true, 'gain': 2};
        r1[1] = r2;
        r2 = {'q': 2, 'freq': 125, 'band': 2, 'type': 6, 'on': true, 'gain': 1};
        r1[2] = r2;
        r2 = {'q': 2, 'freq': 250, 'band': 3, 'type': 6, 'on': true, 'gain': 0.5};
        r1[3] = r2;
        r2 = {'q': 2, 'freq': 1000, 'band': 4, 'type': 6, 'on': true, 'gain': 1};
        r1[4] = r2;
        r2 = {'q': 2, 'freq': 2000, 'band': 5, 'type': 6, 'on': true, 'gain': -0.5};
        r1[5] = r2;
        r2 = {'q': 2, 'freq': 4000, 'band': 6, 'type': 6, 'on': true, 'gain': -1.5};
        r1[6] = r2;
        r2 = {'q': 2, 'freq': 8000, 'band': 7, 'type': 5, 'on': true, 'gain': -2};
        r1[7] = r2;
        r3 = {'f': null, 'gain': 0, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r12 = {};
        r12['on'] = r0;
        r0 = {};
        r1 = {'pdelay': 135, 'hfdamping': 3.5, 'rshape': 58, 'density': 72, 'swidth': 130, 'q': 5, 'diffusion': 42, 'on': true, 'dtime': 1.2};
        r0['rvb'] = r1;
        r1 = true;
        r0['on'] = r1;
        r13 = {'pattern': 15, 'on': true, 'sdelay': 38, 'rsize': 75};
        r0['er'] = r13;
        r0['tc'] = r12;
        r12 = {'center': 0, 'lfe': 0};
        r0['il'] = r12;
        r12 = {'dry': 0, 'er': -30, 'rvb': -24};
        r0['ol'] = r12;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r11;
        r1['alc'] = r10;
        r1['noise'] = r9;
        r9 = {'mix': 0.25, 'voices': 2, 'delay': 15, 'depth': 0.4, 'on': true, 'rate': 0.3};
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.12, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'useable': true, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'lonely-pop';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'peq', 'rvb', 'se', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'effect_name', 'noise'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1.5, 'sshaper': true, 'stereoizer': 4, 'on': true, 'ambience': 5};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[914]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r10 = {};
        r0 = false;
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r0;
        r7 = {};
        r7['on'] = r0;
        r6 = {};
        r6['on'] = r0;
        r5 = {};
        r5['on'] = r0;
        r4 = {};
        r4['on'] = r0;
        r1 = new Array(8);
        r2 = {'q': 2, 'freq': 31, 'band': 0, 'type': 6, 'on': true, 'gain': 2};
        r1[0] = r2;
        r2 = {'q': 2, 'freq': 63, 'band': 1, 'type': 6, 'on': true, 'gain': 3};
        r1[1] = r2;
        r2 = {'q': 2, 'freq': 125, 'band': 2, 'type': 6, 'on': true, 'gain': 1};
        r1[2] = r2;
        r2 = {'q': 2, 'freq': 250, 'band': 3, 'type': 6, 'on': true, 'gain': 0.5};
        r1[3] = r2;
        r2 = {'q': 2, 'freq': 1000, 'band': 4, 'type': 6, 'on': true, 'gain': 1};
        r1[4] = r2;
        r2 = {'q': 2, 'freq': 2000, 'band': 5, 'type': 6, 'on': true, 'gain': -0.5};
        r1[5] = r2;
        r2 = {'q': 2, 'freq': 4000, 'band': 6, 'type': 6, 'on': true, 'gain': -0.5};
        r1[6] = r2;
        r2 = {'q': 2, 'freq': 8000, 'band': 7, 'type': 5, 'on': true, 'gain': -1};
        r1[7] = r2;
        r3 = {'f': null, 'gain': 0, 'on': true};
        r3['f'] = r1;
        r2 = {};
        r2['on'] = r0;
        r11 = {};
        r11['on'] = r0;
        r0 = {};
        r1 = {'pdelay': 135, 'hfdamping': 3.5, 'rshape': 58, 'density': 72, 'swidth': 130, 'q': 5, 'diffusion': 35, 'on': true, 'dtime': 0.9};
        r0['rvb'] = r1;
        r1 = true;
        r0['on'] = r1;
        r12 = {'pattern': 15, 'on': true, 'sdelay': 38, 'rsize': 75};
        r0['er'] = r12;
        r0['tc'] = r11;
        r11 = {'center': 0, 'lfe': 0};
        r0['il'] = r11;
        r11 = {'dry': 0, 'er': -30, 'rvb': -26};
        r0['ol'] = r11;
        r0['useable'] = r1;
        r1 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r0['rl'] = r1;
        r1 = {};
        r1['rotate'] = r10;
        r1['alc'] = r9;
        r9 = {'mix': 0.25, 'voices': 2, 'delay': 15, 'depth': 0.4, 'on': true, 'rate': 0.3};
        r1['chorus'] = r9;
        r1['eq'] = r8;
        r1['cmp'] = r7;
        r1['bt'] = r6;
        r1['flanger'] = r5;
        r1['spspatial'] = r4;
        r1['peq'] = r3;
        r3 = {'attack': 0.005, 'on': true, 'release': 0.08, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 2, 'threshold': -0.1};
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'lonely-pop';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'peq', 'rvb', 'se', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'effect_name'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 1.5, 'sshaper': true, 'stereoizer': 4, 'on': true, 'ambience': 5};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[915]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r1 = false;
        r12['on'] = r1;
        r2 = [0, 0, 0.6, 0];
        r0 = -0.8;
        r2[4] = r0;
        r0 = -0.6;
        r2[5] = r0;
        r2[6] = r0;
        r0 = 0.2;
        r2[7] = r0;
        r2[8] = r0;
        r2[9] = r0;
        r11 = {};
        r0 = true;
        r11['on'] = r0;
        r11['eqs'] = r2;
        r10 = {};
        r10['on'] = r1;
        r3 = new Array(3);
         r2 = {'name': 'Bộ lọc 1', 'band': 1, 'insert': 3, 'curve': 0, 'gain': -6, 'freq': 600, 'q': 1};
        r3[0] = r2;
         r2 = {'name': 'Bộ lọc 2', 'band': 2, 'insert': 3, 'curve': 2, 'gain': -6, 'freq': 7000, 'q': 1};
        r3[1] = r2;
         r2 = {'name': 'Bộ lọc 3', 'band': 3, 'insert': 0, 'curve': 0, 'gain': 0, 'freq': 1000, 'q': 1};
        r3[2] = r2;
        r2 = {};
        r2['on'] = r0;
        r2['f'] = r3;
        r9 = {};
        r3 = {'pdelay': 20, 'dtime': 2, 'hfdamping': 2.5, 'density': 50, 'rshape': 50, 'q': 6, 'diffusion': 60, 'swidth': 140, 'on': true};
        r9['rvb'] = r3;
        r9['on'] = r0;
        r3 = {'pattern': 20, 'rsize': 60, 'sdelay': 50, 'on': true};
        r9['er'] = r3;
        r9['tc'] = r2;
        r2 = {'center': 0, 'lfe': 0};
        r9['il'] = r2;
        r2 = {'dry': 0, 'er': -30, 'rvb': -25};
        r9['ol'] = r2;
        r9['useable'] = r0;
        r0 = {'front': 0, 'rear': 0, 'center': 0, 'lfe': 0};
        r9['rl'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['cmp'] = r12;
        r1['eq'] = r11;
        r1['peq'] = r10;
        r1['rvb'] = r9;
        r9 = {'on': true, 'threshold': -1, 'attack': 0.05, 'release': 0.1, 'outputgain': 0.5, 'infiniteRatio': true, 'ratio': 2};
        r1['limiter'] = r9;
        r1['se'] = r8;
        r1['bt'] = r7;
        r1['rotate'] = r6;
        r1['spspatial'] = r5;
        r1['alc'] = r4;
        r1['chorus'] = r3;
        r1['flanger'] = r2;
        r1['dly'] = r0;
         r0 = 'Nhớ nhung-folk';
        r1['effect_name'] = r0;
        r0 = ['cmp', 'eq', 'peq', 'rvb', 'se', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly'];
        r1['order'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[916]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r1 = false;
        r12['on'] = r1;
        r2 = [0, 0, 0.6, 0];
        r0 = -0.8;
        r2[4] = r0;
        r0 = -0.6;
        r2[5] = r0;
        r2[6] = r0;
        r0 = 0.2;
        r2[7] = r0;
        r2[8] = r0;
        r2[9] = r0;
        r11 = {};
        r0 = true;
        r11['on'] = r0;
        r11['eqs'] = r2;
        r10 = {};
        r10['on'] = r1;
        r3 = new Array(3);
         r2 = {'name': 'Bộ lọc 1', 'band': 1, 'insert': 3, 'curve': 0, 'gain': -6, 'freq': 600, 'q': 1};
        r3[0] = r2;
         r2 = {'name': 'Bộ lọc 2', 'band': 2, 'insert': 3, 'curve': 2, 'gain': -6, 'freq': 7000, 'q': 1};
        r3[1] = r2;
         r2 = {'name': 'Bộ lọc 3', 'band': 3, 'insert': 0, 'curve': 0, 'gain': 0, 'freq': 1000, 'q': 1};
        r3[2] = r2;
        r2 = {};
        r2['on'] = r0;
        r2['f'] = r3;
        r9 = {};
        r3 = {'pdelay': 20, 'dtime': 2.3, 'hfdamping': 2.3, 'density': 50, 'rshape': 50, 'q': 6, 'diffusion': 80, 'swidth': 160, 'on': true};
        r9['rvb'] = r3;
        r9['on'] = r0;
        r3 = {'pattern': 20, 'rsize': 60, 'sdelay': 50, 'on': true};
        r9['er'] = r3;
        r9['tc'] = r2;
        r2 = {'center': 0, 'lfe': 0};
        r9['il'] = r2;
        r2 = {'dry': 0, 'er': -30, 'rvb': -25};
        r9['ol'] = r2;
        r9['useable'] = r0;
        r0 = {'front': 0, 'rear': 0, 'center': 0, 'lfe': 0};
        r9['rl'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['cmp'] = r12;
        r1['eq'] = r11;
        r1['peq'] = r10;
        r1['rvb'] = r9;
        r9 = {'on': true, 'threshold': -1, 'attack': 0.05, 'release': 0.1, 'outputgain': 0.5, 'infiniteRatio': true, 'ratio': 2};
        r1['limiter'] = r9;
        r1['se'] = r8;
        r1['bt'] = r7;
        r1['rotate'] = r6;
        r1['spspatial'] = r5;
        r1['alc'] = r4;
        r1['chorus'] = r3;
        r1['flanger'] = r2;
        r1['dly'] = r0;
         r0 = 'Nhớ nhung-ins';
        r1['effect_name'] = r0;
        r0 = ['cmp', 'eq', 'peq', 'rvb', 'se', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly'];
        r1['order'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[917]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r1 = false;
        r12['on'] = r1;
        r2 = [0, 0, 0.4, 0];
        r0 = -0.8;
        r2[4] = r0;
        r2[5] = r0;
        r0 = -0.6;
        r2[6] = r0;
        r0 = 0.2;
        r2[7] = r0;
        r2[8] = r0;
        r2[9] = r0;
        r11 = {};
        r0 = true;
        r11['on'] = r0;
        r11['eqs'] = r2;
        r10 = {};
        r10['on'] = r1;
        r3 = new Array(3);
         r2 = {'name': 'Bộ lọc 1', 'band': 1, 'insert': 3, 'curve': 0, 'gain': -6, 'freq': 600, 'q': 1};
        r3[0] = r2;
         r2 = {'name': 'Bộ lọc 2', 'band': 2, 'insert': 3, 'curve': 2, 'gain': -6, 'freq': 7000, 'q': 1};
        r3[1] = r2;
         r2 = {'name': 'Bộ lọc 3', 'band': 3, 'insert': 0, 'curve': 0, 'gain': 0, 'freq': 1000, 'q': 1};
        r3[2] = r2;
        r2 = {};
        r2['on'] = r0;
        r2['f'] = r3;
        r9 = {};
        r3 = {'pdelay': 20, 'dtime': 2.2, 'hfdamping': 2.5, 'density': 50, 'rshape': 50, 'q': 6, 'diffusion': 60, 'swidth': 140, 'on': true};
        r9['rvb'] = r3;
        r9['on'] = r0;
        r3 = {'pattern': 20, 'rsize': 60, 'sdelay': 50, 'on': true};
        r9['er'] = r3;
        r9['tc'] = r2;
        r2 = {'center': 0, 'lfe': 0};
        r9['il'] = r2;
        r2 = {'dry': 0, 'er': -30, 'rvb': -23};
        r9['ol'] = r2;
        r9['useable'] = r0;
        r0 = {'front': 0, 'rear': 0, 'center': 0, 'lfe': 0};
        r9['rl'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['cmp'] = r12;
        r1['eq'] = r11;
        r1['peq'] = r10;
        r1['rvb'] = r9;
        r9 = {'on': true, 'threshold': -1, 'attack': 0.05, 'release': 0.1, 'outputgain': 0.5, 'infiniteRatio': true, 'ratio': 2};
        r1['limiter'] = r9;
        r1['se'] = r8;
        r1['bt'] = r7;
        r1['rotate'] = r6;
        r1['spspatial'] = r5;
        r1['alc'] = r4;
        r1['chorus'] = r3;
        r1['flanger'] = r2;
        r1['dly'] = r0;
         r0 = 'Nhớ nhung-pop';
        r1['effect_name'] = r0;
        r0 = ['cmp', 'eq', 'peq', 'rvb', 'se', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly'];
        r1['order'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[918]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r12 = {};
        r1 = false;
        r12['on'] = r1;
        r2 = [0, 0, 0.6, 0];
        r0 = -1;
        r2[4] = r0;
        r0 = -0.8;
        r2[5] = r0;
        r0 = -0.6;
        r2[6] = r0;
        r0 = 0.2;
        r2[7] = r0;
        r2[8] = r0;
        r2[9] = r0;
        r11 = {};
        r0 = true;
        r11['on'] = r0;
        r11['eqs'] = r2;
        r10 = {};
        r10['on'] = r1;
        r3 = new Array(3);
         r2 = {'name': 'Bộ lọc 1', 'band': 1, 'insert': 3, 'curve': 0, 'gain': -6, 'freq': 600, 'q': 1};
        r3[0] = r2;
         r2 = {'name': 'Bộ lọc 2', 'band': 2, 'insert': 3, 'curve': 2, 'gain': -6, 'freq': 7000, 'q': 1};
        r3[1] = r2;
         r2 = {'name': 'Bộ lọc 3', 'band': 3, 'insert': 0, 'curve': 0, 'gain': 0, 'freq': 1000, 'q': 1};
        r3[2] = r2;
        r2 = {};
        r2['on'] = r0;
        r2['f'] = r3;
        r9 = {};
        r3 = {'pdelay': 20, 'dtime': 2.2, 'hfdamping': 2.5, 'density': 50, 'rshape': 50, 'q': 6, 'diffusion': 60, 'swidth': 140, 'on': true};
        r9['rvb'] = r3;
        r9['on'] = r0;
        r3 = {'pattern': 20, 'rsize': 60, 'sdelay': 50, 'on': true};
        r9['er'] = r3;
        r9['tc'] = r2;
        r2 = {'center': 0, 'lfe': 0};
        r9['il'] = r2;
        r2 = {'dry': 0, 'er': -30, 'rvb': -25};
        r9['ol'] = r2;
        r9['useable'] = r0;
        r0 = {'front': 0, 'rear': 0, 'center': 0, 'lfe': 0};
        r9['rl'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['cmp'] = r12;
        r1['eq'] = r11;
        r1['peq'] = r10;
        r1['rvb'] = r9;
        r9 = {'on': true, 'threshold': -1, 'attack': 0.05, 'release': 0.1, 'outputgain': 0.5, 'infiniteRatio': true, 'ratio': 2};
        r1['limiter'] = r9;
        r1['se'] = r8;
        r1['bt'] = r7;
        r1['rotate'] = r6;
        r1['spspatial'] = r5;
        r1['alc'] = r4;
        r1['chorus'] = r3;
        r1['flanger'] = r2;
        r1['dly'] = r0;
         r0 = 'Nhớ nhung-rhythm';
        r1['effect_name'] = r0;
        r0 = ['cmp', 'eq', 'peq', 'rvb', 'se', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly'];
        r1['order'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[919]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r13 = {};
        r1 = false;
        r13['on'] = r1;
        r0 = [0, 0];
        r2 = -10;
        r0[2] = r2;
        r0[3] = r2;
        r2 = -20;
        r0[4] = r2;
        r2 = -16;
        r0[5] = r2;
        r2 = -30;
        r0[6] = r2;
        r2 = -25;
        r0[7] = r2;
        r2 = -40;
        r0[8] = r2;
        r2 = -36;
        r0[9] = r2;
        r2 = -50;
        r0[10] = r2;
        r2 = -46;
        r0[11] = r2;
        r2 = -70;
        r0[12] = r2;
        r0[13] = r2;
        r2 = -200;
        r0[14] = r2;
        r0[15] = r2;
        r12 = {'attack': 0.005, 'hold': 0.05, 'on': true, 'lookahead': 0, 'xyknee': 0, 'release': 0.1, 'lt': 0.5, 'type': 0, 'xypoints': null, 'minisize': 64, 'st': 0.02};
        r12['xypoints'] = r0;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {'eqs': null, 'useable': true, 'on': true};
        r0 = [0.2, 2, 2.6, 3, 2.6, 2, 1.6, 2, 2, 0.2];
        r9['eqs'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r15 = {};
        r15['on'] = r1;
        r14 = {};
        r14['on'] = r1;
        r2 = {};
        r0 = {'pdelay': 40, 'hfdamping': 1.8, 'rshape': 80, 'density': 40, 'swidth': 180, 'q': 6, 'diffusion': 30, 'on': true, 'dtime': 1};
        r2['rvb'] = r0;
        r0 = true;
        r2['on'] = r0;
        r2['er'] = r15;
        r2['tc'] = r14;
        r14 = {'center': 0, 'lfe': 0};
        r2['il'] = r14;
        r14 = {'dry': 0, 'er': -30, 'rvb': -25};
        r2['ol'] = r14;
        r2['useable'] = r0;
        r0 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r2['rl'] = r0;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r13;
        r1['alc'] = r12;
        r1['noise'] = r11;
        r1['chorus'] = r10;
        r1['eq'] = r9;
        r1['cmp'] = r8;
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r4 = {'attack': 0.005, 'on': true, 'release': 0.1, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 4, 'threshold': -0.1};
        r1['limiter'] = r4;
        r1['dly'] = r3;
         r3 = 'Nhiệt huyết-dân ca';
        r1['effect_name'] = r3;
        r3 = ['cmp', 'eq', 'se', 'peq', 'rvb', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'noise', 'effect_name'];
        r1['order'] = r3;
        r1['rvb'] = r2;
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[920]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r13 = {};
        r1 = false;
        r13['on'] = r1;
        r12 = {};
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {'eqs': null, 'useable': true, 'on': true};
        r0 = [0.2, 2, 2.6, 3, 2.6, 2, 1.6, 2, 2, 0.2];
        r9['eqs'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r13;
        r1['alc'] = r12;
        r1['noise'] = r11;
        r1['chorus'] = r10;
        r1['eq'] = r9;
        r1['cmp'] = r8;
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r4 = {'attack': 0.005, 'on': true, 'release': 0.1, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 4, 'threshold': -0.1};
        r1['limiter'] = r4;
        r1['dly'] = r3;
         r3 = 'Nhiệt huyết-không lời';
        r1['effect_name'] = r3;
        r3 = ['cmp', 'eq', 'se', 'peq', 'rvb', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'noise', 'effect_name'];
        r1['order'] = r3;
        r1['rvb'] = r2;
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[921]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r13 = {};
        r1 = false;
        r13['on'] = r1;
        r0 = [0, 0];
        r2 = -10;
        r0[2] = r2;
        r2 = -8;
        r0[3] = r2;
        r2 = -20;
        r0[4] = r2;
        r2 = -16;
        r0[5] = r2;
        r2 = -30;
        r0[6] = r2;
        r2 = -25;
        r0[7] = r2;
        r2 = -40;
        r0[8] = r2;
        r2 = -36;
        r0[9] = r2;
        r2 = -50;
        r0[10] = r2;
        r2 = -46;
        r0[11] = r2;
        r2 = -70;
        r0[12] = r2;
        r0[13] = r2;
        r2 = -200;
        r0[14] = r2;
        r0[15] = r2;
        r12 = {'attack': 0.005, 'hold': 0.05, 'on': true, 'lookahead': 0, 'xyknee': 0, 'release': 0.1, 'lt': 0.5, 'type': 0, 'xypoints': null, 'minisize': 64, 'st': 0.02};
        r12['xypoints'] = r0;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {'eqs': null, 'useable': true, 'on': true};
        r0 = [0.2, 2, 2.6, 3, 2.6, 2, 1.6, 2, 2, 0.2];
        r9['eqs'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r13;
        r1['alc'] = r12;
        r1['noise'] = r11;
        r1['chorus'] = r10;
        r1['eq'] = r9;
        r1['cmp'] = r8;
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r4 = {'attack': 0.005, 'on': true, 'release': 0.1, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 4, 'threshold': -0.1};
        r1['limiter'] = r4;
        r1['dly'] = r3;
        r3 = 'Sôi động-Pop';
        r1['effect_name'] = r3;
        r3 = ['cmp', 'eq', 'se', 'peq', 'rvb', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'noise', 'effect_name'];
        r1['order'] = r3;
        r1['rvb'] = r2;
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[922]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r13 = {};
        r1 = false;
        r13['on'] = r1;
        r0 = [0, 0];
        r2 = -10;
        r0[2] = r2;
        r0[3] = r2;
        r2 = -20;
        r0[4] = r2;
        r2 = -16;
        r0[5] = r2;
        r2 = -30;
        r0[6] = r2;
        r2 = -25;
        r0[7] = r2;
        r2 = -40;
        r0[8] = r2;
        r2 = -36;
        r0[9] = r2;
        r2 = -50;
        r0[10] = r2;
        r2 = -46;
        r0[11] = r2;
        r2 = -70;
        r0[12] = r2;
        r0[13] = r2;
        r2 = -200;
        r0[14] = r2;
        r0[15] = r2;
        r12 = {'attack': 0.005, 'hold': 0.05, 'on': true, 'lookahead': 0, 'xyknee': 0, 'release': 0.1, 'lt': 0.5, 'type': 0, 'xypoints': null, 'minisize': 64, 'st': 0.02};
        r12['xypoints'] = r0;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r0 = [0.2, 2.4, 3, 3, 2.6, 2, 1.4, 1.8, 1.8, 0.2];
        r9['eqs'] = r0;
        r0 = true;
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r13;
        r1['alc'] = r12;
        r1['noise'] = r11;
        r1['chorus'] = r10;
        r1['eq'] = r9;
        r1['cmp'] = r8;
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r4 = {'attack': 0.005, 'on': true, 'release': 0.1, 'outputgain': 0, 'infiniteRatio': true, 'ratio': 4, 'threshold': -0.2};
        r1['limiter'] = r4;
        r1['dly'] = r3;
        r3 = 'Sôi động-Nhịp điệu';
        r1['effect_name'] = r3;
        r3 = ['cmp', 'eq', 'se', 'peq', 'rvb', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'noise', 'effect_name'];
        r1['order'] = r3;
        r1['rvb'] = r2;
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[923]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r13 = {};
        r1 = false;
        r13['on'] = r1;
        r12 = {};
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r0 = [0, 0.6, 0.8, 0];
        r2 = -1;
        r0[4] = r2;
        r2 = 1.1;
        r0[5] = r2;
        r2 = -0.5;
        r0[6] = r2;
        r2 = -0.4;
        r0[7] = r2;
        r0[8] = r2;
        r2 = -0.2;
        r0[9] = r2;
        r9 = {};
        r9['eqs'] = r0;
        r0 = true;
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r13;
        r1['alc'] = r12;
        r1['noise'] = r11;
        r1['chorus'] = r10;
        r1['eq'] = r9;
        r1['cmp'] = r8;
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Thư giãn';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 4.5, 'sshaper': false, 'ambience': 2, 'on': true, 'stereoizer': 2.5};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[924]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r13 = {};
        r1 = false;
        r13['on'] = r1;
        r12 = {};
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r0 = [0, 0.6, 0.8];
        r2 = -0.2;
        r0[3] = r2;
        r3 = -1.5;
        r0[4] = r3;
        r3 = 0.7;
        r0[5] = r3;
        r3 = -0.5;
        r0[6] = r3;
        r3 = -0.4;
        r0[7] = r3;
        r3 = 0;
        r0[8] = r3;
        r0[9] = r2;
        r9 = {};
        r9['eqs'] = r0;
        r0 = true;
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r13;
        r1['alc'] = r12;
        r1['noise'] = r11;
        r1['chorus'] = r10;
        r1['eq'] = r9;
        r1['cmp'] = r8;
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Thư giãn';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 4.5, 'sshaper': false, 'ambience': 2, 'on': true, 'stereoizer': 3.1};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[925]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r13 = {};
        r1 = false;
        r13['on'] = r1;
        r12 = {};
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r0 = [0, 0.6, 0.6];
        r2 = -0.2;
        r0[3] = r2;
        r3 = -0.9;
        r0[4] = r3;
        r3 = 1.1;
        r0[5] = r3;
        r3 = -0.5;
        r0[6] = r3;
        r3 = -0.4;
        r0[7] = r3;
        r0[8] = r3;
        r0[9] = r2;
        r9 = {};
        r9['eqs'] = r0;
        r0 = true;
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r13;
        r1['alc'] = r12;
        r1['noise'] = r11;
        r1['chorus'] = r10;
        r1['eq'] = r9;
        r1['cmp'] = r8;
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Thư giãn';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 5, 'sshaper': false, 'ambience': 2, 'on': true, 'stereoizer': 2.3};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[926]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r13 = {};
        r1 = false;
        r13['on'] = r1;
        r12 = {};
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r10 = {};
        r10['on'] = r1;
        r0 = [0, 0.6, 0.8, 0];
        r2 = -1.3;
        r0[4] = r2;
        r2 = 1.1;
        r0[5] = r2;
        r2 = -0.5;
        r0[6] = r2;
        r2 = -0.4;
        r0[7] = r2;
        r0[8] = r2;
        r2 = -0.2;
        r0[9] = r2;
        r9 = {};
        r9['eqs'] = r0;
        r0 = true;
        r9['on'] = r0;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r13;
        r1['alc'] = r12;
        r1['noise'] = r11;
        r1['chorus'] = r10;
        r1['eq'] = r9;
        r1['cmp'] = r8;
        r1['bt'] = r7;
        r1['flanger'] = r6;
        r1['spspatial'] = r5;
        r1['peq'] = r4;
        r1['limiter'] = r3;
        r1['dly'] = r2;
        r2 = 'Thư giãn';
        r1['effect_name'] = r2;
        r2 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r2;
        r1['rvb'] = r0;
        r0 = {'presence': 4, 'sshaper': false, 'ambience': 2, 'on': true, 'stereoizer': 2};
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[927]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r14 = {};
        r1 = false;
        r14['on'] = r1;
        r13 = {};
        r13['on'] = r1;
        r12 = {};
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r0 = new Array(10);
        r2 = -2;
        r0[0] = r2;
        r2 = 0.8;
        r0[1] = r2;
        r2 = 1;
        r0[2] = r2;
        r3 = 2;
        r0[3] = r3;
        r3 = 1.6;
        r0[4] = r3;
        r0[5] = r2;
        r2 = -1;
        r0[6] = r2;
        r2 = -1.6;
        r0[7] = r2;
        r2 = 1.4;
        r0[8] = r2;
        r2 = 0.2;
        r0[9] = r2;
        r10 = {};
        r10['eqs'] = r0;
        r0 = true;
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r15 = {};
        r15['on'] = r1;
        r2 = {};
        r16 = {'pdelay': 70, 'hfdamping': 3, 'rshape': 100, 'density': 50, 'swidth': 180, 'q': 10, 'diffusion': 100, 'on': true, 'dtime': 2};
        r2['rvb'] = r16;
        r2['on'] = r0;
        r16 = {'pattern': 15, 'on': true, 'sdelay': 30, 'rsize': 100};
        r2['er'] = r16;
        r2['tc'] = r15;
        r15 = {'center': 0, 'lfe': 0};
        r2['il'] = r15;
        r15 = {'dry': 0, 'er': -30, 'rvb': -25};
        r2['ol'] = r15;
        r2['useable'] = r0;
        r0 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r2['rl'] = r0;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r14;
        r1['alc'] = r13;
        r1['noise'] = r12;
        r1['chorus'] = r11;
        r1['eq'] = r10;
        r1['cmp'] = r9;
        r1['bt'] = r8;
        r1['flanger'] = r7;
        r1['spspatial'] = r6;
        r1['peq'] = r5;
        r1['limiter'] = r4;
        r1['dly'] = r3;
        r3 = 'Lãng mạn-Dân ca';
        r1['effect_name'] = r3;
        r3 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r3;
        r1['rvb'] = r2;
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[928]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r14 = {};
        r1 = false;
        r14['on'] = r1;
        r13 = {};
        r13['on'] = r1;
        r12 = {};
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r0 = new Array(10);
        r2 = -2;
        r0[0] = r2;
        r2 = 0.8;
        r0[1] = r2;
        r2 = 1;
        r0[2] = r2;
        r3 = 2;
        r0[3] = r3;
        r3 = 1.6;
        r0[4] = r3;
        r0[5] = r2;
        r2 = -1;
        r0[6] = r2;
        r2 = -1.6;
        r0[7] = r2;
        r2 = 1.4;
        r0[8] = r2;
        r2 = 0.2;
        r0[9] = r2;
        r10 = {};
        r10['eqs'] = r0;
        r0 = true;
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r14;
        r1['alc'] = r13;
        r1['noise'] = r12;
        r1['chorus'] = r11;
        r1['eq'] = r10;
        r1['cmp'] = r9;
        r1['bt'] = r8;
        r1['flanger'] = r7;
        r1['spspatial'] = r6;
        r1['peq'] = r5;
        r1['limiter'] = r4;
        r1['dly'] = r3;
        r3 = 'Lãng mạn-Nhạc không lời';
        r1['effect_name'] = r3;
        r3 = ['cmp', 'eq', 'se', 'peq', 'rvb', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'noise'];
        r1['order'] = r3;
        r1['rvb'] = r2;
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[929]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r14 = {};
        r1 = false;
        r14['on'] = r1;
        r13 = {};
        r13['on'] = r1;
        r12 = {};
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r0 = new Array(10);
        r2 = -2;
        r0[0] = r2;
        r2 = 0.8;
        r0[1] = r2;
        r2 = 1;
        r0[2] = r2;
        r3 = 2;
        r0[3] = r3;
        r3 = 1.6;
        r0[4] = r3;
        r0[5] = r2;
        r2 = -1;
        r0[6] = r2;
        r2 = -1.6;
        r0[7] = r2;
        r2 = 1.4;
        r0[8] = r2;
        r2 = 0.2;
        r0[9] = r2;
        r10 = {};
        r10['eqs'] = r0;
        r0 = true;
        r10['on'] = r0;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r15 = {};
        r15['on'] = r1;
        r2 = {};
        r16 = {'pdelay': 30, 'hfdamping': 3, 'rshape': 80, 'density': 30, 'swidth': 120, 'q': 10, 'diffusion': 80, 'on': true, 'dtime': 1.2};
        r2['rvb'] = r16;
        r2['on'] = r0;
        r16 = {'pattern': 16, 'on': true, 'sdelay': 30, 'rsize': 80};
        r2['er'] = r16;
        r2['tc'] = r15;
        r15 = {'center': 0, 'lfe': 0};
        r2['il'] = r15;
        r15 = {'dry': 0, 'er': -30, 'rvb': -25};
        r2['ol'] = r15;
        r2['useable'] = r0;
        r0 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r2['rl'] = r0;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r14;
        r1['alc'] = r13;
        r1['noise'] = r12;
        r1['chorus'] = r11;
        r1['eq'] = r10;
        r1['cmp'] = r9;
        r1['bt'] = r8;
        r1['flanger'] = r7;
        r1['spspatial'] = r6;
        r1['peq'] = r5;
        r1['limiter'] = r4;
        r1['dly'] = r3;
        r3 = 'Lãng mạn-Pop';
        r1['effect_name'] = r3;
        r3 = ['cmp', 'eq', 'se', 'peq', 'rvb', 'bt', 'rotate', 'spspatial', 'alc', 'chorus', 'flanger', 'dly', 'noise', 'effect_name'];
        r1['order'] = r3;
        r1['rvb'] = r2;
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[930]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r14 = {};
        r1 = false;
        r14['on'] = r1;
        r13 = {};
        r13['on'] = r1;
        r12 = {};
        r12['on'] = r1;
        r11 = {};
        r11['on'] = r1;
        r0 = new Array(10);
        r2 = -2;
        r0[0] = r2;
        r2 = 0.8;
        r0[1] = r2;
        r2 = 1;
        r0[2] = r2;
        r3 = 2;
        r0[3] = r3;
        r3 = 1.6;
        r0[4] = r3;
        r0[5] = r2;
        r2 = -1;
        r0[6] = r2;
        r2 = -1.6;
        r0[7] = r2;
        r2 = 1.4;
        r0[8] = r2;
        r2 = 0.2;
        r0[9] = r2;
        r10 = {'eqs': null, 'useable': true, 'on': true};
        r10['eqs'] = r0;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r15 = {};
        r15['on'] = r1;
        r2 = {};
        r0 = {'pdelay': 60, 'hfdamping': 2, 'rshape': 80, 'density': 30, 'swidth': 180, 'q': 8, 'diffusion': 100, 'on': true, 'dtime': 1.2};
        r2['rvb'] = r0;
        r0 = true;
        r2['on'] = r0;
        r16 = {'pattern': 16, 'on': true, 'sdelay': 30, 'rsize': 100};
        r2['er'] = r16;
        r2['tc'] = r15;
        r15 = {'center': 0, 'lfe': 0};
        r2['il'] = r15;
        r15 = {'dry': 0, 'er': -30, 'rvb': -25};
        r2['ol'] = r15;
        r2['useable'] = r0;
        r0 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r2['rl'] = r0;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rotate'] = r14;
        r1['alc'] = r13;
        r1['noise'] = r12;
        r1['chorus'] = r11;
        r1['eq'] = r10;
        r1['cmp'] = r9;
        r1['bt'] = r8;
        r1['flanger'] = r7;
        r1['spspatial'] = r6;
        r1['peq'] = r5;
        r1['limiter'] = r4;
        r1['dly'] = r3;
        r3 = 'Lãng mạn-Nhịp điệu';
        r1['effect_name'] = r3;
        r3 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r3;
        r1['rvb'] = r2;
        r1['se'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[931]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 0.8, 'gain': -5, 'freq': 500, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 0.8, 'gain': -1, 'freq': 2500, 'curve': 1, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 3, 'band': 3, 'q': 0.7, 'gain': -3, 'freq': 7000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r1 = {};
        r1['f'] = r0;
        r0 = true;
        r1['on'] = r0;
        r9 = {};
        r2 = {'pdelay': 60, 'hfdamping': 4, 'rshape': 60, 'density': 64, 'swidth': 120, 'q': 4, 'diffusion': 72, 'on': true, 'dtime': 2};
        r9['rvb'] = r2;
        r9['on'] = r0;
        r2 = {'pattern': 10, 'rsize': 60, 'on': true, 'sdelay': 15};
        r9['er'] = r2;
        r9['tc'] = r1;
        r1 = {'center': 0, 'lfe': -96.3};
        r9['il'] = r1;
        r1 = {'dry': 0, 'er': -30, 'rvb': -23};
        r9['ol'] = r1;
        r9['useable'] = r0;
        r0 = {'lfe': -96.3, 'front': 0, 'rear': -2, 'center': -1};
        r9['rl'] = r0;
        r8 = {};
        r1 = false;
        r8['on'] = r1;
        r0 = new Array(9);
        r2 = {'type': 1, 'freq': 35, 'band': 0, 'q': 0.8, 'on': true, 'gain': 0};
        r0[0] = r2;
        r2 = {'type': 6, 'freq': 100, 'band': 1, 'q': 3, 'on': true, 'gain': 1};
        r0[1] = r2;
        r2 = {'type': 6, 'freq': 200, 'band': 2, 'q': 3, 'on': true, 'gain': 0.4};
        r0[2] = r2;
        r2 = {'type': 6, 'freq': 4000, 'band': 3, 'q': 2.5, 'on': true, 'gain': -2};
        r0[3] = r2;
        r2 = {'type': 6, 'freq': 3000, 'band': 4, 'q': 3, 'on': true, 'gain': -0.2};
        r0[4] = r2;
        r2 = {'type': 6, 'freq': 1500, 'band': 5, 'q': 2, 'on': true, 'gain': 1.3};
        r0[5] = r2;
        r2 = {'type': 6, 'freq': 8000, 'band': 6, 'q': 1, 'on': true, 'gain': -0.4};
        r0[6] = r2;
        r2 = {'band': 7, 'on': false};
        r0[7] = r2;
        r2 = {'band': 8, 'on': false};
        r0[8] = r2;
        r7 = {'f': null, 'gain': 0, 'on': true};
        r7['f'] = r0;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rvb'] = r9;
        r9 = {'presence': 2, 'sshaper': true, 'ambience': 5, 'on': true, 'stereoizer': 0.2};
        r1['se'] = r9;
        r1['rotate'] = r8;
        r1['peq'] = r7;
        r1['limiter'] = r6;
        r1['spspatial'] = r5;
        r1['bt'] = r4;
        r1['alc'] = r3;
        r3 = 'Buồn 2';
        r1['effect_name'] = r3;
        r1['eq'] = r2;
        r1['cmp'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[932]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 0.8, 'gain': -5, 'freq': 500, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 0.8, 'gain': -1, 'freq': 2500, 'curve': 1, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 3, 'band': 3, 'q': 0.7, 'gain': -3, 'freq': 7000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r1 = {};
        r1['f'] = r0;
        r0 = true;
        r1['on'] = r0;
        r9 = {};
        r2 = {'pdelay': 60, 'hfdamping': 4, 'rshape': 60, 'density': 64, 'swidth': 120, 'q': 4, 'diffusion': 72, 'on': true, 'dtime': 2};
        r9['rvb'] = r2;
        r9['on'] = r0;
        r2 = {'pattern': 10, 'rsize': 60, 'on': true, 'sdelay': 15};
        r9['er'] = r2;
        r9['tc'] = r1;
        r1 = {'center': 0, 'lfe': -96.3};
        r9['il'] = r1;
        r1 = {'dry': 0, 'er': -30, 'rvb': -23};
        r9['ol'] = r1;
        r9['useable'] = r0;
        r0 = {'lfe': -96.3, 'front': 0, 'rear': -2, 'center': -1};
        r9['rl'] = r0;
        r8 = {};
        r1 = false;
        r8['on'] = r1;
        r0 = new Array(9);
        r2 = {'type': 1, 'freq': 35, 'band': 0, 'q': 0.8, 'on': true, 'gain': 0};
        r0[0] = r2;
        r2 = {'type': 6, 'freq': 100, 'band': 1, 'q': 3, 'on': true, 'gain': 1};
        r0[1] = r2;
        r2 = {'type': 6, 'freq': 200, 'band': 2, 'q': 3, 'on': true, 'gain': 0.4};
        r0[2] = r2;
        r2 = {'type': 6, 'freq': 4000, 'band': 3, 'q': 2.5, 'on': true, 'gain': -2};
        r0[3] = r2;
        r2 = {'type': 6, 'freq': 3000, 'band': 4, 'q': 3, 'on': true, 'gain': -0.6};
        r0[4] = r2;
        r2 = {'type': 6, 'freq': 1500, 'band': 5, 'q': 2, 'on': true, 'gain': 0.3};
        r0[5] = r2;
        r2 = {'type': 6, 'freq': 8000, 'band': 6, 'q': 1, 'on': true, 'gain': -0.4};
        r0[6] = r2;
        r2 = {'band': 7, 'on': false};
        r0[7] = r2;
        r2 = {'band': 8, 'on': false};
        r0[8] = r2;
        r7 = {'f': null, 'gain': 0, 'on': true};
        r7['f'] = r0;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rvb'] = r9;
        r9 = {'presence': 4, 'sshaper': true, 'ambience': 5, 'on': true, 'stereoizer': 0.5};
        r1['se'] = r9;
        r1['rotate'] = r8;
        r1['peq'] = r7;
        r1['limiter'] = r6;
        r1['spspatial'] = r5;
        r1['bt'] = r4;
        r1['alc'] = r3;
        r3 = 'Buồn 2';
        r1['effect_name'] = r3;
        r1['eq'] = r2;
        r1['cmp'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[933]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 0.8, 'gain': -5, 'freq': 500, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 0.8, 'gain': -1, 'freq': 2500, 'curve': 1, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 3, 'band': 3, 'q': 0.7, 'gain': -3, 'freq': 7000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r1 = {};
        r1['f'] = r0;
        r0 = true;
        r1['on'] = r0;
        r9 = {};
        r2 = {'pdelay': 60, 'hfdamping': 4, 'rshape': 60, 'density': 64, 'swidth': 120, 'q': 4, 'diffusion': 72, 'on': true, 'dtime': 2};
        r9['rvb'] = r2;
        r9['on'] = r0;
        r2 = {'pattern': 10, 'rsize': 60, 'on': true, 'sdelay': 15};
        r9['er'] = r2;
        r9['tc'] = r1;
        r1 = {'center': 0, 'lfe': -96.3};
        r9['il'] = r1;
        r1 = {'dry': 0, 'er': -30, 'rvb': -23};
        r9['ol'] = r1;
        r9['useable'] = r0;
        r0 = {'lfe': -96.3, 'front': 0, 'rear': -2, 'center': -1};
        r9['rl'] = r0;
        r8 = {};
        r1 = false;
        r8['on'] = r1;
        r0 = new Array(9);
        r2 = {'type': 1, 'freq': 35, 'band': 0, 'q': 0.8, 'on': true, 'gain': 0};
        r0[0] = r2;
        r2 = {'type': 6, 'freq': 100, 'band': 1, 'q': 3, 'on': true, 'gain': 1};
        r0[1] = r2;
        r2 = {'type': 6, 'freq': 200, 'band': 2, 'q': 3, 'on': true, 'gain': 0.4};
        r0[2] = r2;
        r2 = {'type': 6, 'freq': 4000, 'band': 3, 'q': 2.5, 'on': true, 'gain': -2};
        r0[3] = r2;
        r2 = {'type': 6, 'freq': 3000, 'band': 4, 'q': 3, 'on': true, 'gain': -0.6};
        r0[4] = r2;
        r2 = {'type': 6, 'freq': 1500, 'band': 5, 'q': 2, 'on': true, 'gain': 1.3};
        r0[5] = r2;
        r2 = {'type': 6, 'freq': 8000, 'band': 6, 'q': 1, 'on': true, 'gain': -0.4};
        r0[6] = r2;
        r2 = {'band': 7, 'on': false};
        r0[7] = r2;
        r2 = {'band': 8, 'on': false};
        r0[8] = r2;
        r7 = {'f': null, 'gain': 0, 'on': true};
        r7['f'] = r0;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rvb'] = r9;
        r9 = {'presence': 3, 'sshaper': true, 'ambience': 5, 'on': true, 'stereoizer': 0.3};
        r1['se'] = r9;
        r1['rotate'] = r8;
        r1['peq'] = r7;
        r1['limiter'] = r6;
        r1['spspatial'] = r5;
        r1['bt'] = r4;
        r1['alc'] = r3;
        r3 = 'Buồn 2';
        r1['effect_name'] = r3;
        r1['eq'] = r2;
        r1['cmp'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[934]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r0 = new Array(3);
        r1 = {'insert': 3, 'band': 1, 'q': 0.8, 'gain': -5, 'freq': 500, 'curve': 0, 'name': 'Bộ lọc 1'};
        r0[0] = r1;
        r1 = {'insert': 3, 'band': 2, 'q': 0.8, 'gain': -1, 'freq': 2500, 'curve': 1, 'name': 'Bộ lọc 2'};
        r0[1] = r1;
        r1 = {'insert': 3, 'band': 3, 'q': 0.7, 'gain': -3, 'freq': 7000, 'curve': 2, 'name': 'Bộ lọc 3'};
        r0[2] = r1;
        r1 = {};
        r1['f'] = r0;
        r0 = true;
        r1['on'] = r0;
        r9 = {};
        r2 = {'pdelay': 60, 'hfdamping': 4, 'rshape': 60, 'density': 64, 'swidth': 120, 'q': 4, 'diffusion': 72, 'on': true, 'dtime': 2};
        r9['rvb'] = r2;
        r9['on'] = r0;
        r2 = {'pattern': 10, 'rsize': 60, 'on': true, 'sdelay': 15};
        r9['er'] = r2;
        r9['tc'] = r1;
        r1 = {'center': 0, 'lfe': -96.3};
        r9['il'] = r1;
        r1 = {'dry': 0, 'er': -30, 'rvb': -26};
        r9['ol'] = r1;
        r9['useable'] = r0;
        r0 = {'lfe': -96.3, 'front': 0, 'rear': -2, 'center': -1};
        r9['rl'] = r0;
        r8 = {};
        r1 = false;
        r8['on'] = r1;
        r0 = new Array(9);
        r2 = {'type': 1, 'freq': 35, 'band': 0, 'q': 0.8, 'on': true, 'gain': 0};
        r0[0] = r2;
        r2 = {'type': 6, 'freq': 100, 'band': 1, 'q': 3, 'on': true, 'gain': 1};
        r0[1] = r2;
        r2 = {'type': 6, 'freq': 200, 'band': 2, 'q': 3, 'on': true, 'gain': 0.4};
        r0[2] = r2;
        r2 = {'type': 6, 'freq': 4000, 'band': 3, 'q': 2.5, 'on': true, 'gain': -2};
        r0[3] = r2;
        r2 = {'type': 6, 'freq': 3000, 'band': 4, 'q': 3, 'on': true, 'gain': -0.6};
        r0[4] = r2;
        r2 = {'type': 6, 'freq': 1500, 'band': 5, 'q': 2, 'on': true, 'gain': 1.3};
        r0[5] = r2;
        r2 = {'type': 6, 'freq': 8000, 'band': 6, 'q': 1, 'on': true, 'gain': -0.4};
        r0[6] = r2;
        r2 = {'band': 7, 'on': false};
        r0[7] = r2;
        r2 = {'band': 8, 'on': false};
        r0[8] = r2;
        r7 = {'f': null, 'gain': 0, 'on': true};
        r7['f'] = r0;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['rvb'] = r9;
        r9 = {'presence': 2, 'sshaper': true, 'ambience': 5, 'on': true, 'stereoizer': 0.2};
        r1['se'] = r9;
        r1['rotate'] = r8;
        r1['peq'] = r7;
        r1['limiter'] = r6;
        r1['spspatial'] = r5;
        r1['bt'] = r4;
        r1['alc'] = r3;
         r3 = 'Buồn 2';
        r1['effect_name'] = r3;
        r1['eq'] = r2;
        r1['cmp'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[935]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r13 = {};
        r1 = false;
        r13['on'] = r1;
        r12 = {};
        r0 = true;
        r12['on'] = r0;
        r2 = [0, 0, 1, 0.2, 0, 0.6, 0.6, 1.6, 1.6, 0];
        r12['eqs'] = r2;
        r2 = new Array(9);
        r3 = {'band': 0, 'type': 6, 'gain': 1.5, 'freq': 3000, 'q': 1, 'on': true};
        r2[0] = r3;
        r3 = {'band': 1, 'type': 6, 'gain': 1.2, 'freq': 220, 'q': 0.7, 'on': true};
        r2[1] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[2] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[3] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[4] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[5] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[6] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[7] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[8] = r3;
        r11 = {'on': true, 'gain': 1.4};
        r11['f'] = r2;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r10 = {};
        r4 = {'pdelay': 21, 'dtime': 1.1, 'hfdamping': 6, 'density': 58, 'rshape': 25, 'q': 6, 'diffusion': 20, 'swidth': 20, 'on': true};
        r10['rvb'] = r4;
        r10['on'] = r0;
        r10['er'] = r3;
        r10['tc'] = r2;
        r2 = {'center': 0, 'lfe': 0};
        r10['il'] = r2;
        r2 = {'dry': 0, 'er': -32, 'rvb': -26};
        r10['ol'] = r2;
        r10['useable'] = r0;
        r0 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r10['rl'] = r0;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['cmp'] = r13;
        r1['eq'] = r12;
        r1['peq'] = r11;
        r1['rvb'] = r10;
        r1['limiter'] = r9;
        r1['se'] = r8;
        r1['bt'] = r7;
        r1['rotate'] = r6;
        r6 = {'on': true, 'upmix': 1, 'lronoff': 0, 'lrangle': 10, 'otheronoff': 0, 'global': -0.2, 'center': 0.6, 'lfe': 0, 'fl': -0.2, 'fr': -0.2, 'left': 0, 'right': 0, 'rl': 0, 'rr': 0, 'ul': 0, 'ur': 0};
        r1['spspatial'] = r6;
        r1['alc'] = r5;
        r1['chorus'] = r4;
        r1['flanger'] = r3;
        r1['dly'] = r2;
        r1['noise'] = r0;
         r0 = 'Ngọt ngào 2-folk';
        r1['effect_name'] = r0;
        r0 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[936]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r14 = {};
        r1 = false;
        r14['on'] = r1;
        r13 = {};
        r0 = true;
        r13['on'] = r0;
        r2 = [0, 0, 0, 0.6, 0, 0.6, 0.4, 1.6, 1.8, 0];
        r13['eqs'] = r2;
        r2 = new Array(9);
        r3 = {'band': 0, 'type': 6, 'gain': 1.5, 'freq': 3000, 'q': 1, 'on': true};
        r2[0] = r3;
        r3 = {'band': 1, 'type': 6, 'gain': 1.2, 'freq': 220, 'q': 0.7, 'on': true};
        r2[1] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[2] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[3] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[4] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[5] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[6] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[7] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[8] = r3;
        r12 = {'on': true, 'gain': 1.6};
        r12['f'] = r2;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r11 = {};
        r4 = {'pdelay': 30, 'dtime': 1.1, 'hfdamping': 6, 'density': 80, 'rshape': 25, 'q': 6, 'diffusion': 80, 'swidth': 80, 'on': true};
        r11['rvb'] = r4;
        r11['on'] = r0;
        r11['er'] = r3;
        r11['tc'] = r2;
        r2 = {'center': 0, 'lfe': 0};
        r11['il'] = r2;
        r2 = {'dry': 0, 'er': -32, 'rvb': -26};
        r11['ol'] = r2;
        r11['useable'] = r0;
        r0 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r11['rl'] = r0;
        r10 = {};
        r10['on'] = r1;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['cmp'] = r14;
        r1['eq'] = r13;
        r1['peq'] = r12;
        r1['rvb'] = r11;
        r1['limiter'] = r10;
        r1['se'] = r9;
        r1['bt'] = r8;
        r1['rotate'] = r7;
        r1['spspatial'] = r6;
        r1['alc'] = r5;
        r1['chorus'] = r4;
        r1['flanger'] = r3;
        r1['dly'] = r2;
        r1['noise'] = r0;
         r0 = 'Ngọt ngào 2 inst';
        r1['effect_name'] = r0;
        r0 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[937]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r13 = {};
        r1 = false;
        r13['on'] = r1;
        r12 = {};
        r0 = true;
        r12['on'] = r0;
        r2 = [0, 0, 0, 0.6, 0, 0.6, 0.2, 1.4, 1.4, 0];
        r12['eqs'] = r2;
        r2 = new Array(9);
        r3 = {'band': 0, 'type': 6, 'gain': 1.7, 'freq': 3000, 'q': 1, 'on': true};
        r2[0] = r3;
        r3 = {'band': 1, 'type': 6, 'gain': 1.2, 'freq': 220, 'q': 0.7, 'on': true};
        r2[1] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[2] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[3] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[4] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[5] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[6] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[7] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[8] = r3;
        r11 = {'on': true, 'gain': 1.2};
        r11['f'] = r2;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r10 = {};
        r4 = {'pdelay': 25, 'hfdamping': 6, 'rshape': 25, 'density': 80, 'swidth': 20, 'q': 6, 'diffusion': 20, 'on': true, 'dtime': 1.1};
        r10['rvb'] = r4;
        r10['on'] = r0;
        r10['er'] = r3;
        r10['tc'] = r2;
        r2 = {'center': 0, 'lfe': 0};
        r10['il'] = r2;
        r2 = {'dry': 0, 'er': -32, 'rvb': -26};
        r10['ol'] = r2;
        r10['useable'] = r0;
        r0 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r10['rl'] = r0;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['cmp'] = r13;
        r1['eq'] = r12;
        r1['peq'] = r11;
        r1['rvb'] = r10;
        r1['limiter'] = r9;
        r1['se'] = r8;
        r1['bt'] = r7;
        r1['rotate'] = r6;
        r6 = {'on': true, 'upmix': 1, 'lronoff': 0, 'lrangle': 10, 'otheronoff': 0, 'global': -0.2, 'center': 0.6, 'lfe': 0, 'fl': -0.2, 'fr': -0.2, 'left': 0, 'right': 0, 'rl': 0, 'rr': 0, 'ul': 0, 'ur': 0};
        r1['spspatial'] = r6;
        r1['alc'] = r5;
        r1['chorus'] = r4;
        r1['flanger'] = r3;
        r1['dly'] = r2;
        r1['noise'] = r0;
         r0 = 'Ngọt ngào 2-pop';
        r1['effect_name'] = r0;
        r0 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[938]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r13 = {};
        r1 = false;
        r13['on'] = r1;
        r2 = [0, 0.8, 0.6, 0.6, 0, 0.6];
        r0 = -0.4;
        r2[6] = r0;
        r0 = 1.8;
        r2[7] = r0;
        r0 = 1.4;
        r2[8] = r0;
        r0 = 0;
        r2[9] = r0;
        r12 = {};
        r0 = true;
        r12['on'] = r0;
        r12['eqs'] = r2;
        r2 = new Array(9);
        r3 = {'band': 0, 'type': 6, 'gain': 1.5, 'freq': 3000, 'q': 1, 'on': true};
        r2[0] = r3;
        r3 = {'band': 1, 'type': 6, 'gain': 1.2, 'freq': 220, 'q': 0.7, 'on': true};
        r2[1] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[2] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[3] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[4] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[5] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[6] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[7] = r3;
        r3 = {};
        r3['on'] = r1;
        r2[8] = r3;
        r11 = {'on': true, 'gain': 1.2};
        r11['f'] = r2;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r10 = {};
        r4 = {'pdelay': 25, 'hfdamping': 6, 'rshape': 25, 'density': 80, 'swidth': 20, 'q': 6, 'diffusion': 20, 'on': true, 'dtime': 1.1};
        r10['rvb'] = r4;
        r10['on'] = r0;
        r10['er'] = r3;
        r10['tc'] = r2;
        r2 = {'center': 0, 'lfe': 0};
        r10['il'] = r2;
        r2 = {'dry': 0, 'er': -32, 'rvb': -26};
        r10['ol'] = r2;
        r10['useable'] = r0;
        r0 = {'lfe': 0, 'front': 0, 'rear': 0, 'center': 0};
        r10['rl'] = r0;
        r9 = {};
        r9['on'] = r1;
        r8 = {};
        r8['on'] = r1;
        r7 = {};
        r7['on'] = r1;
        r6 = {};
        r6['on'] = r1;
        r5 = {};
        r5['on'] = r1;
        r4 = {};
        r4['on'] = r1;
        r3 = {};
        r3['on'] = r1;
        r2 = {};
        r2['on'] = r1;
        r0 = {};
        r0['on'] = r1;
        r1 = {};
        r1['cmp'] = r13;
        r1['eq'] = r12;
        r1['peq'] = r11;
        r1['rvb'] = r10;
        r1['limiter'] = r9;
        r1['se'] = r8;
        r1['bt'] = r7;
        r1['rotate'] = r6;
        r6 = {'on': true, 'upmix': 1, 'lronoff': 0, 'lrangle': 10, 'otheronoff': 0, 'global': -0.2, 'center': 0.6, 'lfe': 0, 'fl': -0.2, 'fr': -0.2, 'left': 0, 'right': 0, 'rl': 0, 'rr': 0, 'ul': 0, 'ur': 0};
        r1['spspatial'] = r6;
        r1['alc'] = r5;
        r1['chorus'] = r4;
        r1['flanger'] = r3;
        r1['dly'] = r2;
        r1['noise'] = r0;
         r0 = 'Ngọt ngào 2 rythm';
        r1['effect_name'] = r0;
        r0 = ['cmp', 'eq', 'bt', 'peq', 'chorus', 'flanger', 'se', 'rotate', 'spspatial', 'dly', 'rvb', 'noise', 'alc'];
        r1['order'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[939]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r4 = a1;
        r8 = a5;
        r5 = a6;
        var _closure1_slot4 = r8;
        r2 = global;
        r7 = r2.Object;
        r6 = r7.defineProperty;
        r3 = {};
        r0 = true;
        r3['value'] = r0;
        r0 = '__esModule';
        r0 = r6.bind(r7)(r8, r0, r3);
        r0 = 0;
        r3 = r5[r0];
        r0 = undefined;
        r7 = r4.bind(r0)(r3);
        var _closure1_slot0 = r7;
        r6 = r2.Object;
        r3 = r6.keys;
        r7 = r3.bind(r6)(r7);
        r6 = r7.forEach;
        r3 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
            _fun9320: for(var _fun9320_ip = 0; ; ) switch(_fun9320_ip) {
case 0:
                r5 = a0;
                r0 = undefined;
                r1 = 'default';
                r1 = r1 !== r5;
                if(!r1) { _fun9320_ip = 26; continue _fun9320 }
case 18:
                r2 = '__esModule';
                r1 = r2 !== r5;
case 26:
                if(r1) { _fun9320_ip = 35; continue _fun9320 }
case 29:
                var _closure2_slot0 = r5;
                _fun9320_ip = 119; continue _fun9320;
case 35:
                r2 = _closure1_slot4;
                r2 = r5 in r2;
                if(!r2) { _fun9320_ip = 69; continue _fun9320 }
case 49:
                r3 = _closure1_slot4;
                r4 = r3[r5];
                r3 = _closure1_slot0;
                r3 = r3[r5];
                r2 = r4 === r3;
case 69:
                _closure2_slot0 = r5;
                if(r2) { _fun9320_ip = 119; continue _fun9320 }
case 76:
                r2 = global;
                r4 = r2.Object;
                r3 = r4.defineProperty;
                r2 = _closure1_slot4;
                r1 = {};
                r7 = true;
                r1['enumerable'] = r7;
                r6 = function() {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: get, environment: r6
                    r1 = _closure1_slot0;
                    r0 = _closure2_slot0;
                    r0 = r1[r0];
                    return r0;
                };
                r1['get'] = r6;
                r1 = r3.bind(r4)(r2, r5, r1);
                _fun9320_ip = 119; continue _fun9320;
case 119:
                return r0;
            }
        };
        r3 = r6.bind(r7)(r3);
        r3 = 1;
        r3 = r5[r3];
        r7 = r4.bind(r0)(r3);
        var _closure1_slot1 = r7;
        r6 = r2.Object;
        r3 = r6.keys;
        r7 = r3.bind(r6)(r7);
        r6 = r7.forEach;
        r3 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
            _fun9322: for(var _fun9322_ip = 0; ; ) switch(_fun9322_ip) {
case 0:
                r5 = a0;
                r0 = undefined;
                r1 = 'default';
                r1 = r1 !== r5;
                if(!r1) { _fun9322_ip = 26; continue _fun9322 }
case 18:
                r2 = '__esModule';
                r1 = r2 !== r5;
case 26:
                if(r1) { _fun9322_ip = 35; continue _fun9322 }
case 29:
                var _closure2_slot0 = r5;
                _fun9322_ip = 119; continue _fun9322;
case 35:
                r2 = _closure1_slot4;
                r2 = r5 in r2;
                if(!r2) { _fun9322_ip = 69; continue _fun9322 }
case 49:
                r3 = _closure1_slot4;
                r4 = r3[r5];
                r3 = _closure1_slot1;
                r3 = r3[r5];
                r2 = r4 === r3;
case 69:
                _closure2_slot0 = r5;
                if(r2) { _fun9322_ip = 119; continue _fun9322 }
case 76:
                r2 = global;
                r4 = r2.Object;
                r3 = r4.defineProperty;
                r2 = _closure1_slot4;
                r1 = {};
                r7 = true;
                r1['enumerable'] = r7;
                r6 = function() {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: get, environment: r6
                    r1 = _closure1_slot1;
                    r0 = _closure2_slot0;
                    r0 = r1[r0];
                    return r0;
                };
                r1['get'] = r6;
                r1 = r3.bind(r4)(r2, r5, r1);
                _fun9322_ip = 119; continue _fun9322;
case 119:
                return r0;
            }
        };
        r3 = r6.bind(r7)(r3);
        r3 = 2;
        r3 = r5[r3];
        r7 = r4.bind(r0)(r3);
        var _closure1_slot2 = r7;
        r6 = r2.Object;
        r3 = r6.keys;
        r7 = r3.bind(r6)(r7);
        r6 = r7.forEach;
        r3 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
            _fun9324: for(var _fun9324_ip = 0; ; ) switch(_fun9324_ip) {
case 0:
                r5 = a0;
                r0 = undefined;
                r1 = 'default';
                r1 = r1 !== r5;
                if(!r1) { _fun9324_ip = 26; continue _fun9324 }
case 18:
                r2 = '__esModule';
                r1 = r2 !== r5;
case 26:
                if(r1) { _fun9324_ip = 35; continue _fun9324 }
case 29:
                var _closure2_slot0 = r5;
                _fun9324_ip = 119; continue _fun9324;
case 35:
                r2 = _closure1_slot4;
                r2 = r5 in r2;
                if(!r2) { _fun9324_ip = 69; continue _fun9324 }
case 49:
                r3 = _closure1_slot4;
                r4 = r3[r5];
                r3 = _closure1_slot2;
                r3 = r3[r5];
                r2 = r4 === r3;
case 69:
                _closure2_slot0 = r5;
                if(r2) { _fun9324_ip = 119; continue _fun9324 }
case 76:
                r2 = global;
                r4 = r2.Object;
                r3 = r4.defineProperty;
                r2 = _closure1_slot4;
                r1 = {};
                r7 = true;
                r1['enumerable'] = r7;
                r6 = function() {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: get, environment: r6
                    r1 = _closure1_slot2;
                    r0 = _closure2_slot0;
                    r0 = r1[r0];
                    return r0;
                };
                r1['get'] = r6;
                r1 = r3.bind(r4)(r2, r5, r1);
                _fun9324_ip = 119; continue _fun9324;
case 119:
                return r0;
            }
        };
        r3 = r6.bind(r7)(r3);
        r3 = 3;
        r3 = r5[r3];
        r4 = r4.bind(r0)(r3);
        var _closure1_slot3 = r4;
        r3 = r2.Object;
        r2 = r3.keys;
        r3 = r2.bind(r3)(r4);
        r2 = r3.forEach;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
            _fun9326: for(var _fun9326_ip = 0; ; ) switch(_fun9326_ip) {
case 0:
                r5 = a0;
                r0 = undefined;
                r1 = 'default';
                r1 = r1 !== r5;
                if(!r1) { _fun9326_ip = 26; continue _fun9326 }
case 18:
                r2 = '__esModule';
                r1 = r2 !== r5;
case 26:
                if(r1) { _fun9326_ip = 35; continue _fun9326 }
case 29:
                var _closure2_slot0 = r5;
                _fun9326_ip = 119; continue _fun9326;
case 35:
                r2 = _closure1_slot4;
                r2 = r5 in r2;
                if(!r2) { _fun9326_ip = 69; continue _fun9326 }
case 49:
                r3 = _closure1_slot4;
                r4 = r3[r5];
                r3 = _closure1_slot3;
                r3 = r3[r5];
                r2 = r4 === r3;
case 69:
                _closure2_slot0 = r5;
                if(r2) { _fun9326_ip = 119; continue _fun9326 }
case 76:
                r2 = global;
                r4 = r2.Object;
                r3 = r4.defineProperty;
                r2 = _closure1_slot4;
                r1 = {};
                r7 = true;
                r1['enumerable'] = r7;
                r6 = function() {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: get, environment: r6
                    r1 = _closure1_slot3;
                    r0 = _closure2_slot0;
                    r0 = r1[r0];
                    return r0;
                };
                r1['get'] = r6;
                r1 = r3.bind(r4)(r2, r5, r1);
                _fun9326_ip = 119; continue _fun9326;
case 119:
                return r0;
            }
        };
        r1 = r2.bind(r3)(r1);
        return r0;
    };
factories[940]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r3 = a5;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: t, environment: r2
            _fun9329: for(var _fun9329_ip = 0; ; ) switch(_fun9329_ip) {
case 0:
                r3 = a0;
                r0 = r3;
                if(!r3) { _fun9329_ip = 32; continue _fun9329 }
case 9:
                r1 = r3.on;
                r2 = false;
                r0 = r3;
                if(!(r2 === r1)) { _fun9329_ip = 32; continue _fun9329 }
case 23:
                r1 = {};
                r1['on'] = r2;
                r0 = r1;
case 32:
                return r0;
            }
        };
        var _closure1_slot2 = r0;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: p, environment: r2
            r0 = a0;
            r3 = _closure1_slot2;
            r1 = r0.eq;
            r2 = undefined;
            r1 = r3.bind(r2)(r1);
            r0['eq'] = r1;
            r1 = r0.cmp;
            r1 = r3.bind(r2)(r1);
            r0['cmp'] = r1;
            r1 = r0.limiter;
            r1 = r3.bind(r2)(r1);
            r0['limiter'] = r1;
            r1 = r0.bt;
            r1 = r3.bind(r2)(r1);
            r0['bt'] = r1;
            r1 = r0.peq;
            r1 = r3.bind(r2)(r1);
            r0['peq'] = r1;
            r1 = r0.rvb;
            r1 = r3.bind(r2)(r1);
            r0['rvb'] = r1;
            r1 = r0.dly;
            r1 = r3.bind(r2)(r1);
            r0['dly'] = r1;
            r1 = r0.se;
            r1 = r3.bind(r2)(r1);
            r0['se'] = r1;
            r1 = r0.chorus;
            r1 = r3.bind(r2)(r1);
            r0['chorus'] = r1;
            r1 = r0.flanger;
            r1 = r3.bind(r2)(r1);
            r0['flanger'] = r1;
            r1 = r0.rotate;
            r1 = r3.bind(r2)(r1);
            r0['rotate'] = r1;
            r1 = r0.alc;
            r1 = r3.bind(r2)(r1);
            r0['alc'] = r1;
            r1 = r0.spspatial;
            r1 = r3.bind(r2)(r1);
            r0['spspatial'] = r1;
            return r0;
        };
        var _closure1_slot3 = r1;
        r0 = global;
        r6 = r0.Object;
        r5 = r6.defineProperty;
        r4 = {};
        r0 = true;
        r4['value'] = r0;
        r0 = '__esModule';
        r0 = r5.bind(r6)(r3, r0, r4);
        r0 = undefined;
        r3['SSHAPER_OFF_THRESHOLD'] = r0;
        r4 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9331: for(var _fun9331_ip = 0; ; ) switch(_fun9331_ip) {
case 0:
                r1 = a0;
                r4 = a1;
                r2 = 'number';
                r0 = typeof r4;
                if(!(r2 === r0)) { _fun9331_ip = 131; continue _fun9331 }
case 17:
                r0 = global;
                r2 = r0.Number;
                r0 = r2.isFinite;
                r0 = r0.bind(r2)(r4);
                if(!r0) { _fun9331_ip = 131; continue _fun9331 }
case 39:
                r2 = _closure1_slot0;
                r3 = r2.floatGt;
                r2 = _closure1_slot1;
                r0 = undefined;
                r0 = r3.bind(r0)(r4, r2);
                if(!r0) { _fun9331_ip = 87; continue _fun9331 }
case 67:
                r0 = r1.se;
                r2 = null;
                if(!(r2 != r0)) { _fun9331_ip = 87; continue _fun9331 }
case 79:
                r0 = r0.on;
                if(r0) { _fun9331_ip = 102; continue _fun9331 }
case 87:
                r0 = {};
                r0['params'] = r1;
                r2 = false;
                r0['sshaperDisabledByLoudness'] = r2;
                return r0;
case 102:
                r2 = r1.se;
                r0 = false;
                r2['sshaper'] = r0;
                r0 = {};
                r0['params'] = r1;
                r2 = true;
                r0['sshaperDisabledByLoudness'] = r2;
                return r0;
case 131:
                r0 = {};
                r0['params'] = r1;
                r1 = false;
                r0['sshaperDisabledByLoudness'] = r1;
                return r0;
            }
        };
        r3['applySshaperRule'] = r4;
        r4 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            r2 = _closure1_slot3;
            r0 = _closure1_slot0;
            r3 = r0.deepClone;
            r1 = undefined;
            r0 = a0;
            r0 = r3.bind(r1)(r0);
            r1 = r2.bind(r1)(r0);
            return r0;
        };
        r3['prepareEffectTemplate'] = r4;
        r3['pruneDisabledFields'] = r1;
        r4 = a6;
        r1 = 0;
        r4 = r4[r1];
        r1 = a1;
        r1 = r1.bind(r0)(r4);
        var _closure1_slot0 = r1;
        r1 = -6;
        r3['SSHAPER_OFF_THRESHOLD'] = r1;
        var _closure1_slot1 = r1;
        return r0;
    };
factories[941]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r4 = a1;
        r3 = a5;
        r5 = a6;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: u, environment: r2
            _fun9334: for(var _fun9334_ip = 0; ; ) switch(_fun9334_ip) {
case 0:
                r0 = a0;
                r2 = null;
                r1 = undefined;
                if(!(r2 != r0)) { _fun9334_ip = 16; continue _fun9334 }
case 11:
                r1 = r0.on;
case 16:
                r2 = 'boolean';
                r1 = typeof r1;
                if(!(r2 === r1)) { _fun9334_ip = 34; continue _fun9334 }
case 27:
                r0 = r0.on;
                return r0;
case 34:
                r0 = global;
                r2 = r0.Error;
                r1 = a1;
                 r0 = ' Thiếu hoặc không phải boolean';
                r3 = r1 + r0;
                r1 = r2.prototype;
                r1 = Object.create(r1, {constructor: {value: r2}});
                r4 = r1;
                r0 = originalConstruct(r2, [r3, r2]);
                r0 = r0 instanceof Object ? r0 : r1;
                throw r0;
            }
        };
        var _closure1_slot5 = r0;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: b, environment: r2
            _fun9335: for(var _fun9335_ip = 0; ; ) switch(_fun9335_ip) {
case 0:
                r3 = undefined;
                r0 = a0;
                r1 = r0.eq;
                r0 = null;
                r2 = undefined;
                if(!(r0 != r1)) { _fun9335_ip = 25; continue _fun9335 }
case 19:
                r2 = r1.eqs;
case 25:
                if(!(r0 != r2)) { _fun9335_ip = 193; continue _fun9335 }
case 32:
                r0 = global;
                r4 = r0.Array;
                r1 = r4.isArray;
                r1 = r1.bind(r4)(r2);
                if(!r1) { _fun9335_ip = 144; continue _fun9335 }
case 53:
                r5 = r2.length;
                r4 = _closure1_slot1;
                r4 = r4.AEV2_EQ_GAIN_DB_SIZE;
                if(!(r5 === r4)) { _fun9335_ip = 144; continue _fun9335 }
case 75:
                r5 = r2.some;
                r4 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r4
                    _fun9336: for(var _fun9336_ip = 0; ; ) switch(_fun9336_ip) {
case 0:
                        r3 = a0;
                        r1 = 'number';
                        r0 = typeof r3;
                        r0 = r1 !== r0;
                        if(r0) { _fun9336_ip = 39; continue _fun9336 }
case 17:
                        r1 = global;
                        r2 = r1.Number;
                        r1 = r2.isFinite;
                        r1 = r1.bind(r2)(r3);
                        r0 = !r1;
case 39:
                        return r0;
                    }
                };
                r4 = r5.bind(r2)(r4);
                if(r4) { _fun9335_ip = 112; continue _fun9335 }
case 96:
                r1 = _closure1_slot0;
                r1 = r1.default;
                r1 = r1.bind(r3)(r2);
                return r1;
case 112:
                r3 = r0.Error;
                r1 = r3.prototype;
                r2 = Object.create(r1, {constructor: {value: r3}});
                 r6 = 'Có giá trị không hợp lệ trong template.eq.eqs';
                r7 = r2;
                r1 = originalConstruct(r3, [r6, r5]);
                r1 = r1 instanceof Object ? r1 : r2;
                throw r1;
case 144:
                r2 = r0.Error;
                r0 = _closure1_slot1;
                r1 = r0.AEV2_EQ_GAIN_DB_SIZE;
                 r0 = 'Độ dài của template.eq.eqs phải là ';
                r6 = r0 + r1;
                r1 = r2.prototype;
                r1 = Object.create(r1, {constructor: {value: r2}});
                r7 = r1;
                r0 = originalConstruct(r2, [r6, r5]);
                r0 = r0 instanceof Object ? r0 : r1;
                throw r0;
case 193:
                r0 = global;
                r2 = r0.Array;
                r0 = _closure1_slot1;
                r6 = r0.AEV2_EQ_GAIN_DB_SIZE;
                r1 = r2.prototype;
                r1 = Object.create(r1, {constructor: {value: r2}});
                r7 = r1;
                r0 = originalConstruct(r2, [r6, r5]);
                r2 = r0 instanceof Object ? r0 : r1;
                r1 = r2.fill;
                r0 = 0;
                r0 = r1.bind(r2)(r0);
                return r0;
            }
        };
        var _closure1_slot6 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: p, environment: r2
            _fun9337: for(var _fun9337_ip = 0; ; ) switch(_fun9337_ip) {
case 0:
                r1 = a1;
                r0 = _closure1_slot2;
                r4 = r0.assertFiniteNumber;
                r2 = r1.readTemplate;
                r0 = a0;
                r3 = r2.bind(r1)(r0);
                r2 = r1.templateField;
                r0 = undefined;
                r3 = r4.bind(r0)(r3, r2);
                r2 = r1.toAev2;
                r0 = r3;
                if(!r2) { _fun9337_ip = 67; continue _fun9337 }
case 56:
                r2 = r1.toAev2;
                r0 = r2.bind(r1)(r3);
case 67:
                r2 = global;
                r4 = r2.Number;
                r3 = r4.isFinite;
                r3 = r3.bind(r4)(r0);
                if(r3) { _fun9337_ip = 131; continue _fun9337 }
case 89:
                r3 = r2.Error;
                r2 = r1.templateField;
                 r1 = ' sau khi ánh xạ sang AEV2 không phải là giá trị hữu hạn';
                r6 = r2 + r1;
                r2 = r3.prototype;
                r2 = Object.create(r2, {constructor: {value: r3}});
                r7 = r2;
                r1 = originalConstruct(r3, [r6, r5]);
                r1 = r1 instanceof Object ? r1 : r2;
                throw r1;
case 131:
                return r0;
            }
        };
        var _closure1_slot7 = r0;
        r0 = function(a0, a1, a2, a3) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: _, environment: r2
            _fun9338: for(var _fun9338_ip = 0; ; ) switch(_fun9338_ip) {
case 0:
                r4 = a1;
                r2 = a2;
                r0 = _closure1_slot2;
                r3 = r0.assertFiniteNumber;
                r0 = undefined;
                r1 = a3;
                r5 = r3.bind(r0)(r1, r2);
                r1 = r4.fromAev2;
                r3 = r5;
                if(!r1) { _fun9338_ip = 53; continue _fun9338 }
case 42:
                r1 = r4.fromAev2;
                r3 = r1.bind(r4)(r5);
case 53:
                r1 = global;
                r6 = r1.Number;
                r5 = r6.isFinite;
                r5 = r5.bind(r6)(r3);
                if(r5) { _fun9338_ip = 111; continue _fun9338 }
case 75:
                r5 = r1.Error;
                 r1 = ' sau khi ánh xạ trở lại template không phải là giá trị hữu hạn';
                r8 = r2 + r1;
                r2 = r5.prototype;
                r2 = Object.create(r2, {constructor: {value: r5}});
                r9 = r2;
                r1 = originalConstruct(r5, [r8, r7]);
                r1 = r1 instanceof Object ? r1 : r2;
                throw r1;
case 111:
                r2 = r4.writeTemplate;
                r1 = a0;
                r1 = r2.bind(r4)(r1, r3);
                return r0;
            }
        };
        var _closure1_slot8 = r0;
        r0 = 0;
        r1 = r5[r0];
        r0 = undefined;
        r6 = r4.bind(r0)(r1);
        r1 = global;
        r9 = r1.Object;
        r8 = r9.defineProperty;
        r7 = {};
        r1 = true;
        r7['value'] = r1;
        r1 = '__esModule';
        r1 = r8.bind(r9)(r3, r1, r7);
        r3['AEV2_TEMPLATE_SCALAR_MAPPINGS'] = r0;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9339: for(var _fun9339_ip = 0; ; ) switch(_fun9339_ip) {
case 0:
                r8 = a0;
                r7 = undefined;
                r3 = _closure1_slot5;
                r2 = r8.rvb;
                r1 = 'template.rvb.on';
                r3 = r3.bind(r7)(r2, r1);
                r2 = r8.dly;
                r4 = null;
                r1 = undefined;
                if(!(r4 != r2)) { _fun9339_ip = 47; continue _fun9339 }
case 42:
                r1 = r2.on;
case 47:
                r2 = 'boolean';
                r1 = typeof r1;
                r2 = r2 === r1;
                if(!r2) { _fun9339_ip = 72; continue _fun9339 }
case 61:
                r1 = r8.dly;
                r2 = r1.on;
case 72:
                r5 = _closure1_slot5;
                r4 = r8.se;
                r1 = 'template.se.on';
                r1 = r5.bind(r7)(r4, r1);
                r4 = _closure1_slot6;
                r4 = r4.bind(r7)(r8);
                if(r3) { _fun9339_ip = 114; continue _fun9339 }
case 104:
                r3 = {};
                r5 = false;
                r3['on'] = r5;
                _fun9339_ip = 234; continue _fun9339;
case 114:
                r6 = _closure1_slot7;
                r5 = _closure1_slot4;
                r5 = r5.reverb_time;
                r12 = r6.bind(r7)(r8, r5);
                r5 = _closure1_slot4;
                r5 = r5.dry_level;
                r11 = r6.bind(r7)(r8, r5);
                r5 = _closure1_slot4;
                r5 = r5.wet_level;
                r10 = r6.bind(r7)(r8, r5);
                r5 = _closure1_slot4;
                r5 = r5.room_size;
                r9 = r6.bind(r7)(r8, r5);
                r5 = _closure1_slot4;
                r5 = r5.diffusion;
                r6 = r6.bind(r7)(r8, r5);
                r5 = {};
                r13 = true;
                r5['on'] = r13;
                r5['reverb_time'] = r12;
                r5['dry_level'] = r11;
                r5['wet_level'] = r10;
                r5['room_size'] = r9;
                r5['diffusion'] = r6;
                r3 = r5;
case 234:
                if(r2) { _fun9339_ip = 247; continue _fun9339 }
case 237:
                r2 = {};
                r5 = false;
                r2['on'] = r5;
                _fun9339_ip = 304; continue _fun9339;
case 247:
                r6 = _closure1_slot7;
                r5 = _closure1_slot4;
                r5 = r5.delay_time;
                r9 = r6.bind(r7)(r8, r5);
                r5 = _closure1_slot4;
                r5 = r5.delay_feedback;
                r6 = r6.bind(r7)(r8, r5);
                r5 = {};
                r10 = true;
                r5['on'] = r10;
                r5['delay_time'] = r9;
                r5['feedback'] = r6;
                r2 = r5;
case 304:
                if(r1) { _fun9339_ip = 317; continue _fun9339 }
case 307:
                r1 = {};
                r5 = false;
                r1['on'] = r5;
                _fun9339_ip = 374; continue _fun9339;
case 317:
                r5 = _closure1_slot7;
                r6 = _closure1_slot4;
                r6 = r6.stereo_width;
                r6 = r5.bind(r7)(r8, r6);
                r0 = _closure1_slot4;
                r0 = r0.stereo_depth;
                r5 = r5.bind(r7)(r8, r0);
                r0 = {};
                r7 = true;
                r0['on'] = r7;
                r0['stereo_width'] = r6;
                r0['stereo_depth'] = r5;
                r1 = r0;
case 374:
                r0 = {};
                r0['eq_gain_db'] = r4;
                r0['reverb'] = r3;
                r0['delay'] = r2;
                r0['stereo_enhance'] = r1;
                return r0;
            }
        };
        r3['buildBaseExtraAev2FromTemplate'] = r1;
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9340: for(var _fun9340_ip = 0; ; ) switch(_fun9340_ip) {
case 0:
                r3 = a1;
                r1 = _closure1_slot2;
                r4 = r1.deepClone;
                r2 = undefined;
                r1 = a0;
                r1 = r4.bind(r2)(r1);
                r4 = r1.eq;
                r5 = null;
                if(!(r5 == r4)) { _fun9340_ip = 40; continue _fun9340 }
case 38:
                r4 = {};
case 40:
                r1['eq'] = r4;
                r5 = r1.eq;
                r4 = true;
                r5['on'] = r4;
                r5 = r1.eq;
                r4 = _closure1_slot0;
                r6 = r4.default;
                r4 = r3.eq_gain_db;
                r4 = r6.bind(r2)(r4);
                r5['eqs'] = r4;
                r4 = _closure1_slot2;
                r6 = r4.requireField;
                r5 = r1.rvb;
                r4 = 'template.rvb';
                r5 = r6.bind(r2)(r5, r4);
                r4 = r3.reverb;
                r4 = r4.on;
                r5['on'] = r4;
                r4 = r3.reverb;
                r4 = r4.on;
                if(!r4) { _fun9340_ip = 323; continue _fun9340 }
case 152:
                r7 = _closure1_slot8;
                r4 = _closure1_slot4;
                r10 = r4.reverb_time;
                r4 = r3.reverb;
                r8 = r4.reverb_time;
                r9 = 'aev2.reverb.reverb_time';
                r12 = undefined;
                r11 = r1;
                r4 = r7.call(r12, r11, r10, r9, r8, r7);
                r4 = _closure1_slot4;
                r10 = r4.dry_level;
                r4 = r3.reverb;
                r8 = r4.dry_level;
                r9 = 'aev2.reverb.dry_level';
                r11 = r1;
                r4 = r7.call(r12, r11, r10, r9, r8, r7);
                r4 = _closure1_slot4;
                r10 = r4.wet_level;
                r4 = r3.reverb;
                r8 = r4.wet_level;
                r9 = 'aev2.reverb.wet_level';
                r11 = r1;
                r4 = r7.call(r12, r11, r10, r9, r8, r7);
                r4 = _closure1_slot4;
                r10 = r4.room_size;
                r4 = r3.reverb;
                r8 = r4.room_size;
                r9 = 'aev2.reverb.room_size';
                r11 = r1;
                r4 = r7.call(r12, r11, r10, r9, r8, r7);
                r4 = _closure1_slot4;
                r10 = r4.diffusion;
                r4 = r3.reverb;
                r8 = r4.diffusion;
                r9 = 'aev2.reverb.diffusion';
                r11 = r1;
                r4 = r7.call(r12, r11, r10, r9, r8, r7);
case 323:
                r4 = r1.dly;
                if(r4) { _fun9340_ip = 346; continue _fun9340 }
case 332:
                r4 = {};
                r5 = false;
                r4['on'] = r5;
                r1['dly'] = r4;
case 346:
                r5 = r1.dly;
                r4 = r3.delay;
                r4 = r4.on;
                r5['on'] = r4;
                r4 = r3.delay;
                r4 = r4.on;
                if(!r4) { _fun9340_ip = 455; continue _fun9340 }
case 383:
                r7 = _closure1_slot8;
                r4 = _closure1_slot4;
                r10 = r4.delay_time;
                r4 = r3.delay;
                r8 = r4.delay_time;
                r9 = 'aev2.delay.delay_time';
                r12 = undefined;
                r11 = r1;
                r4 = r7.call(r12, r11, r10, r9, r8, r7);
                r4 = _closure1_slot4;
                r10 = r4.delay_feedback;
                r4 = r3.delay;
                r8 = r4.feedback;
                r9 = 'aev2.delay.feedback';
                r11 = r1;
                r4 = r7.call(r12, r11, r10, r9, r8, r7);
case 455:
                r4 = _closure1_slot2;
                r6 = r4.requireField;
                r5 = r1.se;
                r4 = 'template.se';
                r5 = r6.bind(r2)(r5, r4);
                r4 = r3.stereo_enhance;
                r4 = r4.on;
                r5['on'] = r4;
                r4 = r3.stereo_enhance;
                r4 = r4.on;
                if(!r4) { _fun9340_ip = 584; continue _fun9340 }
case 512:
                r6 = _closure1_slot8;
                r4 = _closure1_slot4;
                r10 = r4.stereo_width;
                r4 = r3.stereo_enhance;
                r8 = r4.stereo_width;
                r9 = 'aev2.stereo_enhance.stereo_width';
                r12 = undefined;
                r11 = r1;
                r4 = r6.call(r12, r11, r10, r9, r8, r7);
                r4 = _closure1_slot4;
                r10 = r4.stereo_depth;
                r3 = r3.stereo_enhance;
                r8 = r3.stereo_depth;
                r9 = 'aev2.stereo_enhance.stereo_depth';
                r11 = r1;
                r3 = r6.call(r12, r11, r10, r9, r8, r7);
case 584:
                r0 = _closure1_slot3;
                r0 = r0.pruneDisabledFields;
                r0 = r0.bind(r2)(r1);
                return r0;
            }
        };
        r3['mergeDecodedAev2IntoTemplate'] = r1;
        r1 = 1;
        r1 = r5[r1];
        r1 = r4.bind(r0)(r1);
        r1 = r6.bind(r0)(r1);
        var _closure1_slot0 = r1;
        r1 = 2;
        r1 = r5[r1];
        r1 = r4.bind(r0)(r1);
        var _closure1_slot1 = r1;
        r1 = 3;
        r1 = r5[r1];
        r1 = r4.bind(r0)(r1);
        var _closure1_slot2 = r1;
        r1 = 4;
        r1 = r5[r1];
        r1 = r4.bind(r0)(r1);
        var _closure1_slot3 = r1;
        r12 = {};
        r1 = 'template.rvb.rvb.dtime';
        r12['templateField'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: readTemplate, environment: r2
            _fun9341: for(var _fun9341_ip = 0; ; ) switch(_fun9341_ip) {
case 0:
                r0 = a0;
                r2 = r0.rvb;
                r3 = null;
                r0 = undefined;
                if(!(r3 != r2)) { _fun9341_ip = 38; continue _fun9341 }
case 17:
                r2 = r2.rvb;
                r1 = undefined;
                if(!(r3 != r2)) { _fun9341_ip = 35; continue _fun9341 }
case 29:
                r1 = r2.dtime;
case 35:
                r0 = r1;
case 38:
                return r0;
            }
        };
        r12['readTemplate'] = r1;
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: writeTemplate, environment: r2
            r1 = _closure1_slot2;
            r3 = r1.requireField;
            r0 = _closure1_slot2;
            r4 = r0.requireField;
            r0 = a0;
            r2 = r0.rvb;
            r0 = undefined;
            r1 = 'template.rvb';
            r1 = r4.bind(r0)(r2, r1);
            r2 = r1.rvb;
            r1 = 'template.rvb.rvb';
            r2 = r3.bind(r0)(r2, r1);
            r1 = a1;
            r2['dtime'] = r1;
            return r0;
        };
        r12['writeTemplate'] = r1;
        r11 = {};
        r1 = 'template.rvb.ol.dry';
        r11['templateField'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: readTemplate, environment: r2
            _fun9343: for(var _fun9343_ip = 0; ; ) switch(_fun9343_ip) {
case 0:
                r0 = a0;
                r2 = r0.rvb;
                r3 = null;
                r0 = undefined;
                if(!(r3 != r2)) { _fun9343_ip = 38; continue _fun9343 }
case 17:
                r2 = r2.ol;
                r1 = undefined;
                if(!(r3 != r2)) { _fun9343_ip = 35; continue _fun9343 }
case 29:
                r1 = r2.dry;
case 35:
                r0 = r1;
case 38:
                return r0;
            }
        };
        r11['readTemplate'] = r1;
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: writeTemplate, environment: r2
            r1 = _closure1_slot2;
            r3 = r1.requireField;
            r0 = _closure1_slot2;
            r4 = r0.requireField;
            r0 = a0;
            r2 = r0.rvb;
            r0 = undefined;
            r1 = 'template.rvb';
            r1 = r4.bind(r0)(r2, r1);
            r2 = r1.ol;
            r1 = 'template.rvb.ol';
            r2 = r3.bind(r0)(r2, r1);
            r1 = a1;
            r2['dry'] = r1;
            return r0;
        };
        r11['writeTemplate'] = r1;
        r10 = {};
        r1 = 'template.rvb.ol.rvb';
        r10['templateField'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: readTemplate, environment: r2
            _fun9345: for(var _fun9345_ip = 0; ; ) switch(_fun9345_ip) {
case 0:
                r0 = a0;
                r2 = r0.rvb;
                r3 = null;
                r0 = undefined;
                if(!(r3 != r2)) { _fun9345_ip = 38; continue _fun9345 }
case 17:
                r2 = r2.ol;
                r1 = undefined;
                if(!(r3 != r2)) { _fun9345_ip = 35; continue _fun9345 }
case 29:
                r1 = r2.rvb;
case 35:
                r0 = r1;
case 38:
                return r0;
            }
        };
        r10['readTemplate'] = r1;
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: writeTemplate, environment: r2
            r1 = _closure1_slot2;
            r3 = r1.requireField;
            r0 = _closure1_slot2;
            r4 = r0.requireField;
            r0 = a0;
            r2 = r0.rvb;
            r0 = undefined;
            r1 = 'template.rvb';
            r1 = r4.bind(r0)(r2, r1);
            r2 = r1.ol;
            r1 = 'template.rvb.ol';
            r2 = r3.bind(r0)(r2, r1);
            r1 = a1;
            r2['rvb'] = r1;
            return r0;
        };
        r10['writeTemplate'] = r1;
        r9 = {};
        r1 = 'template.rvb.rvb.rshape';
        r9['templateField'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: readTemplate, environment: r2
            _fun9347: for(var _fun9347_ip = 0; ; ) switch(_fun9347_ip) {
case 0:
                r0 = a0;
                r2 = r0.rvb;
                r3 = null;
                r0 = undefined;
                if(!(r3 != r2)) { _fun9347_ip = 38; continue _fun9347 }
case 17:
                r2 = r2.rvb;
                r1 = undefined;
                if(!(r3 != r2)) { _fun9347_ip = 35; continue _fun9347 }
case 29:
                r1 = r2.rshape;
case 35:
                r0 = r1;
case 38:
                return r0;
            }
        };
        r9['readTemplate'] = r1;
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: writeTemplate, environment: r2
            r1 = _closure1_slot2;
            r3 = r1.requireField;
            r0 = _closure1_slot2;
            r4 = r0.requireField;
            r0 = a0;
            r2 = r0.rvb;
            r0 = undefined;
            r1 = 'template.rvb';
            r1 = r4.bind(r0)(r2, r1);
            r2 = r1.rvb;
            r1 = 'template.rvb.rvb';
            r2 = r3.bind(r0)(r2, r1);
            r1 = a1;
            r2['rshape'] = r1;
            return r0;
        };
        r9['writeTemplate'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: toAev2, environment: r2
            r1 = a0;
            r0 = 100;
            r0 = r1 / r0;
            return r0;
        };
        r9['toAev2'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: fromAev2, environment: r2
            r1 = 100;
            r0 = a0;
            r0 = r1 * r0;
            return r0;
        };
        r9['fromAev2'] = r1;
        r8 = {};
        r1 = 'template.rvb.rvb.diffusion';
        r8['templateField'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: readTemplate, environment: r2
            _fun9351: for(var _fun9351_ip = 0; ; ) switch(_fun9351_ip) {
case 0:
                r0 = a0;
                r2 = r0.rvb;
                r3 = null;
                r0 = undefined;
                if(!(r3 != r2)) { _fun9351_ip = 38; continue _fun9351 }
case 17:
                r2 = r2.rvb;
                r1 = undefined;
                if(!(r3 != r2)) { _fun9351_ip = 35; continue _fun9351 }
case 29:
                r1 = r2.diffusion;
case 35:
                r0 = r1;
case 38:
                return r0;
            }
        };
        r8['readTemplate'] = r1;
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: writeTemplate, environment: r2
            r1 = _closure1_slot2;
            r3 = r1.requireField;
            r0 = _closure1_slot2;
            r4 = r0.requireField;
            r0 = a0;
            r2 = r0.rvb;
            r0 = undefined;
            r1 = 'template.rvb';
            r1 = r4.bind(r0)(r2, r1);
            r2 = r1.rvb;
            r1 = 'template.rvb.rvb';
            r2 = r3.bind(r0)(r2, r1);
            r1 = a1;
            r2['diffusion'] = r1;
            return r0;
        };
        r8['writeTemplate'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: toAev2, environment: r2
            r1 = a0;
            r0 = 100;
            r0 = r1 / r0;
            return r0;
        };
        r8['toAev2'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: fromAev2, environment: r2
            r1 = 100;
            r0 = a0;
            r0 = r1 * r0;
            return r0;
        };
        r8['fromAev2'] = r1;
        r7 = {};
        r1 = 'template.dly.delay';
        r7['templateField'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: readTemplate, environment: r2
            _fun9355: for(var _fun9355_ip = 0; ; ) switch(_fun9355_ip) {
case 0:
                r1 = a0;
                r1 = r1.dly;
                r2 = null;
                r0 = undefined;
                if(!(r2 != r1)) { _fun9355_ip = 23; continue _fun9355 }
case 17:
                r0 = r1.delay;
case 23:
                return r0;
            }
        };
        r7['readTemplate'] = r1;
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: writeTemplate, environment: r2
            r0 = _closure1_slot2;
            r3 = r0.requireField;
            r0 = a0;
            r2 = r0.dly;
            r0 = undefined;
            r1 = 'template.dly';
            r2 = r3.bind(r0)(r2, r1);
            r1 = a1;
            r2['delay'] = r1;
            return r0;
        };
        r7['writeTemplate'] = r1;
        r6 = {};
        r1 = 'template.dly.feedback';
        r6['templateField'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: readTemplate, environment: r2
            _fun9357: for(var _fun9357_ip = 0; ; ) switch(_fun9357_ip) {
case 0:
                r1 = a0;
                r1 = r1.dly;
                r2 = null;
                r0 = undefined;
                if(!(r2 != r1)) { _fun9357_ip = 23; continue _fun9357 }
case 17:
                r0 = r1.feedback;
case 23:
                return r0;
            }
        };
        r6['readTemplate'] = r1;
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: writeTemplate, environment: r2
            r0 = _closure1_slot2;
            r3 = r0.requireField;
            r0 = a0;
            r2 = r0.dly;
            r0 = undefined;
            r1 = 'template.dly';
            r2 = r3.bind(r0)(r2, r1);
            r1 = a1;
            r2['feedback'] = r1;
            return r0;
        };
        r6['writeTemplate'] = r1;
        r5 = {};
        r1 = 'template.se.stereoizer';
        r5['templateField'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: readTemplate, environment: r2
            _fun9359: for(var _fun9359_ip = 0; ; ) switch(_fun9359_ip) {
case 0:
                r1 = a0;
                r1 = r1.se;
                r2 = null;
                r0 = undefined;
                if(!(r2 != r1)) { _fun9359_ip = 23; continue _fun9359 }
case 17:
                r0 = r1.stereoizer;
case 23:
                return r0;
            }
        };
        r5['readTemplate'] = r1;
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: writeTemplate, environment: r2
            r0 = _closure1_slot2;
            r3 = r0.requireField;
            r0 = a0;
            r2 = r0.se;
            r0 = undefined;
            r1 = 'template.se';
            r2 = r3.bind(r0)(r2, r1);
            r1 = a1;
            r2['stereoizer'] = r1;
            return r0;
        };
        r5['writeTemplate'] = r1;
        r4 = {};
        r1 = 'template.se.presence';
        r4['templateField'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: readTemplate, environment: r2
            _fun9361: for(var _fun9361_ip = 0; ; ) switch(_fun9361_ip) {
case 0:
                r1 = a0;
                r1 = r1.se;
                r2 = null;
                r0 = undefined;
                if(!(r2 != r1)) { _fun9361_ip = 23; continue _fun9361 }
case 17:
                r0 = r1.presence;
case 23:
                return r0;
            }
        };
        r4['readTemplate'] = r1;
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: writeTemplate, environment: r2
            r0 = _closure1_slot2;
            r3 = r0.requireField;
            r0 = a0;
            r2 = r0.se;
            r0 = undefined;
            r1 = 'template.se';
            r2 = r3.bind(r0)(r2, r1);
            r1 = a1;
            r2['presence'] = r1;
            return r0;
        };
        r4['writeTemplate'] = r1;
        r1 = {};
        r1['reverb_time'] = r12;
        r1['dry_level'] = r11;
        r1['wet_level'] = r10;
        r1['room_size'] = r9;
        r1['diffusion'] = r8;
        r1['delay_time'] = r7;
        r1['delay_feedback'] = r6;
        r1['stereo_width'] = r5;
        r1['stereo_depth'] = r4;
        r3['AEV2_TEMPLATE_SCALAR_MAPPINGS'] = r1;
        var _closure1_slot4 = r1;
        return r0;
    };
factories[942]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r2 = a5;
        r0 = global;
        r4 = r0.Object;
        r3 = r4.defineProperty;
        r1 = {};
        r0 = true;
        r1['value'] = r0;
        r0 = '__esModule';
        r0 = r3.bind(r4)(r2, r0, r1);
        r0 = undefined;
        r2['AEV2_EQ_GAIN_DB_SIZE'] = r0;
        r2['AEV2_MIN_VALUES'] = r0;
        r1 = 10;
        r2['AEV2_EQ_GAIN_DB_SIZE'] = r1;
        r1 = {};
        r3 = {'reverb_time': 0, 'dry_level': -96.3, 'wet_level': -96.3, 'room_size': 0, 'diffusion': 0};
        r1['reverb'] = r3;
        r3 = {'delay_time': 0, 'feedback': 0};
        r1['delay'] = r3;
        r3 = {'stereo_width': 0, 'stereo_depth': 0};
        r1['stereo_enhance'] = r3;
        r2['AEV2_MIN_VALUES'] = r1;
        return r0;
    };
factories[943]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r1 = a5;
        r0 = global;
        r5 = r0.Object;
        r4 = r5.defineProperty;
        r3 = {};
        r0 = true;
        r3['value'] = r0;
        r0 = '__esModule';
        r0 = r4.bind(r5)(r1, r0, r3);
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9365: for(var _fun9365_ip = 0; ; ) switch(_fun9365_ip) {
case 0:
                r6 = undefined;
                r8 = false;
                r5 = 0;
                r0 = undefined;
                r7 = undefined;
                r2 = _closure1_slot0;
                r4 = r2.deepClone;
                r2 = a0;
                r0 = r4.bind(r6)(r2);
                r9 = _closure1_slot9;
                r2 = r9;
                var __iterator_values_2=Array.from(r2), __iterator_index_2=0; r4={return:function(){}}; r2=function(){if(__iterator_index_2>=__iterator_values_2.length){r4=undefined;return {value:undefined}}return {value:__iterator_values_2[__iterator_index_2++]}};
case 43:
                r10 = r2().value;
                r9 = r4;
                if(!(r9 !== r6)) { _fun9365_ip = 85; continue _fun9365 }
case 54: // try_start_0
                r9 = r0;
                r9 = r9[r10];
                r7 = r9;
                if(!r9) { _fun9365_ip = 76; continue _fun9365 }
case 67:
                r9 = r7;
                r9['on'] = r8;
case 76: // try_end0
                _fun9365_ip = 43; continue _fun9365;
case 78: // catch_target0
                CatchBlockStart(arg_register=2);
                r4.return();
                throw r2;
case 85:
                r2 = r0;
                r2 = r2.eq;
                if(r2) { _fun9365_ip = 114; continue _fun9365 }
case 97:
                r4 = r0;
                r2 = {};
                r7 = true;
                r2['on'] = r7;
                r4['eq'] = r2;
case 114:
                r7 = r0;
                r2 = r7.eq;
                r4 = true;
                r2['on'] = r4;
                r2 = global;
                r9 = r2.Array;
                r8 = r9.isArray;
                r7 = r7.eq;
                r7 = r7.eqs;
                r7 = r8.bind(r9)(r7);
                if(!r7) { _fun9365_ip = 198; continue _fun9365 }
case 164:
                r7 = r0;
                r7 = r7.eq;
                r7 = r7.eqs;
                r8 = r7.length;
                r7 = _closure1_slot6;
                if(!(!(r8 < r7))) { _fun9365_ip = 198; continue _fun9365 }
case 192:
                var _closure2_slot0 = r6;
                _fun9365_ip = 291; continue _fun9365;
case 198:
                r8 = r2.Array;
                r7 = r8.isArray;
                r2 = r0;
                r2 = r2.eq;
                r2 = r2.eqs;
                r2 = r7.bind(r8)(r2);
                if(r2) { _fun9365_ip = 238; continue _fun9365 }
case 232:
                r2 = new Array(0);
                _fun9365_ip = 253; continue _fun9365;
case 238:
                r7 = r0;
                r7 = r7.eq;
                r2 = r7.eqs;
case 253:
                _closure2_slot0 = r2;
                r2 = r0;
                r2 = r2.eq;
                r8 = _closure1_slot10;
                r7 = r8.map;
                r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
                    r0 = _closure1_slot0;
                    r3 = r0.finiteOr;
                    r1 = _closure2_slot0;
                    r0 = a1;
                    r2 = r1[r0];
                    r1 = undefined;
                    r0 = a0;
                    r0 = r3.bind(r1)(r2, r0);
                    return r0;
                };
                r1 = r7.bind(r8)(r1);
                r2['eqs'] = r1;
case 291:
                r2 = _closure1_slot2;
                r1 = a1;
                r7 = r2[r1];
                if(!r7) { _fun9365_ip = 418; continue _fun9365 }
case 305:
                r1 = _closure1_slot6;
                r2 = 0;
                if(!(r2 < r1)) { _fun9365_ip = 418; continue _fun9365 }
case 315:
                r1 = r0;
                r8 = r1.eq;
                r8 = r8.eqs;
                r9 = _closure1_slot0;
                r11 = r9.clamp;
                r9 = _closure1_slot0;
                r9 = r9.finiteOr;
                r1 = r1.eq;
                r1 = r1.eqs;
                r1 = r1[r2];
                r9 = r9.bind(r6)(r1, r5);
                r10 = r7[r2];
                r1 = _closure1_slot1;
                r1 = r10 * r1;
                r10 = r9 + r1;
                r9 = _closure1_slot7;
                r1 = _closure1_slot8;
                r1 = r11.bind(r6)(r10, r9, r1);
                r8[r2] = r1;
                r2 = r2 + 1;
                r1 = _closure1_slot6;
                if(r2 < r1) { _fun9365_ip = 315; continue _fun9365 }
case 418:
                r1 = r0;
                r7 = r1.rvb;
                r1 = null;
                r2 = undefined;
                if(!(r1 != r7)) { _fun9365_ip = 440; continue _fun9365 }
case 435:
                r2 = r7.on;
case 440:
                if(!(r4 === r2)) { _fun9365_ip = 843; continue _fun9365 }
case 447:
                r2 = r0;
                r2 = r2.rvb;
                r2 = r2.rvb;
                if(!r2) { _fun9365_ip = 700; continue _fun9365 }
case 468:
                r2 = r0;
                r2 = r2.rvb;
                r4 = r2.rvb;
                r2 = r4.dtime;
                r7 = 'number';
                r2 = typeof r2;
                if(!(r7 === r2)) { _fun9365_ip = 562; continue _fun9365 }
case 500:
                r2 = _closure1_slot0;
                r9 = r2.clamp;
                r2 = _closure1_slot0;
                r8 = r2.finiteOr;
                r2 = r4.dtime;
                r8 = r8.bind(r6)(r2, r5);
                r2 = 1.15;
                r8 = r2 * r8;
                r2 = 10;
                r2 = r9.bind(r6)(r8, r5, r2);
                r4['dtime'] = r2;
case 562:
                r2 = r4.rshape;
                r2 = typeof r2;
                if(!(r7 === r2)) { _fun9365_ip = 631; continue _fun9365 }
case 575:
                r2 = _closure1_slot0;
                r9 = r2.clamp;
                r2 = _closure1_slot0;
                r8 = r2.finiteOr;
                r2 = r4.rshape;
                r8 = r8.bind(r6)(r2, r5);
                r2 = _closure1_slot4;
                r8 = r8 + r2;
                r2 = 100;
                r2 = r9.bind(r6)(r8, r5, r2);
                r4['rshape'] = r2;
case 631:
                r2 = r4.diffusion;
                r2 = typeof r2;
                if(!(r7 === r2)) { _fun9365_ip = 700; continue _fun9365 }
case 644:
                r2 = _closure1_slot0;
                r8 = r2.clamp;
                r2 = _closure1_slot0;
                r7 = r2.finiteOr;
                r2 = r4.diffusion;
                r7 = r7.bind(r6)(r2, r5);
                r2 = _closure1_slot5;
                r7 = r7 + r2;
                r2 = 100;
                r2 = r8.bind(r6)(r7, r5, r2);
                r4['diffusion'] = r2;
case 700:
                r2 = r0;
                r2 = r2.rvb;
                r2 = r2.ol;
                if(!r2) { _fun9365_ip = 750; continue _fun9365 }
case 718:
                r4 = r0;
                r4 = r4.rvb;
                r4 = r4.ol;
                r4 = r4.rvb;
                r7 = 'number';
                r4 = typeof r4;
                r2 = r7 === r4;
case 750:
                if(!r2) { _fun9365_ip = 843; continue _fun9365 }
case 753:
                r2 = r0;
                r4 = r2.rvb;
                r4 = r4.ol;
                r7 = _closure1_slot0;
                r8 = r7.clamp;
                r7 = _closure1_slot0;
                r7 = r7.finiteOr;
                r2 = r2.rvb;
                r2 = r2.ol;
                r2 = r2.rvb;
                r7 = r7.bind(r6)(r2, r5);
                r2 = _closure1_slot3;
                r7 = r7 + r2;
                r2 = -96.3;
                r2 = r8.bind(r6)(r7, r2, r5);
                r4['rvb'] = r2;
case 843:
                r2 = r0;
                r2 = r2.rvb;
                r1 = r1 != r2;
                if(!r1) { _fun9365_ip = 865; continue _fun9365 }
case 859:
                r1 = r2.ol;
case 865:
                if(!r1) { _fun9365_ip = 900; continue _fun9365 }
case 868:
                r2 = r0;
                r2 = r2.rvb;
                r2 = r2.ol;
                r2 = r2.dry;
                r4 = 'number';
                r2 = typeof r2;
                r1 = r4 === r2;
case 900:
                if(!r1) { _fun9365_ip = 985; continue _fun9365 }
case 903:
                r1 = r0;
                r2 = r1.rvb;
                r2 = r2.ol;
                r4 = _closure1_slot0;
                r4 = r4.clamp;
                r3 = _closure1_slot0;
                r3 = r3.finiteOr;
                r1 = r1.rvb;
                r1 = r1.ol;
                r1 = r1.dry;
                r3 = r3.bind(r6)(r1, r5);
                r1 = -96.3;
                r1 = r4.bind(r6)(r3, r1, r5);
                r2['dry'] = r1;
case 985:
                return r0;
            }
        };
        r1['applyMultichannelPostProcess'] = r0;
        r0 = a6;
        r3 = 0;
        r4 = r0[r3];
        r1 = a1;
        r0 = undefined;
        r1 = r1.bind(r0)(r4);
        var _closure1_slot0 = r1;
        r1 = 0.3;
        var _closure1_slot1 = r1;
        r15 = [0, 0, 1.6, 0];
        r17 = -1.5;
        r15[4] = r17;
        r15[5] = r3;
        r15[6] = r3;
        r8 = 0.5;
        r15[7] = r8;
        r18 = 0.2;
        r15[8] = r18;
        r15[9] = r3;
        r14 = [0, 0, 0.5];
        r14[3] = r17;
        r14[4] = r17;
        r7 = 1;
        r14[5] = r7;
        r1 = 1.5;
        r14[6] = r1;
        r14[7] = r1;
        r4 = 3;
        r14[8] = r4;
        r14[9] = r4;
        r13 = [0, 0, 0];
        r4 = -0.4;
        r13[3] = r4;
        r13[4] = r3;
        r13[5] = r3;
        r13[6] = r3;
        r5 = 3.4;
        r13[7] = r5;
        r13[8] = r3;
        r13[9] = r3;
        r12 = new Array(10);
        r5 = -2;
        r12[0] = r5;
        r9 = 0.8;
        r12[1] = r9;
        r12[2] = r7;
        r6 = 2;
        r12[3] = r6;
        r6 = 1.6;
        r12[4] = r6;
        r12[5] = r7;
        r16 = -1;
        r12[6] = r16;
        r6 = -1.6;
        r12[7] = r6;
        r6 = 1.4;
        r12[8] = r6;
        r12[9] = r18;
        r11 = [0, 1, 0.6, 0, 0, 0];
        r6 = -0.3;
        r11[6] = r6;
        r6 = -0.6;
        r11[7] = r6;
        r11[8] = r6;
        r6 = -0.2;
        r11[9] = r6;
        r10 = new Array(10);
        r10[0] = r16;
        r6 = -0.5;
        r10[1] = r6;
        r10[2] = r3;
        r10[3] = r8;
        r10[4] = r18;
        r10[5] = r9;
        r10[6] = r8;
        r10[7] = r16;
        r10[8] = r16;
        r10[9] = r16;
        r9 = [0, 0, 1.6, 0];
        r9[4] = r17;
        r9[5] = r3;
        r9[6] = r4;
        r9[7] = r7;
        r7 = 0.6;
        r9[8] = r7;
        r9[9] = r3;
        r8 = [0, 0, 0.5, 1.5, 1, 1];
        r8[6] = r6;
        r8[7] = r6;
        r8[8] = r1;
        r8[9] = r1;
        r7 = [1.5, 2, 1, 0.5, 0, 1];
        r7[6] = r6;
        r7[7] = r17;
        r7[8] = r5;
        r7[9] = r3;
        r6 = [0, 0, 1, 0.4, 0, 0, 1.3];
        r5 = -2.6;
        r6[7] = r5;
        r6[8] = r4;
        r6[9] = r3;
        r5 = new Array(10);
        r4 = -0.65;
        r5[0] = r4;
        r5[1] = r4;
        r4 = -0.15;
        r5[2] = r4;
        r4 = 0.35;
        r5[3] = r4;
        r4 = 1.35;
        r5[4] = r4;
        r4 = -0.05;
        r5[5] = r4;
        r4 = -0.85;
        r5[6] = r4;
        r4 = -3.25;
        r5[7] = r4;
        r5[8] = r4;
        r4 = -2.25;
        r5[9] = r4;
        r4 = [0, 0, 1, 0];
        r4[4] = r17;
        r4[5] = r3;
        r4[6] = r16;
        r16 = -2.2;
        r4[7] = r16;
        r16 = 0.4;
        r4[8] = r16;
        r4[9] = r3;
        r3 = {};
        r3[1] = r15;
        r15 = [0.2, 2, 2.6, 3, 2.6, 2, 1.6, 2, 2, 0.2];
        r3[2] = r15;
        r15 = [0.5, 1, 0.5, 0.5, 0.5, 3.5, 2.5, 2.5, 2, 3];
        r3[3] = r15;
        r3[4] = r14;
        r14 = [0.4, 0.4, 0.4, 2.2, 0.4, 1, 0.6, 4.3, 1.8, 0.4];
        r3[5] = r14;
        r3[6] = r13;
        r3[7] = r12;
        r3[8] = r11;
        r3[9] = r10;
        r3[10] = r9;
        r3[11] = r8;
        r3[12] = r7;
        r3[13] = r6;
        r3[14] = r5;
        r3[15] = r4;
        var _closure1_slot2 = r3;
        var _closure1_slot3 = r1;
        r1 = 10;
        var _closure1_slot4 = r1;
        var _closure1_slot5 = r1;
        var _closure1_slot6 = r1;
        r1 = -12;
        var _closure1_slot7 = r1;
        r1 = 12;
        var _closure1_slot8 = r1;
        r1 = ['se', 'dly', 'alc', 'cmp', 'bt', 'chorus', 'flanger', 'spspatial', 'rotate'];
        var _closure1_slot9 = r1;
        r1 = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
        var _closure1_slot10 = r1;
        return r0;
    };
factories[944]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r1 = a5;
        r0 = global;
        r5 = r0.Object;
        r4 = r5.defineProperty;
        r3 = {};
        r0 = true;
        r3['value'] = r0;
        r0 = '__esModule';
        r0 = r4.bind(r5)(r1, r0, r3);
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9368: for(var _fun9368_ip = 0; ; ) switch(_fun9368_ip) {
case 0:
                r7 = undefined;
                r5 = false;
                r4 = 'object';
                r3 = 'number';
                r0 = undefined;
                r9 = undefined;
                r6 = undefined;
                r2 = _closure1_slot0;
                r8 = r2.deepClone;
                r2 = a0;
                r2 = r8.bind(r7)(r2);
                r0 = r2;
                r2 = r2.se;
                if(!r2) { _fun9368_ip = 67; continue _fun9368 }
case 51:
                r8 = r0;
                r8 = r8.se;
                r8 = typeof r8;
                r2 = r4 === r8;
case 67:
                if(!r2) { _fun9368_ip = 85; continue _fun9368 }
case 70:
                r2 = r0;
                r2 = r2.se;
                r2['sshaper'] = r5;
case 85:
                r10 = _closure1_slot1;
                r2 = r10;
                var __iterator_values_3=Array.from(r2), __iterator_index_3=0; r8={return:function(){}}; r2=function(){if(__iterator_index_3>=__iterator_values_3.length){r8=undefined;return {value:undefined}}return {value:__iterator_values_3[__iterator_index_3++]}};
case 95:
                r11 = r2().value;
                r10 = r8;
                if(!(r10 !== r7)) { _fun9368_ip = 153; continue _fun9368 }
case 106: // try_start_0
                r10 = r0;
                r11 = r10[r11];
                r9 = r11;
                r10 = r11;
                if(!r11) { _fun9368_ip = 132; continue _fun9368 }
case 122:
                r11 = r9;
                r11 = typeof r11;
                r10 = r4 === r11;
case 132:
                if(!r10) { _fun9368_ip = 144; continue _fun9368 }
case 135:
                r10 = r9;
                r10['on'] = r5;
case 144: // try_end0
                _fun9368_ip = 95; continue _fun9368;
case 146: // catch_target0
                CatchBlockStart(arg_register=2);
                r8.return();
                throw r2;
case 153:
                r2 = r0;
                r2 = r2.rvb;
                if(!r2) { _fun9368_ip = 346; continue _fun9368 }
case 168:
                r2 = r0;
                r2 = r2.rvb;
                r2 = typeof r2;
                if(!(r4 === r2)) { _fun9368_ip = 346; continue _fun9368 }
case 187:
                r2 = r0;
                r2 = r2.rvb;
                r4 = r2.ol;
                if(!r4) { _fun9368_ip = 271; continue _fun9368 }
case 205:
                r2 = r4.rvb;
                r2 = typeof r2;
                if(!(r3 === r2)) { _fun9368_ip = 238; continue _fun9368 }
case 218:
                r8 = r4.rvb;
                r2 = _closure1_slot2;
                r2 = r8 - r2;
                r4['rvb'] = r2;
case 238:
                r2 = r4.er;
                r2 = typeof r2;
                if(!(r3 === r2)) { _fun9368_ip = 271; continue _fun9368 }
case 251:
                r8 = r4.er;
                r2 = _closure1_slot2;
                r2 = r8 - r2;
                r4['er'] = r2;
case 271:
                r2 = r0;
                r2 = r2.rvb;
                r2 = r2.on;
                if(!(r5 !== r2)) { _fun9368_ip = 346; continue _fun9368 }
case 289:
                r2 = r0;
                r2 = r2.rvb;
                r4 = r2.rvb;
                r2 = r4;
                if(!r2) { _fun9368_ip = 323; continue _fun9368 }
case 310:
                r8 = r4.dtime;
                r8 = typeof r8;
                r2 = r3 === r8;
case 323:
                if(!r2) { _fun9368_ip = 346; continue _fun9368 }
case 326:
                r8 = r4.dtime;
                r2 = _closure1_slot5;
                r2 = r8 * r2;
                r4['dtime'] = r2;
case 346:
                r2 = r0;
                r2 = r2.eq;
                if(!r2) { _fun9368_ip = 376; continue _fun9368 }
case 358:
                r4 = r0;
                r4 = r4.eq;
                r4 = r4.on;
                r2 = r5 !== r4;
case 376:
                if(!r2) { _fun9368_ip = 412; continue _fun9368 }
case 379:
                r4 = global;
                r9 = r4.Array;
                r8 = r9.isArray;
                r4 = r0;
                r4 = r4.eq;
                r4 = r4.eqs;
                r2 = r8.bind(r9)(r4);
case 412:
                if(!r2) { _fun9368_ip = 459; continue _fun9368 }
case 415:
                r2 = r0;
                r4 = r2.eq;
                r2 = r2.eq;
                r9 = r2.eqs;
                r8 = r9.map;
                r2 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
                    r1 = _closure1_slot3;
                    r0 = a0;
                    r0 = r0 * r1;
                    return r0;
                };
                r2 = r8.bind(r9)(r2);
                r4['eqs'] = r2;
case 459:
                r2 = r0;
                r2 = r2.peq;
                if(!r2) { _fun9368_ip = 641; continue _fun9368 }
case 474:
                r2 = r0;
                r2 = r2.peq;
                r2 = r2.on;
                if(!(r5 !== r2)) { _fun9368_ip = 641; continue _fun9368 }
case 495:
                r2 = global;
                r8 = r2.Array;
                r4 = r8.isArray;
                r2 = r0;
                r2 = r2.peq;
                r2 = r2.f;
                r2 = r4.bind(r8)(r2);
                if(!r2) { _fun9368_ip = 641; continue _fun9368 }
case 531:
                r2 = r0;
                r2 = r2.peq;
                r8 = r2.f;
                r2 = r8;
                var __iterator_values_4=Array.from(r2), __iterator_index_4=0; r4={return:function(){}}; r2=function(){if(__iterator_index_4>=__iterator_values_4.length){r4=undefined;return {value:undefined}}return {value:__iterator_values_4[__iterator_index_4++]}};
case 552:
                r10 = r2().value;
                r8 = r4;
                if(!(r8 !== r7)) { _fun9368_ip = 641; continue _fun9368 }
case 563: // try_start_1
                r6 = r10;
                r9 = r10;
                if(!r10) { _fun9368_ip = 584; continue _fun9368 }
case 572:
                r10 = r6;
                r10 = r10.on;
                r9 = r5 !== r10;
case 584:
                r8 = r9;
                if(!r9) { _fun9368_ip = 606; continue _fun9368 }
case 590:
                r9 = r6;
                r9 = r9.gain;
                r9 = typeof r9;
                r8 = r3 === r9;
case 606:
                if(!r8) { _fun9368_ip = 632; continue _fun9368 }
case 609:
                r9 = r6;
                r10 = r9.gain;
                r8 = _closure1_slot3;
                r8 = r10 * r8;
                r9['gain'] = r8;
case 632: // try_end1
                _fun9368_ip = 552; continue _fun9368;
case 634: // catch_target1
                CatchBlockStart(arg_register=2);
                r4.return();
                throw r2;
case 641:
                r2 = r0;
                r2 = r2.se;
                if(!r2) { _fun9368_ip = 671; continue _fun9368 }
case 653:
                r4 = r0;
                r4 = r4.se;
                r4 = r4.on;
                r2 = r5 !== r4;
case 671:
                if(!r2) { _fun9368_ip = 776; continue _fun9368 }
case 674:
                r2 = r0;
                r2 = r2.se;
                r2 = r2.stereoizer;
                r2 = typeof r2;
                if(!(r3 === r2)) { _fun9368_ip = 725; continue _fun9368 }
case 696:
                r2 = r0;
                r4 = r2.se;
                r5 = r4.stereoizer;
                r2 = _closure1_slot4;
                r2 = r5 * r2;
                r4['stereoizer'] = r2;
case 725:
                r2 = r0;
                r2 = r2.se;
                r2 = r2.presence;
                r2 = typeof r2;
                if(!(r3 === r2)) { _fun9368_ip = 776; continue _fun9368 }
case 747:
                r2 = r0;
                r2 = r2.se;
                r3 = r2.presence;
                r1 = _closure1_slot4;
                r1 = r3 * r1;
                r2['presence'] = r1;
case 776:
                return r0;
            }
        };
        r1['applyDegradation'] = r0;
        r1 = a6;
        r0 = 0;
        r3 = r1[r0];
        r1 = a1;
        r0 = undefined;
        r1 = r1.bind(r0)(r3);
        var _closure1_slot0 = r1;
        r1 = ['dly', 'chorus', 'flanger'];
        var _closure1_slot1 = r1;
        r1 = 3;
        var _closure1_slot2 = r1;
        r1 = 0.8;
        var _closure1_slot3 = r1;
        r1 = 0.6;
        var _closure1_slot4 = r1;
        r1 = 0.7;
        var _closure1_slot5 = r1;
        return r0;
    };
factories[945]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r2 = a5;
        r1 = global;
        r6 = r1.Object;
        r5 = r6.defineProperty;
        r4 = {};
        r0 = true;
        r4['value'] = r0;
        r0 = '__esModule';
        r0 = r5.bind(r6)(r2, r0, r4);
        r0 = undefined;
        r2['EMOTION_INDEX'] = r0;
        r2['EMOTION_LABELS'] = r0;
        r2['GENRE_INDEX'] = r0;
        r2['VALID_EMOTIONS'] = r0;
        r2['VALID_FAMILIES'] = r0;
        r4 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
            r0 = global;
            r0 = r0.Object;
            r0 = r0.prototype;
            r3 = r0.hasOwnProperty;
            r2 = r3.call;
            r1 = _closure1_slot0;
            r0 = a0;
            r0 = r2.bind(r3)(r1, r0);
            return r0;
        };
        r2['isEmotionLabel'] = r4;
        r5 = {'default': 0, 'ecstatic': 1, 'passionate': 2, 'energetic': 3, 'joyful': 4, 'sweet': 5, 'confident': 6, 'romantic': 7, 'relaxed': 8, 'healing': 9, 'calm': 10, 'nostalgic': 11, 'lonely': 12, 'sad': 13, 'bitter': 14, 'depressed': 15};
        r2['EMOTION_INDEX'] = r5;
        var _closure1_slot0 = r5;
        r4 = r1.Object;
        r3 = r4.keys;
        r5 = r3.bind(r4)(r5);
        r2['EMOTION_LABELS'] = r5;
        r3 = r1.Set;
        r4 = r3.prototype;
        r4 = Object.create(r4, {constructor: {value: r3}});
        r10 = r4;
        r9 = r5;
        r3 = originalConstruct(r3, [r9, r8]);
        r3 = r3 instanceof Object ? r3 : r4;
        r2['VALID_EMOTIONS'] = r3;
        r5 = {'pop-family': 0, 'folk-family': 1, 'rhythm-family': 2, 'instrumental-family': 3};
        r2['GENRE_INDEX'] = r5;
        r4 = r1.Set;
        r3 = r1.Object;
        r1 = r3.keys;
        r9 = r1.bind(r3)(r5);
        r3 = r4.prototype;
        r3 = Object.create(r3, {constructor: {value: r4}});
        r10 = r3;
        r1 = originalConstruct(r4, [r9, r8]);
        r1 = r1 instanceof Object ? r1 : r3;
        r2['VALID_FAMILIES'] = r1;
        return r0;
    };
factories[949]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r5 = a1;
        r3 = a5;
        r6 = a6;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: E, environment: r2
            _fun9413: for(var _fun9413_ip = 0; ; ) switch(_fun9413_ip) {
case 0:
                r3 = a0;
                r0 = global;
                r1 = r0.Array;
                r0 = r1.isArray;
                r0 = r0.bind(r1)(r3);
                if(!r0) { _fun9413_ip = 35; continue _fun9413 }
case 24:
                r2 = r3.length;
                r1 = 0;
                r0 = r2 > r1;
case 35:
                if(!r0) { _fun9413_ip = 62; continue _fun9413 }
case 38:
                r2 = r3.every;
                r1 = _closure1_slot3;
                r1 = r1.isPositiveInteger;
                r0 = r2.bind(r3)(r1);
case 62:
                return r0;
            }
        };
        var _closure1_slot8 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: x, environment: r2
            _fun9414: for(var _fun9414_ip = 0; ; ) switch(_fun9414_ip) {
case 0:
                r5 = a0;
                r2 = a1;
                r0 = null;
                if(!(r0 != r5)) { _fun9414_ip = 36; continue _fun9414 }
case 14:
                var _closure2_slot0 = r2;
                r4 = r5.find;
                r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
                    r0 = a0;
                    r1 = r0.name;
                    r0 = _closure2_slot0;
                    r0 = r1 === r0;
                    return r0;
                };
                r0 = r4.bind(r5)(r0);
                _fun9414_ip = 42; continue _fun9414;
case 36:
                _closure2_slot0 = r2;
                r0 = undefined;
case 42:
                return r0;
            }
        };
        var _closure1_slot9 = r0;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: F, environment: r2
            _fun9416: for(var _fun9416_ip = 0; ; ) switch(_fun9416_ip) {
case 0:
                r0 = a0;
                if(!r0) { _fun9416_ip = 29; continue _fun9416 }
case 6:
                r3 = _closure1_slot8;
                r2 = r0.shape;
                r4 = undefined;
                r2 = r3.bind(r4)(r2);
                if(r2) { _fun9416_ip = 33; continue _fun9416 }
case 29:
                r2 = undefined;
                return r2;
case 33:
                r3 = r0.name;
                r2 = r0.dtype;
                r1 = _closure1_slot1;
                r1 = r1.default;
                r0 = r0.shape;
                r1 = r1.bind(r4)(r0);
                r0 = {};
                r0['name'] = r3;
                r0['dtype'] = r2;
                r0['shape'] = r1;
                return r0;
            }
        };
        var _closure1_slot10 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: v, environment: r2
            _fun9417: for(var _fun9417_ip = 0; ; ) switch(_fun9417_ip) {
case 0:
                r2 = undefined;
                r1 = undefined;
                r4 = a0;
                var __iterator_values_5=Array.from(r4), __iterator_index_5=0; r3={return:function(){}}; r4=function(){if(__iterator_index_5>=__iterator_values_5.length){r3=undefined;return {value:undefined}}return {value:__iterator_values_5[__iterator_index_5++]}};
case 13:
                r6 = r4().value;
                r5 = r3;
                if(!(r5 !== r2)) { _fun9417_ip = 67; continue _fun9417 }
case 24: // try_start_0
                r1 = r6;
                r5 = _closure1_slot8;
                r5 = r5.bind(r2)(r6);
                if(r5) { _fun9417_ip = 41; continue _fun9417 }
case 39: // try_end0
                _fun9417_ip = 13; continue _fun9417;
case 41: // try_start_1
                r4 = _closure1_slot1;
                r4 = r4.default;
                r1 = r4.bind(r2)(r1);
case 55: // try_end1
                r3.return();
                return r1;
case 60: // catch_target0 // catch_target1
                CatchBlockStart(arg_register=1);
                r3.return();
                throw r1;
case 67:
                r0 = _closure1_slot1;
                r1 = r0.default;
                r0 = a1;
                r0 = r1.bind(r2)(r0);
                return r0;
            }
        };
        var _closure1_slot11 = r0;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: A, environment: r2
            _fun9418: for(var _fun9418_ip = 0; ; ) switch(_fun9418_ip) {
case 0:
                r4 = a0;
                r1 = undefined;
                r3 = _closure1_slot10;
                r2 = r4.runtimeTensor;
                r7 = r3.bind(r1)(r2);
                r6 = _closure1_slot11;
                r3 = r4.runtime;
                r5 = null;
                r2 = undefined;
                if(!(r5 != r3)) { _fun9418_ip = 47; continue _fun9418 }
case 41:
                r2 = r3.shape;
case 47:
                r3 = new Array(3);
                r3[0] = r2;
                r2 = undefined;
                if(!(r5 != r7)) { _fun9418_ip = 67; continue _fun9418 }
case 61:
                r2 = r7.shape;
case 67:
                r3[1] = r2;
                r8 = r4.requested;
                r2 = undefined;
                if(!(r5 != r8)) { _fun9418_ip = 89; continue _fun9418 }
case 83:
                r2 = r8.shape;
case 89:
                r3[2] = r2;
                r2 = r4.fallback;
                r2 = r2.shape;
                r2 = r6.bind(r1)(r3, r2);
                r6 = r4.runtime;
                r3 = undefined;
                if(!(r5 != r6)) { _fun9418_ip = 129; continue _fun9418 }
case 123:
                r3 = r6.dtype;
case 129:
                if(!(r5 == r3)) { _fun9418_ip = 148; continue _fun9418 }
case 133:
                r6 = undefined;
                if(!(r5 != r7)) { _fun9418_ip = 145; continue _fun9418 }
case 139:
                r6 = r7.dtype;
case 145:
                r3 = r6;
case 148:
                if(!(r5 == r3)) { _fun9418_ip = 173; continue _fun9418 }
case 152:
                r7 = r4.requested;
                r6 = undefined;
                if(!(r5 != r7)) { _fun9418_ip = 170; continue _fun9418 }
case 164:
                r6 = r7.dtype;
case 170:
                r3 = r6;
case 173:
                if(!(r5 == r3)) { _fun9418_ip = 189; continue _fun9418 }
case 177:
                r5 = r4.fallback;
                r3 = r5.dtype;
case 189:
                r4 = r4.name;
                r0 = _closure1_slot3;
                r0 = r0.getTensorSize;
                r1 = r0.bind(r1)(r2);
                r0 = {};
                r0['name'] = r4;
                r0['dtype'] = r3;
                r0['shape'] = r2;
                r0['size'] = r1;
                return r0;
            }
        };
        var _closure1_slot12 = r0;
        r0 = function(a0, a1, a2) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: h, environment: r2
            _fun9419: for(var _fun9419_ip = 0; ; ) switch(_fun9419_ip) {
case 0:
                r3 = a0;
                r5 = a1;
                r4 = a2;
                if(!r3) { _fun9419_ip = 25; continue _fun9419 }
case 14:
                r2 = r3.length;
                r0 = 0;
                if(!(!(r2 > r0))) { _fun9419_ip = 84; continue _fun9419 }
case 25:
                if(!r5) { _fun9419_ip = 39; continue _fun9419 }
case 28:
                r2 = r5.length;
                r0 = 0;
                if(!(!(r2 > r0))) { _fun9419_ip = 67; continue _fun9419 }
case 39:
                r0 = global;
                r2 = r0.Object;
                r0 = r2.keys;
                r6 = null;
                if(!(r6 == r4)) { _fun9419_ip = 60; continue _fun9419 }
case 58:
                r4 = {};
case 60:
                r0 = r0.bind(r2)(r4);
                _fun9419_ip = 82; continue _fun9419;
case 67:
                r4 = r5.map;
                r2 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
                    r0 = a0;
                    r0 = r0.name;
                    return r0;
                };
                r0 = r4.bind(r5)(r2);
case 82:
                _fun9419_ip = 99; continue _fun9419;
case 84:
                r2 = r3.map;
                r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
                    r0 = a0;
                    r0 = r0.name;
                    return r0;
                };
                r0 = r2.bind(r3)(r1);
case 99:
                return r0;
            }
        };
        var _closure1_slot13 = r0;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: y, environment: r2
            r1 = _closure1_slot6;
            r2 = [1];
            r2[1] = r1;
            r1 = _closure1_slot6;
            r0 = {};
            r3 = a0;
            r0['name'] = r3;
            r3 = 'float32';
            r0['dtype'] = r3;
            r0['shape'] = r2;
            r0['size'] = r1;
            return r0;
        };
        var _closure1_slot14 = r0;
        r0 = 0;
        r1 = r6[r0];
        r0 = undefined;
        r4 = r5.bind(r0)(r1);
        r1 = global;
        r9 = r1.Object;
        r8 = r9.defineProperty;
        r7 = {};
        r1 = true;
        r7['value'] = r1;
        r1 = '__esModule';
        r1 = r8.bind(r9)(r3, r1, r7);
        r3['EMOFX_AEV2_CORE_SIZE'] = r0;
        r3['EMOFX_AEV2_DEFAULT_SIZE'] = r0;
        r3['EMOFX_BASE_EXTRA_FLAT_DEFAULT_SIZE'] = r0;
        r3['EMOFX_FEATURE_CORE_SIZE'] = r0;
        r3['EMOFX_FEATURE_DEFAULT_SIZE'] = r0;
        r3['EMOFX_TENSOR_NAMES'] = r0;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            r0 = a0;
            r3 = r0.modelId;
            r1 = r0.inputs;
            r1 = r1.emotion_idx;
            r6 = r1.name;
            r1 = r0.inputs;
            r1 = r1.emotion_idx;
            r5 = r1.dtype;
            r2 = _closure1_slot1;
            r4 = r2.default;
            r2 = r0.inputs;
            r2 = r2.emotion_idx;
            r2 = r2.shape;
            r7 = undefined;
            r2 = r4.bind(r7)(r2);
            r4 = {};
            r4['name'] = r6;
            r4['dtype'] = r5;
            r4['shape'] = r2;
            r2 = new Array(4);
            r2[0] = r4;
            r4 = r0.inputs;
            r4 = r4.genre_idx;
            r8 = r4.name;
            r4 = r0.inputs;
            r4 = r4.genre_idx;
            r6 = r4.dtype;
            r4 = _closure1_slot1;
            r5 = r4.default;
            r4 = r0.inputs;
            r4 = r4.genre_idx;
            r4 = r4.shape;
            r5 = r5.bind(r7)(r4);
            r4 = {};
            r4['name'] = r8;
            r4['dtype'] = r6;
            r4['shape'] = r5;
            r2[1] = r4;
            r4 = r0.inputs;
            r4 = r4.features;
            r8 = r4.name;
            r4 = r0.inputs;
            r4 = r4.features;
            r6 = r4.dtype;
            r4 = _closure1_slot1;
            r5 = r4.default;
            r4 = r0.inputs;
            r4 = r4.features;
            r4 = r4.shape;
            r5 = r5.bind(r7)(r4);
            r4 = {};
            r4['name'] = r8;
            r4['dtype'] = r6;
            r4['shape'] = r5;
            r2[2] = r4;
            r4 = r0.inputs;
            r4 = r4.base_extra_flat;
            r6 = r4.name;
            r4 = r0.inputs;
            r4 = r4.base_extra_flat;
            r5 = r4.dtype;
            r1 = _closure1_slot1;
            r4 = r1.default;
            r1 = r0.inputs;
            r1 = r1.base_extra_flat;
            r1 = r1.shape;
            r4 = r4.bind(r7)(r1);
            r1 = {};
            r1['name'] = r6;
            r1['dtype'] = r5;
            r1['shape'] = r4;
            r2[3] = r1;
            r1 = global;
            r4 = r1.Object;
            r1 = r4.values;
            r0 = r0.outputs;
            r4 = r1.bind(r4)(r0);
            r1 = r4.map;
            r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r0
                r0 = a0;
                r3 = r0.name;
                r2 = r0.dtype;
                r1 = _closure1_slot1;
                r4 = r1.default;
                r1 = r0.shape;
                r0 = undefined;
                r1 = r4.bind(r0)(r1);
                r0 = {};
                r0['name'] = r3;
                r0['dtype'] = r2;
                r0['shape'] = r1;
                return r0;
            };
            r1 = r1.bind(r4)(r0);
            r0 = {};
            r0['modelId'] = r3;
            r0['inputs'] = r2;
            r0['outputs'] = r1;
            return r0;
        };
        r3['buildModelSignatureFromResolved'] = r1;
        r1 = function() {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9425: for(var _fun9425_ip = 0; ; ) switch(_fun9425_ip) {
case 0:
                r7 = undefined;
                r3 = undefined;
                r5 = undefined;
                r1 = arguments.length;
                r2 = 0;
                if(!(r1 > r2)) { _fun9425_ip = 25; continue _fun9425 }
case 17:
                r1 = arguments[r2];
                if(!(r7 === r1)) { _fun9425_ip = 29; continue _fun9425 }
case 25:
                r1 = {};
                _fun9425_ip = 33; continue _fun9425;
case 29:
                r1 = arguments[r2];
case 33:
                r3 = r1.fallback;
                r2 = null;
                if(!(r2 == r3)) { _fun9425_ip = 373; continue _fun9425 }
case 48:
                r6 = _closure1_slot2;
                r9 = r6.EMOFX_DEFAULT_MODEL_ID;
                r6 = _closure1_slot7;
                r6 = r6.emotion_idx;
                r12 = {};
                r12['name'] = r6;
                r8 = 'int32';
                r12['dtype'] = r8;
                r6 = [1];
                r12['shape'] = r6;
                r6 = 1;
                r12['size'] = r6;
                r10 = _closure1_slot7;
                r10 = r10.genre_idx;
                r11 = {};
                r11['name'] = r10;
                r11['dtype'] = r8;
                r8 = [1];
                r11['shape'] = r8;
                r11['size'] = r6;
                r6 = _closure1_slot7;
                r13 = r6.features;
                r6 = _closure1_slot4;
                r8 = [1];
                r8[1] = r6;
                r6 = _closure1_slot4;
                r10 = {};
                r10['name'] = r13;
                r13 = 'float32';
                r10['dtype'] = r13;
                r10['shape'] = r8;
                r10['size'] = r6;
                r6 = _closure1_slot7;
                r15 = r6.base_extra_flat;
                r6 = _closure1_slot5;
                r14 = [1];
                r14[1] = r6;
                r8 = _closure1_slot5;
                r6 = {};
                r6['name'] = r15;
                r6['dtype'] = r13;
                r6['shape'] = r14;
                r6['size'] = r8;
                r8 = {};
                r8['emotion_idx'] = r12;
                r8['genre_idx'] = r11;
                r8['features'] = r10;
                r8['base_extra_flat'] = r6;
                r6 = _closure1_slot0;
                r11 = r6.default;
                r6 = _closure1_slot7;
                r10 = r6.aev2;
                r6 = _closure1_slot7;
                r14 = r6.aev2;
                r6 = _closure1_slot6;
                r12 = [1];
                r12[1] = r6;
                r4 = _closure1_slot6;
                r6 = {};
                r6['name'] = r14;
                r6['dtype'] = r13;
                r6['shape'] = r12;
                r6['size'] = r4;
                r4 = {};
                r6 = r11.bind(r7)(r4, r10, r6);
                r4 = {};
                r4['modelId'] = r9;
                r4['inputs'] = r8;
                r4['outputs'] = r6;
                r3 = r4;
case 373:
                r4 = r1.requested;
                r13 = undefined;
                if(!(r2 != r4)) { _fun9425_ip = 391; continue _fun9425 }
case 385:
                r13 = r4.inputs;
case 391:
                r4 = r1.runtime;
                r12 = undefined;
                if(!(r2 != r4)) { _fun9425_ip = 409; continue _fun9425 }
case 403:
                r12 = r4.inputs;
case 409:
                r4 = r1.requested;
                r8 = undefined;
                if(!(r2 != r4)) { _fun9425_ip = 427; continue _fun9425 }
case 421:
                r8 = r4.outputs;
case 427:
                r4 = r1.runtime;
                r6 = undefined;
                if(!(r2 != r4)) { _fun9425_ip = 445; continue _fun9425 }
case 439:
                r6 = r4.outputs;
case 445:
                var _closure2_slot0 = r1;
                var _closure2_slot1 = r3;
                var _closure2_slot2 = r8;
                var _closure2_slot4 = r7;
                var _closure2_slot3 = r6;
                r4 = _closure1_slot13;
                r3 = r3.outputs;
                r6 = r4.bind(r7)(r6, r8, r3);
                r3 = {};
                _closure2_slot4 = r3;
                r5 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: Z, environment: r0
                    _fun9426: for(var _fun9426_ip = 0; ; ) switch(_fun9426_ip) {
case 0:
                        r3 = a0;
                        r0 = undefined;
                        r2 = _closure2_slot1;
                        r2 = r2.outputs;
                        r8 = r2[r3];
                        r5 = null;
                        if(!(r5 == r8)) { _fun9426_ip = 42; continue _fun9426 }
case 30:
                        r2 = _closure1_slot14;
                        r8 = r2.bind(r0)(r3);
case 42:
                        r2 = _closure2_slot4;
                        r4 = _closure1_slot12;
                        r10 = _closure1_slot9;
                        r6 = _closure2_slot2;
                        r7 = r10.bind(r0)(r6, r3);
                        r6 = _closure2_slot3;
                        r6 = r10.bind(r0)(r6, r3);
                        r9 = _closure2_slot0;
                        r10 = r9.outputs;
                        if(!(r5 != r10)) { _fun9426_ip = 113; continue _fun9426 }
case 91:
                        var _closure3_slot0 = r3;
                        r9 = r10.find;
                        r5 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
                            r0 = a0;
                            r1 = r0.name;
                            r0 = _closure3_slot0;
                            r0 = r1 === r0;
                            return r0;
                        };
                        r5 = r9.bind(r10)(r5);
                        _fun9426_ip = 119; continue _fun9426;
case 113:
                        _closure3_slot0 = r3;
                        r5 = undefined;
case 119:
                        r1 = {};
                        r1['name'] = r3;
                        r1['fallback'] = r8;
                        r1['requested'] = r7;
                        r1['runtime'] = r6;
                        r1['runtimeTensor'] = r5;
                        r1 = r4.bind(r0)(r1);
                        r2[r3] = r1;
                        return r0;
                    }
                };
                r3 = r6;
                var __iterator_values_6=Array.from(r3), __iterator_index_6=0; r4={return:function(){}}; r3=function(){if(__iterator_index_6>=__iterator_values_6.length){r4=undefined;return {value:undefined}}return {value:__iterator_values_6[__iterator_index_6++]}};
case 502:
                r8 = r3().value;
                r6 = r4;
                if(!(r6 !== r7)) { _fun9425_ip = 530; continue _fun9425 }
case 513: // try_start_0
                r6 = r5;
                r6 = r6.bind(r7)(r8);
case 521: // try_end0
                _fun9425_ip = 502; continue _fun9425;
case 523: // catch_target0
                CatchBlockStart(arg_register=3);
                r4.return();
                throw r3;
case 530:
                r3 = _closure2_slot0;
                r4 = r3.runtime;
                r3 = undefined;
                if(!(r2 != r4)) { _fun9425_ip = 552; continue _fun9425 }
case 546:
                r3 = r4.modelId;
case 552:
                if(!(r2 == r3)) { _fun9425_ip = 581; continue _fun9425 }
case 556:
                r4 = _closure2_slot0;
                r5 = r4.requested;
                r4 = undefined;
                if(!(r2 != r5)) { _fun9425_ip = 578; continue _fun9425 }
case 572:
                r4 = r5.modelId;
case 578:
                r3 = r4;
case 581:
                if(!(r2 == r3)) { _fun9425_ip = 595; continue _fun9425 }
case 585:
                r2 = _closure2_slot1;
                r3 = r2.modelId;
case 595:
                r2 = _closure1_slot12;
                r4 = _closure1_slot7;
                r10 = r4.emotion_idx;
                r4 = _closure2_slot1;
                r4 = r4.inputs;
                r9 = r4.emotion_idx;
                r8 = _closure1_slot9;
                r4 = _closure1_slot7;
                r4 = r4.emotion_idx;
                r6 = r8.bind(r7)(r13, r4);
                r4 = _closure1_slot7;
                r4 = r4.emotion_idx;
                r5 = r8.bind(r7)(r12, r4);
                r4 = {};
                r4['name'] = r10;
                r4['fallback'] = r9;
                r4['requested'] = r6;
                r4['runtime'] = r5;
                r6 = r2.bind(r7)(r4);
                r4 = _closure1_slot7;
                r11 = r4.genre_idx;
                r4 = _closure2_slot1;
                r4 = r4.inputs;
                r10 = r4.genre_idx;
                r4 = _closure1_slot7;
                r4 = r4.genre_idx;
                r9 = r8.bind(r7)(r13, r4);
                r4 = _closure1_slot7;
                r4 = r4.genre_idx;
                r5 = r8.bind(r7)(r12, r4);
                r4 = {};
                r4['name'] = r11;
                r4['fallback'] = r10;
                r4['requested'] = r9;
                r4['runtime'] = r5;
                r5 = r2.bind(r7)(r4);
                r4 = _closure1_slot7;
                r14 = r4.features;
                r4 = _closure2_slot1;
                r4 = r4.inputs;
                r11 = r4.features;
                r4 = _closure1_slot7;
                r4 = r4.features;
                r10 = r8.bind(r7)(r13, r4);
                r4 = _closure1_slot7;
                r4 = r4.features;
                r9 = r8.bind(r7)(r12, r4);
                r4 = {};
                r4['name'] = r14;
                r4['fallback'] = r11;
                r4['requested'] = r10;
                r4['runtime'] = r9;
                r4 = r2.bind(r7)(r4);
                r9 = _closure1_slot7;
                r11 = r9.base_extra_flat;
                r9 = _closure2_slot1;
                r9 = r9.inputs;
                r10 = r9.base_extra_flat;
                r9 = _closure1_slot7;
                r9 = r9.base_extra_flat;
                r9 = r8.bind(r7)(r13, r9);
                r1 = _closure1_slot7;
                r1 = r1.base_extra_flat;
                r8 = r8.bind(r7)(r12, r1);
                r1 = {};
                r1['name'] = r11;
                r1['fallback'] = r10;
                r1['requested'] = r9;
                r1['runtime'] = r8;
                r1 = r2.bind(r7)(r1);
                r2 = {};
                r2['emotion_idx'] = r6;
                r2['genre_idx'] = r5;
                r2['features'] = r4;
                r2['base_extra_flat'] = r1;
                r1 = _closure2_slot4;
                r0 = {};
                r0['modelId'] = r3;
                r0['inputs'] = r2;
                r0['outputs'] = r1;
                return r0;
            }
        };
        r3['resolveEmoFXModelSignature'] = r1;
        r1 = 1;
        r1 = r6[r1];
        r1 = r5.bind(r0)(r1);
        r1 = r4.bind(r0)(r1);
        var _closure1_slot0 = r1;
        r1 = 2;
        r1 = r6[r1];
        r1 = r5.bind(r0)(r1);
        r1 = r4.bind(r0)(r1);
        var _closure1_slot1 = r1;
        r1 = 3;
        r1 = r6[r1];
        r1 = r5.bind(r0)(r1);
        var _closure1_slot2 = r1;
        r4 = 4;
        r4 = r6[r4];
        r4 = r5.bind(r0)(r4);
        var _closure1_slot3 = r4;
        r4 = 50;
        r3['EMOFX_FEATURE_CORE_SIZE'] = r4;
        r4 = r1.EMOFX_FEATURE_DEFAULT_SIZE;
        r3['EMOFX_FEATURE_DEFAULT_SIZE'] = r4;
        var _closure1_slot4 = r4;
        r4 = 19;
        r3['EMOFX_AEV2_CORE_SIZE'] = r4;
        r4 = r1.EMOFX_BASE_EXTRA_FLAT_DEFAULT_SIZE;
        r3['EMOFX_BASE_EXTRA_FLAT_DEFAULT_SIZE'] = r4;
        var _closure1_slot5 = r4;
        r1 = r1.EMOFX_AEV2_DEFAULT_SIZE;
        r3['EMOFX_AEV2_DEFAULT_SIZE'] = r1;
        var _closure1_slot6 = r1;
        r1 = {'emotion_idx': 'emotion_idx', 'genre_idx': 'genre_idx', 'features': 'features', 'base_extra_flat': 'base_extra_flat', 'aev2': 'aev2'};
        r3['EMOFX_TENSOR_NAMES'] = r1;
        var _closure1_slot7 = r1;
        return r0;
    };
factories[950]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r2 = a5;
        r0 = global;
        r4 = r0.Object;
        r3 = r4.defineProperty;
        r1 = {};
        r0 = true;
        r1['value'] = r0;
        r0 = '__esModule';
        r0 = r3.bind(r4)(r2, r0, r1);
        r0 = undefined;
        r2['EMOFX_AEV2_DEFAULT_SIZE'] = r0;
        r2['EMOFX_BASE_EXTRA_FLAT_DEFAULT_SIZE'] = r0;
        r2['EMOFX_DEFAULT_MODEL_ID'] = r0;
        r2['EMOFX_FEATURE_DEFAULT_SIZE'] = r0;
        r1 = 'motional_sound_effects_model';
        r2['EMOFX_DEFAULT_MODEL_ID'] = r1;
        r1 = 64;
        r2['EMOFX_FEATURE_DEFAULT_SIZE'] = r1;
        r1 = 32;
        r2['EMOFX_BASE_EXTRA_FLAT_DEFAULT_SIZE'] = r1;
        r2['EMOFX_AEV2_DEFAULT_SIZE'] = r1;
        return r0;
    };
factories[952]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r3 = a1;
        r1 = a5;
        r4 = a6;
        r0 = global;
        r7 = r0.Object;
        r6 = r7.defineProperty;
        r5 = {};
        r0 = true;
        r5['value'] = r0;
        r0 = '__esModule';
        r0 = r6.bind(r7)(r1, r0, r5);
        r0 = function(a0, a1, a2) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9437: for(var _fun9437_ip = 0; ; ) switch(_fun9437_ip) {
case 0:
                r7 = a0;
                r5 = a2;
                r4 = 0;
                r2 = undefined;
                r1 = global;
                r8 = r1.Object;
                r6 = r8.keys;
                r3 = a1;
                r3 = r3.outputs;
                r3 = r6.bind(r8)(r3);
                r3 = r3[r4];
                if(r3) { _fun9437_ip = 78; continue _fun9437 }
case 46:
                r9 = r1.Error;
                r6 = r9.prototype;
                r8 = Object.create(r6, {constructor: {value: r9}});
                r15 = 'decode: no output tensor defined in signature';
                r16 = r8;
                r6 = originalConstruct(r9, [r15, r14]);
                r6 = r6 instanceof Object ? r6 : r8;
                throw r6;
case 78:
                var _closure2_slot0 = r3;
                r6 = r7.find;
                r3 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r0
                    r0 = a0;
                    r1 = r0.name;
                    r0 = _closure2_slot0;
                    r0 = r1 === r0;
                    return r0;
                };
                r6 = r6.bind(r7)(r3);
                if(r6) { _fun9437_ip = 149; continue _fun9437 }
case 101:
                r7 = r1.Error;
                r3 = _closure2_slot0;
                r0 = 'decode: missing "';
                r8 = r0 + r3;
                r0 = r7.prototype;
                r3 = Object.create(r0, {constructor: {value: r7}});
                r0 = '" tensor in outputs';
                r15 = r8 + r0;
                r16 = r3;
                r0 = originalConstruct(r7, [r15, r14]);
                r0 = r0 instanceof Object ? r0 : r3;
                throw r0;
case 149:
                r0 = r6.data;
                r8 = r1.Array;
                r7 = r8.isArray;
                r3 = r6.shape;
                r3 = r7.bind(r8)(r3);
                if(!r3) { _fun9437_ip = 194; continue _fun9437 }
case 179:
                r3 = r6.shape;
                r3 = r3.length;
                if(!(!(r3 > r4))) { _fun9437_ip = 218; continue _fun9437 }
case 194:
                r8 = null;
                r7 = undefined;
                if(!(r8 != r0)) { _fun9437_ip = 207; continue _fun9437 }
case 202:
                r7 = r0.length;
case 207:
                r3 = 0;
                if(!(r8 != r7)) { _fun9437_ip = 216; continue _fun9437 }
case 213:
                r3 = r7;
case 216:
                _fun9437_ip = 242; continue _fun9437;
case 218:
                r7 = _closure1_slot2;
                r8 = r7.getTensorSize;
                r7 = r6.shape;
                r3 = r8.bind(r2)(r7);
case 242:
                if(!r0) { _fun9437_ip = 297; continue _fun9437 }
case 245:
                r9 = r1.Array;
                r8 = r9.isArray;
                r7 = r6.shape;
                r7 = r8.bind(r9)(r7);
                if(!r7) { _fun9437_ip = 297; continue _fun9437 }
case 270:
                r6 = r6.shape;
                r6 = r6.length;
                if(!(r6 > r4)) { _fun9437_ip = 297; continue _fun9437 }
case 285:
                r6 = r0.length;
                if(!(r6 === r3)) { _fun9437_ip = 600; continue _fun9437 }
case 297:
                if(!r0) { _fun9437_ip = 517; continue _fun9437 }
case 303:
                r8 = r0.length;
                r7 = _closure1_slot1;
                r7 = r7.EMOFX_AEV2_CORE_SIZE;
                if(!(!(r8 < r7))) { _fun9437_ip = 517; continue _fun9437 }
case 328:
                r7 = r0.slice;
                r6 = _closure1_slot0;
                r6 = r6.AEV2_EQ_GAIN_DB_SIZE;
                r9 = r7.bind(r0)(r4, r6);
                r13 = r5.reverb;
                r6 = 10;
                r12 = r0[r6];
                r6 = 11;
                r11 = r0[r6];
                r6 = 12;
                r10 = r0[r6];
                r6 = 13;
                r7 = r0[r6];
                r6 = 14;
                r6 = r0[r6];
                r8 = {};
                r8['on'] = r13;
                r8['reverb_time'] = r12;
                r8['dry_level'] = r11;
                r8['wet_level'] = r10;
                r8['room_size'] = r7;
                r8['diffusion'] = r6;
                r11 = r5.delay;
                r6 = 15;
                r10 = r0[r6];
                r6 = 16;
                r6 = r0[r6];
                r7 = {};
                r7['on'] = r11;
                r7['delay_time'] = r10;
                r7['feedback'] = r6;
                r11 = r5.stereo_enhance;
                r5 = 17;
                r10 = r0[r5];
                r5 = 18;
                r5 = r0[r5];
                r6 = {};
                r6['on'] = r11;
                r6['stereo_width'] = r10;
                r6['stereo_depth'] = r5;
                r5 = {};
                r5['eq_gain_db'] = r9;
                r5['reverb'] = r8;
                r5['delay'] = r7;
                r5['stereo_enhance'] = r6;
                return r5;
case 517:
                r5 = r1.Error;
                r6 = null;
                r2 = undefined;
                if(!(r6 != r0)) { _fun9437_ip = 536; continue _fun9437 }
case 531:
                r2 = r0.length;
case 536:
                r4 = 0;
                if(!(r6 != r2)) { _fun9437_ip = 545; continue _fun9437 }
case 542:
                r4 = r2;
case 545:
                r2 = 'decode: aev2 tensor data length ';
                r6 = r2 + r4;
                r2 = _closure1_slot1;
                r4 = r2.EMOFX_AEV2_CORE_SIZE;
                r2 = ' < ';
                r2 = r6 + r2;
                r15 = r2 + r4;
                r4 = r5.prototype;
                r4 = Object.create(r4, {constructor: {value: r5}});
                r16 = r4;
                r2 = originalConstruct(r5, [r15, r14]);
                r2 = r2 instanceof Object ? r2 : r4;
                throw r2;
case 600:
                r2 = r1.Error;
                r1 = 'decode: aev2 tensor shape/data mismatch (';
                r3 = r1 + r3;
                r1 = r0.length;
                r0 = ' vs ';
                r0 = r3 + r0;
                r3 = r0 + r1;
                r0 = r2.prototype;
                r1 = Object.create(r0, {constructor: {value: r2}});
                r0 = ')';
                r15 = r3 + r0;
                r16 = r1;
                r0 = originalConstruct(r2, [r15, r14]);
                r0 = r0 instanceof Object ? r0 : r1;
                throw r0;
            }
        };
        r1['decodeEmoFX'] = r0;
        r0 = 0;
        r1 = r4[r0];
        r0 = undefined;
        r1 = r3.bind(r0)(r1);
        var _closure1_slot0 = r1;
        r1 = 1;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot1 = r1;
        r1 = 2;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot2 = r1;
        return r0;
    };
factories[953]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r3 = a1;
        r6 = a5;
        r4 = a6;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: o, environment: r2
            _fun9440: for(var _fun9440_ip = 0; ; ) switch(_fun9440_ip) {
case 0:
                r3 = a0;
                r1 = a1;
                r8 = undefined;
                r2 = _closure1_slot3;
                r2 = r2.EMOFX_BASE_EXTRA_FLAT_DEFAULT_SIZE;
                if(!(r1 === r2)) { _fun9440_ip = 742; continue _fun9440 }
case 28:
                if(r3) { _fun9440_ip = 77; continue _fun9440 }
case 31:
                r2 = global;
                r2 = r2.Array;
                r4 = r2.prototype;
                r4 = Object.create(r4, {constructor: {value: r2}});
                r17 = r4;
                r16 = r1;
                r2 = originalConstruct(r2, [r16, r15]);
                r5 = r2 instanceof Object ? r2 : r4;
                r4 = r5.fill;
                r2 = 0;
                r2 = r4.bind(r5)(r2);
                return r2;
case 77:
                r11 = r3.eq_gain_db;
                r2 = global;
                r5 = r2.Array;
                r4 = r5.isArray;
                r4 = r4.bind(r5)(r11);
                if(!r4) { _fun9440_ip = 696; continue _fun9440 }
case 107:
                r5 = r11.length;
                r4 = _closure1_slot1;
                r4 = r4.AEV2_EQ_GAIN_DB_SIZE;
                if(!(r5 === r4)) { _fun9440_ip = 696; continue _fun9440 }
case 129:
                r5 = r3.reverb;
                r10 = null;
                r4 = undefined;
                if(!(r10 != r5)) { _fun9440_ip = 148; continue _fun9440 }
case 143:
                r4 = r5.on;
case 148:
                r7 = false;
                if(!(r7 !== r4)) { _fun9440_ip = 198; continue _fun9440 }
case 154:
                r12 = r2.Object;
                r9 = r12.assign;
                r4 = _closure1_slot1;
                r4 = r4.AEV2_MIN_VALUES;
                r6 = r4.reverb;
                r5 = r3.reverb;
                r4 = {};
                r6 = r9.bind(r12)(r4, r6, r5);
                _fun9440_ip = 233; continue _fun9440;
case 198:
                r12 = r2.Object;
                r9 = r12.assign;
                r4 = _closure1_slot1;
                r4 = r4.AEV2_MIN_VALUES;
                r5 = r4.reverb;
                r4 = {};
                r6 = r9.bind(r12)(r4, r5);
case 233:
                r5 = r3.delay;
                r4 = undefined;
                if(!(r10 != r5)) { _fun9440_ip = 250; continue _fun9440 }
case 245:
                r4 = r5.on;
case 250:
                if(!(r7 !== r4)) { _fun9440_ip = 298; continue _fun9440 }
case 254:
                r13 = r2.Object;
                r12 = r13.assign;
                r4 = _closure1_slot1;
                r4 = r4.AEV2_MIN_VALUES;
                r9 = r4.delay;
                r5 = r3.delay;
                r4 = {};
                r5 = r12.bind(r13)(r4, r9, r5);
                _fun9440_ip = 333; continue _fun9440;
case 298:
                r13 = r2.Object;
                r12 = r13.assign;
                r4 = _closure1_slot1;
                r4 = r4.AEV2_MIN_VALUES;
                r9 = r4.delay;
                r4 = {};
                r5 = r12.bind(r13)(r4, r9);
case 333:
                r9 = r3.stereo_enhance;
                r4 = undefined;
                if(!(r10 != r9)) { _fun9440_ip = 350; continue _fun9440 }
case 345:
                r4 = r9.on;
case 350:
                if(!(r7 !== r4)) { _fun9440_ip = 398; continue _fun9440 }
case 354:
                r10 = r2.Object;
                r9 = r10.assign;
                r4 = _closure1_slot1;
                r4 = r4.AEV2_MIN_VALUES;
                r7 = r4.stereo_enhance;
                r4 = r3.stereo_enhance;
                r3 = {};
                r4 = r9.bind(r10)(r3, r7, r4);
                _fun9440_ip = 433; continue _fun9440;
case 398:
                r10 = r2.Object;
                r9 = r10.assign;
                r3 = _closure1_slot1;
                r3 = r3.AEV2_MIN_VALUES;
                r7 = r3.stereo_enhance;
                r3 = {};
                r4 = r9.bind(r10)(r3, r7);
case 433:
                r3 = new Array(0);
                r10 = r3.push;
                r9 = r10.apply;
                r7 = _closure1_slot0;
                r7 = r7.default;
                r7 = r7.bind(r8)(r11);
                r7 = r9.bind(r10)(r3, r7);
                r9 = r3.push;
                r7 = r6.reverb_time;
                r7 = r9.bind(r3)(r7);
                r9 = r3.push;
                r7 = r6.dry_level;
                r7 = r9.bind(r3)(r7);
                r9 = r3.push;
                r7 = r6.wet_level;
                r7 = r9.bind(r3)(r7);
                r9 = r3.push;
                r7 = r6.room_size;
                r7 = r9.bind(r3)(r7);
                r7 = r3.push;
                r6 = r6.diffusion;
                r6 = r7.bind(r3)(r6);
                r7 = r3.push;
                r6 = r5.delay_time;
                r6 = r7.bind(r3)(r6);
                r6 = r3.push;
                r5 = r5.feedback;
                r5 = r6.bind(r3)(r5);
                r6 = r3.push;
                r5 = r4.stereo_width;
                r5 = r6.bind(r3)(r5);
                r5 = r3.push;
                r4 = r4.stereo_depth;
                r4 = r5.bind(r3)(r4);
                r6 = r3.push;
                r5 = r6.apply;
                r4 = _closure1_slot0;
                r7 = r4.default;
                r10 = r2.Array;
                r4 = _closure1_slot3;
                r4 = r4.EMOFX_AEV2_CORE_SIZE;
                r16 = r1 - r4;
                r9 = r10.prototype;
                r9 = Object.create(r9, {constructor: {value: r10}});
                r17 = r9;
                r4 = originalConstruct(r10, [r16, r15]);
                r10 = r4 instanceof Object ? r4 : r9;
                r9 = r10.fill;
                r4 = 0;
                r4 = r9.bind(r10)(r4);
                r4 = r7.bind(r8)(r4);
                r4 = r5.bind(r6)(r3, r4);
                return r3;
case 696:
                r4 = r2.Error;
                r2 = _closure1_slot1;
                r3 = r2.AEV2_EQ_GAIN_DB_SIZE;
                 r2 = 'Độ dài của base_extra_aev2.eq_gain_db phải là ';
                r16 = r2 + r3;
                r3 = r4.prototype;
                r3 = Object.create(r3, {constructor: {value: r4}});
                r17 = r3;
                r2 = originalConstruct(r4, [r16, r15]);
                r2 = r2 instanceof Object ? r2 : r3;
                throw r2;
case 742:
                r2 = global;
                r2 = r2.Error;
                r0 = _closure1_slot3;
                r3 = r0.EMOFX_BASE_EXTRA_FLAT_DEFAULT_SIZE;
                 r0 = 'Kích thước của base_extra_flat phải là ';
                r3 = r0 + r3;
                 r0 = ', hiện tại là ';
                r0 = r3 + r0;
                r16 = r0 + r1;
                r1 = r2.prototype;
                r1 = Object.create(r1, {constructor: {value: r2}});
                r17 = r1;
                r0 = originalConstruct(r2, [r16, r15]);
                r0 = r0 instanceof Object ? r0 : r1;
                throw r0;
            }
        };
        var _closure1_slot4 = r0;
        r0 = 0;
        r1 = r4[r0];
        r0 = undefined;
        r5 = r3.bind(r0)(r1);
        r1 = global;
        r9 = r1.Object;
        r8 = r9.defineProperty;
        r7 = {};
        r1 = true;
        r7['value'] = r1;
        r1 = '__esModule';
        r1 = r8.bind(r9)(r6, r1, r7);
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9441: for(var _fun9441_ip = 0; ; ) switch(_fun9441_ip) {
case 0:
                r6 = a0;
                r0 = a1;
                r3 = 'invalid';
                r11 = 1;
                r14 = 0;
                r1 = r0.inputs;
                r1 = r1.emotion_idx;
                r1 = r1.size;
                if(!(r11 === r1)) { _fun9441_ip = 1843; continue _fun9441 }
case 40:
                r1 = r0.inputs;
                r1 = r1.genre_idx;
                r1 = r1.size;
                if(!(r11 === r1)) { _fun9441_ip = 1787; continue _fun9441 }
case 65:
                r1 = r0.inputs;
                r1 = r1.features;
                r4 = r1.size;
                r2 = _closure1_slot3;
                r2 = r2.EMOFX_FEATURE_DEFAULT_SIZE;
                if(!(r4 === r2)) { _fun9441_ip = 1709; continue _fun9441 }
case 103:
                r2 = r0.inputs;
                r2 = r2.base_extra_flat;
                r4 = r2.size;
                r2 = _closure1_slot3;
                r2 = r2.EMOFX_BASE_EXTRA_FLAT_DEFAULT_SIZE;
                if(!(r4 === r2)) { _fun9441_ip = 1631; continue _fun9441 }
case 138:
                r2 = _closure1_slot3;
                r2 = r2.EMOFX_TENSOR_NAMES;
                r7 = r2.emotion_idx;
                r2 = r0.inputs;
                r2 = r2.emotion_idx;
                r5 = r2.dtype;
                r2 = _closure1_slot0;
                r4 = r2.default;
                r2 = r0.inputs;
                r2 = r2.emotion_idx;
                r2 = r2.shape;
                r15 = undefined;
                r4 = r4.bind(r15)(r2);
                r8 = r6.emotion_idx;
                r2 = new Array(1);
                r2[0] = r8;
                r10 = {};
                r10['name'] = r7;
                r10['dtype'] = r5;
                r10['shape'] = r4;
                r10['data'] = r2;
                r2 = _closure1_slot3;
                r2 = r2.EMOFX_TENSOR_NAMES;
                r7 = r2.genre_idx;
                r2 = r0.inputs;
                r2 = r2.genre_idx;
                r5 = r2.dtype;
                r2 = _closure1_slot0;
                r4 = r2.default;
                r2 = r0.inputs;
                r2 = r2.genre_idx;
                r2 = r2.shape;
                r4 = r4.bind(r15)(r2);
                r8 = r6.genre_idx;
                r2 = new Array(1);
                r2[0] = r8;
                r9 = {};
                r9['name'] = r7;
                r9['dtype'] = r5;
                r9['shape'] = r4;
                r9['data'] = r2;
                r2 = r6.mel_spectrogram_32;
                r5 = global;
                r7 = r5.Array;
                r4 = r7.isArray;
                r4 = r4.bind(r7)(r2);
                if(!r4) { _fun9441_ip = 1571; continue _fun9441 }
case 370:
                r7 = r2.length;
                r4 = 32;
                if(!(r4 === r7)) { _fun9441_ip = 1571; continue _fun9441 }
case 385:
                r7 = r2.some;
                r4 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r4
                    _fun9442: for(var _fun9442_ip = 0; ; ) switch(_fun9442_ip) {
case 0:
                        r3 = a0;
                        r1 = 'number';
                        r0 = typeof r3;
                        r0 = r1 !== r0;
                        if(r0) { _fun9442_ip = 39; continue _fun9442 }
case 17:
                        r1 = global;
                        r2 = r1.Number;
                        r1 = r2.isFinite;
                        r1 = r1.bind(r2)(r3);
                        r0 = !r1;
case 39:
                        return r0;
                    }
                };
                r4 = r7.bind(r2)(r4);
                if(r4) { _fun9441_ip = 1539; continue _fun9441 }
case 409:
                r4 = _closure1_slot0;
                r4 = r4.default;
                r4 = r4.bind(r15)(r2);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r13 = r7.assertFiniteNumber;
                r12 = r6.dynamic_range;
                r7 = 'dynamic_range';
                r7 = r13.bind(r15)(r12, r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r13 = r7.assertFiniteNumber;
                r12 = r6.loudness_lufs;
                r7 = 'loudness_lufs';
                r7 = r13.bind(r15)(r12, r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r13 = r7.assertFiniteNumber;
                r12 = r6.stereo_width_ratio;
                r7 = 'stereo_width_ratio';
                r7 = r13.bind(r15)(r12, r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r13 = r7.assertFiniteNumber;
                r12 = r6.repbpm;
                r7 = 'repbpm';
                r7 = r13.bind(r15)(r12, r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r13 = r7.assertFiniteNumber;
                r12 = r6.chorus_loudness;
                r7 = 'chorus_loudness';
                r7 = r13.bind(r15)(r12, r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r12 = r7.optionalFiniteNumber;
                r7 = r6.vocal_main_freq;
                r7 = r12.bind(r15)(r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r12 = r7.optionalFiniteNumber;
                r7 = r6.vocal_time_ratio;
                r7 = r12.bind(r15)(r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r12 = r7.optionalFiniteNumber;
                r7 = r6.harmonic_intensity_ratio;
                r7 = r12.bind(r15)(r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r12 = r7.optionalFiniteNumber;
                r7 = r6.vocal_dynamic_range;
                r7 = r12.bind(r15)(r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r12 = r7.optionalFiniteNumber;
                r7 = r6.drum_time_ratio;
                r7 = r12.bind(r15)(r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r12 = r7.optionalFiniteNumber;
                r7 = r6.rhythm_complexity;
                r7 = r12.bind(r15)(r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r12 = r7.optionalFiniteNumber;
                r7 = r6.vocal_energy_ratio;
                r7 = r12.bind(r15)(r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r12 = r7.optionalFiniteNumber;
                r7 = r6.drum_energy_ratio;
                r7 = r12.bind(r15)(r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r12 = r7.optionalFiniteNumber;
                r7 = r6.bass_energy_ratio;
                r7 = r12.bind(r15)(r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = _closure1_slot2;
                r12 = r7.optionalFiniteNumber;
                r7 = r6.other_instrument_energy_ratio;
                r7 = r12.bind(r15)(r7);
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = r6.module_switches;
                r12 = r7.reverb;
                r7 = 0;
                if(!r12) { _fun9441_ip = 938; continue _fun9441 }
case 935:
                r7 = r11;
case 938:
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = r6.module_switches;
                r12 = r7.delay;
                r7 = 0;
                if(!r12) { _fun9441_ip = 968; continue _fun9441 }
case 965:
                r7 = r11;
case 968:
                r7 = r8.bind(r4)(r7);
                r8 = r4.push;
                r7 = r6.module_switches;
                r12 = r7.stereo_enhance;
                r7 = 0;
                if(!r12) { _fun9441_ip = 998; continue _fun9441 }
case 995:
                r7 = r11;
case 998:
                r7 = r8.bind(r4)(r7);
                r11 = r4.push;
                r8 = r11.apply;
                r7 = _closure1_slot0;
                r12 = r7.default;
                r16 = r5.Array;
                r7 = r0.inputs;
                r7 = r7.features;
                r13 = r7.size;
                r7 = _closure1_slot3;
                r7 = r7.EMOFX_FEATURE_CORE_SIZE;
                r18 = r13 - r7;
                r13 = r16.prototype;
                r13 = Object.create(r13, {constructor: {value: r16}});
                r19 = r13;
                r7 = originalConstruct(r16, [r18, r17]);
                r13 = r7 instanceof Object ? r7 : r13;
                r7 = r13.fill;
                r7 = r7.bind(r13)(r14);
                r7 = r12.bind(r15)(r7);
                r7 = r8.bind(r11)(r4, r7);
                r8 = r4.length;
                r7 = r0.inputs;
                r7 = r7.features;
                r7 = r7.size;
                if(!(r8 === r7)) { _fun9441_ip = 1468; continue _fun9441 }
case 1132:
                r7 = _closure1_slot3;
                r7 = r7.EMOFX_TENSOR_NAMES;
                r12 = r7.features;
                r7 = r0.inputs;
                r7 = r7.features;
                r11 = r7.dtype;
                r7 = _closure1_slot0;
                r8 = r7.default;
                r7 = r0.inputs;
                r7 = r7.features;
                r7 = r7.shape;
                r7 = r8.bind(r15)(r7);
                r8 = {};
                r8['name'] = r12;
                r8['dtype'] = r11;
                r8['shape'] = r7;
                r8['data'] = r4;
                r7 = _closure1_slot3;
                r7 = r7.EMOFX_TENSOR_NAMES;
                r13 = r7.base_extra_flat;
                r7 = r0.inputs;
                r7 = r7.base_extra_flat;
                r12 = r7.dtype;
                r7 = _closure1_slot0;
                r11 = r7.default;
                r7 = r0.inputs;
                r7 = r7.base_extra_flat;
                r7 = r7.shape;
                r11 = r11.bind(r15)(r7);
                r14 = _closure1_slot4;
                r7 = r6.base_extra_aev2;
                r6 = r0.inputs;
                r6 = r6.base_extra_flat;
                r6 = r6.size;
                r7 = r14.bind(r15)(r7, r6);
                r6 = {};
                r6['name'] = r13;
                r6['dtype'] = r12;
                r6['shape'] = r11;
                r6['data'] = r7;
                r7 = r6.data;
                r11 = r7.length;
                r7 = r0.inputs;
                r7 = r7.base_extra_flat;
                r7 = r7.size;
                if(!(r11 === r7)) { _fun9441_ip = 1392; continue _fun9441 }
case 1370:
                r7 = new Array(4);
                r7[0] = r10;
                r7[1] = r9;
                r7[2] = r8;
                r7[3] = r6;
                return r7;
case 1392:
                r8 = r5.Error;
                r7 = r0.inputs;
                r7 = r7.base_extra_flat;
                r9 = r7.size;
                 r7 = 'Độ dài mã hóa của base_extra_flat bất thường, mong đợi ';
                r9 = r7 + r9;
                r6 = r6.data;
                r7 = r6.length;
                 r6 = ', thực tế ';
                r6 = r9 + r6;
                r18 = r6 + r7;
                r7 = r8.prototype;
                r7 = Object.create(r7, {constructor: {value: r8}});
                r19 = r7;
                r6 = originalConstruct(r8, [r18, r17]);
                r6 = r6 instanceof Object ? r6 : r7;
                throw r6;
case 1468:
                r7 = r5.Error;
                r6 = r0.inputs;
                r6 = r6.features;
                r8 = r6.size;
                 r6 = 'Độ dài mã hóa của features bất thường, mong đợi ';
                r8 = r6 + r8;
                r6 = r4.length;
                 r4 = ', thực tế ';
                r4 = r8 + r4;
                r18 = r4 + r6;
                r6 = r7.prototype;
                r6 = Object.create(r6, {constructor: {value: r7}});
                r19 = r6;
                r4 = originalConstruct(r7, [r18, r17]);
                r4 = r4 instanceof Object ? r4 : r6;
                throw r4;
case 1539:
                r7 = r5.Error;
                r4 = r7.prototype;
                r6 = Object.create(r4, {constructor: {value: r7}});
                 r18 = 'Tất cả phần tử trong mảng mel_spectrogram_32 phải là giá trị hữu hạn';
                r19 = r6;
                r4 = originalConstruct(r7, [r18, r17]);
                r4 = r4 instanceof Object ? r4 : r6;
                throw r4;
case 1571:
                r4 = r5.Error;
                r6 = r5.Array;
                r5 = r6.isArray;
                r5 = r5.bind(r6)(r2);
                if(!r5) { _fun9441_ip = 1601; continue _fun9441 }
case 1596:
                r3 = r2.length;
case 1601:
                 r2 = 'Độ dài của mảng mel_spectrogram_32 phải là 32, hiện tại là ';
                r18 = r2 + r3;
                r3 = r4.prototype;
                r3 = Object.create(r3, {constructor: {value: r4}});
                r19 = r3;
                r2 = originalConstruct(r4, [r18, r17]);
                r2 = r2 instanceof Object ? r2 : r3;
                throw r2;
case 1631:
                r2 = global;
                r4 = r2.Error;
                r2 = _closure1_slot3;
                r3 = r2.EMOFX_BASE_EXTRA_FLAT_DEFAULT_SIZE;
                 r2 = 'Kích thước của base_extra_flat phải là ';
                r5 = r2 + r3;
                r2 = r0.inputs;
                r2 = r2.base_extra_flat;
                r3 = r2.size;
                 r2 = ', hiện tại là ';
                r2 = r5 + r2;
                r18 = r2 + r3;
                r3 = r4.prototype;
                r3 = Object.create(r3, {constructor: {value: r4}});
                r19 = r3;
                r2 = originalConstruct(r4, [r18, r17]);
                r2 = r2 instanceof Object ? r2 : r3;
                throw r2;
case 1709:
                r2 = global;
                r3 = r2.Error;
                r1 = _closure1_slot3;
                r2 = r1.EMOFX_FEATURE_DEFAULT_SIZE;
                 r1 = 'Kích thước của features phải là ';
                r4 = r1 + r2;
                r1 = r0.inputs;
                r1 = r1.features;
                r2 = r1.size;
                 r1 = ', hiện tại là ';
                r1 = r4 + r1;
                r18 = r1 + r2;
                r2 = r3.prototype;
                r2 = Object.create(r2, {constructor: {value: r3}});
                r19 = r2;
                r1 = originalConstruct(r3, [r18, r17]);
                r1 = r1 instanceof Object ? r1 : r2;
                throw r1;
case 1787:
                r1 = global;
                r3 = r1.Error;
                r1 = r0.inputs;
                r1 = r1.genre_idx;
                r2 = r1.size;
                r1 = 'Kích thước tensor genre_idx phải là 1, hiện tại là ';
                r18 = r1 + r2;
                r2 = r3.prototype;
                r2 = Object.create(r2, {constructor: {value: r3}});
                r19 = r2;
                r1 = originalConstruct(r3, [r18, r17]);
                r1 = r1 instanceof Object ? r1 : r2;
                throw r1;
case 1843:
                r1 = global;
                r2 = r1.Error;
                r0 = r0.inputs;
                r0 = r0.emotion_idx;
                r1 = r0.size;
                r0 = 'Kích thước tensor emotion_idx phải là 1, hiện tại là ';
                r18 = r0 + r1;
                r1 = r2.prototype;
                r1 = Object.create(r1, {constructor: {value: r2}});
                r19 = r1;
                r0 = originalConstruct(r2, [r18, r17]);
                r0 = r0 instanceof Object ? r0 : r1;
                throw r0;
            }
        };
        r6['encodeEmoFX'] = r1;
        r1 = 1;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        r1 = r5.bind(r0)(r1);
        var _closure1_slot0 = r1;
        r1 = 2;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot1 = r1;
        r1 = 3;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot2 = r1;
        r1 = 4;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot3 = r1;
        return r0;
    };
factories[957]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r3 = a1;
        r6 = a5;
        r4 = a6;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: c, environment: r2
            _fun9450: for(var _fun9450_ip = 0; ; ) switch(_fun9450_ip) {
case 0:
                r1 = a0;
                r0 = {};
                r2 = r1.genre_id;
                r3 = null;
                if(!(r3 != r2)) { _fun9450_ip = 29; continue _fun9450 }
case 17:
                r2 = r1.genre_id;
                r0['genre_id'] = r2;
case 29:
                r2 = r1.repbpm;
                if(!(r3 != r2)) { _fun9450_ip = 51; continue _fun9450 }
case 39:
                r2 = r1.repbpm;
                r0['repbpm'] = r2;
case 51:
                r2 = r1.key_mode;
                if(!(r3 != r2)) { _fun9450_ip = 73; continue _fun9450 }
case 61:
                r2 = r1.key_mode;
                r0['key_mode'] = r2;
case 73:
                r2 = r1.loudness_lufs;
                if(!(r3 != r2)) { _fun9450_ip = 95; continue _fun9450 }
case 83:
                r2 = r1.loudness_lufs;
                r0['loudness_lufs'] = r2;
case 95:
                r2 = r1.dynamic_range;
                if(!(r3 != r2)) { _fun9450_ip = 117; continue _fun9450 }
case 105:
                r2 = r1.dynamic_range;
                r0['dynamic_range'] = r2;
case 117:
                r2 = r1.stereo_width_ratio;
                if(!(r3 != r2)) { _fun9450_ip = 139; continue _fun9450 }
case 127:
                r2 = r1.stereo_width_ratio;
                r0['stereo_width_ratio'] = r2;
case 139:
                r2 = r1.mel_spectrogram_32;
                if(!(r3 != r2)) { _fun9450_ip = 180; continue _fun9450 }
case 149:
                r2 = _closure1_slot0;
                r5 = r2.default;
                r4 = r1.mel_spectrogram_32;
                r2 = undefined;
                r2 = r5.bind(r2)(r4);
                r0['mel_spectrogram_32'] = r2;
case 180:
                r2 = r1.chorus_loudness;
                if(!(r3 != r2)) { _fun9450_ip = 202; continue _fun9450 }
case 190:
                r2 = r1.chorus_loudness;
                r0['chorus_loudness'] = r2;
case 202:
                r2 = r1.vocal_main_freq;
                if(!(r3 != r2)) { _fun9450_ip = 224; continue _fun9450 }
case 212:
                r2 = r1.vocal_main_freq;
                r0['vocal_main_freq'] = r2;
case 224:
                r2 = r1.vocal_time_ratio;
                if(!(r3 != r2)) { _fun9450_ip = 246; continue _fun9450 }
case 234:
                r2 = r1.vocal_time_ratio;
                r0['vocal_time_ratio'] = r2;
case 246:
                r2 = r1.harmonic_intensity_ratio;
                if(!(r3 != r2)) { _fun9450_ip = 268; continue _fun9450 }
case 256:
                r2 = r1.harmonic_intensity_ratio;
                r0['harmonic_intensity_ratio'] = r2;
case 268:
                r2 = r1.vocal_dynamic_range;
                if(!(r3 != r2)) { _fun9450_ip = 290; continue _fun9450 }
case 278:
                r2 = r1.vocal_dynamic_range;
                r0['vocal_dynamic_range'] = r2;
case 290:
                r2 = r1.drum_time_ratio;
                if(!(r3 != r2)) { _fun9450_ip = 312; continue _fun9450 }
case 300:
                r2 = r1.drum_time_ratio;
                r0['drum_time_ratio'] = r2;
case 312:
                r2 = r1.rhythm_complexity;
                if(!(r3 != r2)) { _fun9450_ip = 334; continue _fun9450 }
case 322:
                r2 = r1.rhythm_complexity;
                r0['rhythm_complexity'] = r2;
case 334:
                r2 = r1.vocal_energy_ratio;
                if(!(r3 != r2)) { _fun9450_ip = 356; continue _fun9450 }
case 344:
                r2 = r1.vocal_energy_ratio;
                r0['vocal_energy_ratio'] = r2;
case 356:
                r2 = r1.drum_energy_ratio;
                if(!(r3 != r2)) { _fun9450_ip = 378; continue _fun9450 }
case 366:
                r2 = r1.drum_energy_ratio;
                r0['drum_energy_ratio'] = r2;
case 378:
                r2 = r1.bass_energy_ratio;
                if(!(r3 != r2)) { _fun9450_ip = 400; continue _fun9450 }
case 388:
                r2 = r1.bass_energy_ratio;
                r0['bass_energy_ratio'] = r2;
case 400:
                r2 = r1.other_instrument_energy_ratio;
                if(!(r3 != r2)) { _fun9450_ip = 422; continue _fun9450 }
case 410:
                r1 = r1.other_instrument_energy_ratio;
                r0['other_instrument_energy_ratio'] = r1;
case 422:
                return r0;
            }
        };
        var _closure1_slot6 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: y, environment: r2
            _fun9451: for(var _fun9451_ip = 0; ; ) switch(_fun9451_ip) {
case 0:
                r3 = a0;
                r11 = a1;
                r0 = 'input_tag_mapping';
                r1 = 'input_direct';
                r4 = 'default';
                r5 = 'emotion_mapping';
                r2 = r3.emotion_label;
                r8 = null;
                if(!(r8 != r2)) { _fun9451_ip = 60; continue _fun9451 }
case 34:
                r6 = _closure1_slot3;
                r7 = r6.isEmotionLabel;
                r6 = undefined;
                r6 = r7.bind(r6)(r2);
                if(r6) { _fun9451_ip = 261; continue _fun9451 }
case 60:
                r6 = _closure1_slot6;
                r10 = undefined;
                r9 = r6.bind(r10)(r3);
                r6 = _closure1_slot1;
                r6 = r6.validateEmotionMappingFeatures;
                r6 = r6.bind(r10)(r9);
                r12 = r6.valid;
                if(r12) { _fun9451_ip = 188; continue _fun9451 }
case 98:
                r14 = r6.issues;
                r13 = r14.join;
                r12 = '; ';
                r13 = r13.bind(r14)(r12);
                r12 = 'emotion_mapping input invalid: ';
                r12 = r12 + r13;
                if(!(r8 != r11)) { _fun9451_ip = 159; continue _fun9451 }
case 130:
                r14 = r11.warn;
                r6 = r6.issues;
                r13 = {};
                r13['issues'] = r6;
                r6 = 'emotion:mapping:invalid';
                r6 = r14.bind(r11)(r6, r13);
case 159:
                r6 = _closure1_slot5;
                r13 = r6.DEFAULT_EMOTION;
                r6 = {};
                r6['emotionLabel'] = r13;
                r6['emotionSource'] = r4;
                r6['fallbackReason'] = r12;
                return r6;
case 188:
                r6 = _closure1_slot1;
                r6 = r6.predictEmotionLabel;
                r6 = r6.bind(r10)(r9);
                if(!(r8 != r11)) { _fun9451_ip = 230; continue _fun9451 }
case 207:
                r10 = r11.info;
                r9 = {};
                r9['emotionLabel'] = r6;
                r8 = 'emotion:mapping:resolved';
                r8 = r10.bind(r11)(r8, r9);
case 230:
                r7 = _closure1_slot5;
                r7 = r7.DEFAULT_EMOTION;
                if(!(r6 === r7)) { _fun9451_ip = 247; continue _fun9451 }
case 244:
                r5 = r4;
case 247:
                r4 = {};
                r4['emotionLabel'] = r6;
                r4['emotionSource'] = r5;
                return r4;
case 261:
                r3 = r3.emotionFromTagMapping;
                if(!r3) { _fun9451_ip = 273; continue _fun9451 }
case 270:
                r1 = r0;
case 273:
                r0 = {};
                r0['emotionLabel'] = r2;
                r0['emotionSource'] = r1;
                return r0;
            }
        };
        var _closure1_slot7 = r0;
        r0 = 0;
        r1 = r4[r0];
        r0 = undefined;
        r5 = r3.bind(r0)(r1);
        r1 = global;
        r9 = r1.Object;
        r8 = r9.defineProperty;
        r7 = {};
        r1 = true;
        r7['value'] = r1;
        r1 = '__esModule';
        r1 = r8.bind(r9)(r6, r1, r7);
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9452: for(var _fun9452_ip = 0; ; ) switch(_fun9452_ip) {
case 0:
                r2 = a0;
                r9 = a1;
                r0 = undefined;
                r30 = 'Thể loại không xác định';
                r8 = null;
                if(!(r8 != r9)) { _fun9452_ip = 70; continue _fun9452 }
case 18:
                r4 = r9.info;
                r5 = undefined;
                if(!(r8 != r2)) { _fun9452_ip = 36; continue _fun9452 }
case 30:
                r5 = r2.emotion_label;
case 36:
                r1 = undefined;
                if(!(r8 != r2)) { _fun9452_ip = 48; continue _fun9452 }
case 42:
                r1 = r2.genre_id;
case 48:
                r3 = {};
                r3['emotion'] = r5;
                r3['genreId'] = r1;
                r1 = 'start';
                r1 = r4.bind(r9)(r1, r3);
case 70:
                r1 = _closure1_slot2;
                r1 = r1.sanitizeInput;
                r1 = r1.bind(r0)(r2, r9);
                r6 = r1.sanitized;
                r7 = r1.issues;
                r5 = r1.availability;
                r31 = r6.genre_id;
                r4 = r8 != r31;
                if(r4) { _fun9452_ip = 132; continue _fun9452 }
case 120:
                r1 = _closure1_slot5;
                r2 = r1.DEFAULT_FAMILY;
                _fun9452_ip = 147; continue _fun9452;
case 132:
                r1 = _closure1_slot4;
                r1 = r1.getFamily;
                r2 = r1.bind(r0)(r31);
case 147:
                if(!r4) { _fun9452_ip = 165; continue _fun9452 }
case 150:
                r1 = _closure1_slot4;
                r1 = r1.getGenreName;
                r30 = r1.bind(r0)(r31);
case 165:
                r1 = _closure1_slot7;
                r3 = r1.bind(r0)(r6, r9);
                r1 = r3.emotionLabel;
                r12 = r3.emotionSource;
                r3 = r3.fallbackReason;
                r11 = _closure1_slot3;
                r11 = r11.EMOTION_INDEX;
                r11 = r11[r1];
                r14 = 'number';
                r13 = typeof r11;
                if(!(r14 === r13)) { _fun9452_ip = 604; continue _fun9452 }
case 221:
                r10 = _closure1_slot3;
                r10 = r10.GENRE_INDEX;
                r10 = r10[r2];
                r13 = typeof r10;
                if(!(r14 === r13)) { _fun9452_ip = 566; continue _fun9452 }
case 245:
                r29 = r6.mel_spectrogram_32;
                r28 = r6.dynamic_range;
                r27 = r6.loudness_lufs;
                r26 = r6.stereo_width_ratio;
                r25 = r6.repbpm;
                r24 = r6.key_mode;
                r23 = r6.chorus_loudness;
                r22 = r6.vocal_main_freq;
                r21 = r6.vocal_time_ratio;
                r20 = r6.harmonic_intensity_ratio;
                r19 = r6.vocal_dynamic_range;
                r18 = r6.drum_time_ratio;
                r17 = r6.rhythm_complexity;
                r16 = r6.vocal_energy_ratio;
                r15 = r6.drum_energy_ratio;
                r14 = r6.bass_energy_ratio;
                r13 = r6.other_instrument_energy_ratio;
                r6 = {};
                r6['emotion_label'] = r1;
                r6['emotion_source'] = r12;
                r6['genre_id'] = r31;
                r6['genre_name'] = r30;
                r6['family'] = r2;
                r6['emotion_idx'] = r11;
                r6['genre_idx'] = r10;
                r6['mel_spectrogram_32'] = r29;
                r6['dynamic_range'] = r28;
                r6['loudness_lufs'] = r27;
                r6['stereo_width_ratio'] = r26;
                r6['repbpm'] = r25;
                r6['key_mode'] = r24;
                r6['chorus_loudness'] = r23;
                r6['vocal_main_freq'] = r22;
                r6['vocal_time_ratio'] = r21;
                r6['harmonic_intensity_ratio'] = r20;
                r6['vocal_dynamic_range'] = r19;
                r6['drum_time_ratio'] = r18;
                r6['rhythm_complexity'] = r17;
                r6['vocal_energy_ratio'] = r16;
                r6['drum_energy_ratio'] = r15;
                r6['bass_energy_ratio'] = r14;
                r6['other_instrument_energy_ratio'] = r13;
                if(!(r8 != r9)) { _fun9452_ip = 542; continue _fun9452 }
case 473:
                r8 = r9.info;
                r14 = r7.length;
                r13 = 0;
                r0 = undefined;
                if(!(r14 > r13)) { _fun9452_ip = 495; continue _fun9452 }
case 492:
                r0 = r7;
case 495:
                r7 = {};
                r7['emotion'] = r1;
                r7['emotionSource'] = r12;
                r7['family'] = r2;
                r7['emotionIdx'] = r11;
                r7['genreIdx'] = r10;
                r7['issues'] = r0;
                r7['availability'] = r5;
                r0 = 'done';
                r0 = r8.bind(r9)(r0, r7);
case 542:
                r0 = {};
                r0['input'] = r6;
                r0['availability'] = r5;
                r0['familyKnown'] = r4;
                r0['fallbackReason'] = r3;
                return r0;
case 566:
                r0 = global;
                r3 = r0.Error;
                r0 = 'genre index missing for ';
                r34 = r0 + r2;
                r2 = r3.prototype;
                r2 = Object.create(r2, {constructor: {value: r3}});
                r35 = r2;
                r0 = originalConstruct(r3, [r34, r33]);
                r0 = r0 instanceof Object ? r0 : r2;
                throw r0;
case 604:
                r0 = global;
                r2 = r0.Error;
                r0 = 'emotion index missing for ';
                r34 = r0 + r1;
                r1 = r2.prototype;
                r1 = Object.create(r1, {constructor: {value: r2}});
                r35 = r1;
                r0 = originalConstruct(r2, [r34, r33]);
                r0 = r0 instanceof Object ? r0 : r1;
                throw r0;
            }
        };
        r6['preprocess'] = r1;
        r1 = 1;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        r1 = r5.bind(r0)(r1);
        var _closure1_slot0 = r1;
        r1 = 2;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot1 = r1;
        r1 = 3;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot2 = r1;
        r1 = 4;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot3 = r1;
        r1 = 5;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot4 = r1;
        r1 = 6;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot5 = r1;
        return r0;
    };
factories[958]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r5 = a1;
        r3 = a5;
        r6 = a6;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: q, environment: r2
            _fun9454: for(var _fun9454_ip = 0; ; ) switch(_fun9454_ip) {
case 0:
                r3 = a0;
                r1 = 'number';
                r0 = typeof r3;
                r0 = r1 === r0;
                if(!r0) { _fun9454_ip = 36; continue _fun9454 }
case 17:
                r1 = global;
                r2 = r1.Number;
                r1 = r2.isFinite;
                r0 = r1.bind(r2)(r3);
case 36:
                return r0;
            }
        };
        var _closure1_slot26 = r0;
        r0 = function(a0, a1, a2) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: G, environment: r2
            r0 = global;
            r2 = r0.Math;
            r1 = r2.exp;
            r5 = r0.Math;
            r4 = r5.pow;
            r3 = a0;
            r0 = a1;
            r3 = r3 - r0;
            r0 = a2;
            r3 = r3 / r0;
            r0 = 2;
            r3 = r4.bind(r5)(r3, r0);
            r0 = -0.5;
            r0 = r0 * r3;
            r0 = r1.bind(r2)(r0);
            return r0;
        };
        var _closure1_slot27 = r0;
        r0 = function(a0, a1, a2) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: B, environment: r2
            _fun9456: for(var _fun9456_ip = 0; ; ) switch(_fun9456_ip) {
case 0:
                r4 = a0;
                r0 = -70;
                r3 = r4.slice;
                r2 = a1;
                r1 = a2;
                r1 = r3.bind(r4)(r2, r1);
                r2 = r1.length;
                r4 = 0;
                if(!(r2 > r4)) { _fun9456_ip = 65; continue _fun9456 }
case 37:
                r3 = r1.reduce;
                r2 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
                    r1 = a0;
                    r0 = a1;
                    r0 = r1 + r0;
                    return r0;
                };
                r2 = r3.bind(r1)(r2, r4);
                r1 = r1.length;
                r0 = r2 / r1;
case 65:
                return r0;
            }
        };
        var _closure1_slot28 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: F, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot6;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot29 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: R, environment: r2
            _fun9459: for(var _fun9459_ip = 0; ; ) switch(_fun9459_ip) {
case 0:
                r1 = a1;
                r0 = r1.trim;
                r2 = r0.bind(r1)();
                r1 = r2.endsWith;
                r0 = 'Major';
                r1 = r1.bind(r2)(r0);
                r2 = _closure1_slot7;
                r0 = a0;
                r2 = r2[r0];
                r0 = r2;
                if(r1) { _fun9459_ip = 55; continue _fun9459 }
case 48:
                r1 = 1;
                r0 = r1 - r2;
case 55:
                return r0;
            }
        };
        var _closure1_slot30 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: T, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot8;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot31 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: W, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot9;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot32 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: Z, environment: r2
            _fun9462: for(var _fun9462_ip = 0; ; ) switch(_fun9462_ip) {
case 0:
                r5 = a0;
                r6 = a1;
                r0 = 1;
                r2 = 0.1;
                r4 = 0.4;
                r3 = _closure1_slot10;
                r8 = r3[r5];
                r3 = null;
                if(!(r3 != r8)) { _fun9462_ip = 59; continue _fun9462 }
case 46:
                r7 = r8.has;
                r7 = r7.bind(r8)(r6);
                if(r7) { _fun9462_ip = 96; continue _fun9462 }
case 59:
                r1 = _closure1_slot11;
                r5 = r1[r5];
                r1 = r4;
                if(!(r3 != r5)) { _fun9462_ip = 93; continue _fun9462 }
case 74:
                r3 = r5.has;
                r3 = r3.bind(r5)(r6);
                r1 = r4;
                if(!r3) { _fun9462_ip = 93; continue _fun9462 }
case 90:
                r1 = r2;
case 93:
                r0 = r1;
case 96:
                return r0;
            }
        };
        var _closure1_slot33 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: z, environment: r2
            r11 = a0;
            r4 = a1;
            r2 = _closure1_slot28;
            r5 = undefined;
            r3 = 24;
            r1 = 32;
            r7 = r2.bind(r5)(r4, r3, r1);
            r3 = 0;
            r1 = 8;
            r4 = r2.bind(r5)(r4, r3, r1);
            r1 = _closure1_slot0;
            r2 = r1.default;
            r1 = _closure1_slot12;
            r1 = r1[r11];
            r10 = 2;
            r1 = r2.bind(r5)(r1, r10);
            r6 = r1[r3];
            r8 = 1;
            r1 = r1[r8];
            r2 = _closure1_slot0;
            r9 = r2.default;
            r2 = _closure1_slot13;
            r2 = r2[r11];
            r2 = r9.bind(r5)(r2, r10);
            r3 = r2[r3];
            r2 = r2[r8];
            r0 = _closure1_slot27;
            r6 = r0.bind(r5)(r7, r6, r1);
            r1 = 0.75;
            r1 = r1 * r6;
            r2 = r0.bind(r5)(r4, r3, r2);
            r0 = 0.25;
            r0 = r0 * r2;
            r0 = r1 + r0;
            return r0;
        };
        var _closure1_slot34 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: C, environment: r2
            r4 = _closure1_slot27;
            r1 = _closure1_slot14;
            r0 = a0;
            r3 = r1[r0];
            r2 = undefined;
            r1 = a1;
            r0 = 0.08;
            r0 = r4.bind(r2)(r1, r3, r0);
            return r0;
        };
        var _closure1_slot35 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: D, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot15;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot36 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: H, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot16;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot37 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: J, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot17;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot38 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: K, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot18;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot39 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: Q, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot19;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot40 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: U, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot20;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot41 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: V, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot21;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot42 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: X, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot22;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot43 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: Y, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot23;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot44 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: $, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot24;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot45 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: ee, environment: r2
            r1 = _closure1_slot0;
            r3 = r1.default;
            r2 = _closure1_slot25;
            r1 = a0;
            r2 = r2[r1];
            r4 = undefined;
            r1 = 2;
            r1 = r3.bind(r4)(r2, r1);
            r3 = _closure1_slot27;
            r0 = 0;
            r2 = r1[r0];
            r0 = 1;
            r1 = r1[r0];
            r0 = a1;
            r0 = r3.bind(r4)(r0, r2, r1);
            return r0;
        };
        var _closure1_slot46 = r0;
        r0 = function(a0, a1, a2, a3, a4) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: te, environment: r2
            _fun9476: for(var _fun9476_ip = 0; ; ) switch(_fun9476_ip) {
case 0:
                r6 = a0;
                r4 = a1;
                r2 = a2;
                r3 = a3;
                r5 = a4;
                r0 = null;
                if(!(r0 != r6)) { _fun9476_ip = 157; continue _fun9476 }
case 24:
                r8 = _closure1_slot26;
                r7 = undefined;
                r7 = r8.bind(r7)(r6);
                if(r7) { _fun9476_ip = 61; continue _fun9476 }
case 41:
                r8 = r2.push;
                r7 = ' phải là giá trị hữu hạn';
                r7 = r4 + r7;
                r7 = r8.bind(r2)(r7);
                _fun9476_ip = 157; continue _fun9476;
case 61:
                if(!(r0 != r3)) { _fun9476_ip = 83; continue _fun9476 }
case 65:
                r7 = _closure1_slot1;
                r7 = r7.RANGE_EPSILON;
                r7 = r3 - r7;
                if(!(!(r6 < r7))) { _fun9476_ip = 135; continue _fun9476 }
case 83:
                r0 = r0 != r5;
                if(!r0) { _fun9476_ip = 108; continue _fun9476 }
case 90:
                r1 = _closure1_slot1;
                r1 = r1.RANGE_EPSILON;
                r1 = r5 + r1;
                r0 = r6 > r1;
case 108:
                if(!r0) { _fun9476_ip = 157; continue _fun9476 }
case 111:
                r1 = r2.push;
                r0 = ' phải <= ';
                r0 = r4 + r0;
                r0 = r0 + r5;
                r0 = r1.bind(r2)(r0);
                _fun9476_ip = 157; continue _fun9476;
case 135:
                r1 = r2.push;
                r0 = ' phải >= ';
                r0 = r4 + r0;
                r0 = r0 + r3;
                r0 = r1.bind(r2)(r0);
case 157:
                r0 = undefined;
                return r0;
            }
        };
        var _closure1_slot47 = r0;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: ne, environment: r2
            _fun9477: for(var _fun9477_ip = 0; ; ) switch(_fun9477_ip) {
case 0:
                r5 = a0;
                r4 = undefined;
                r1 = null;
                r6 = _closure1_slot4;
                r3 = r6;
                var __iterator_values_7=Array.from(r3), __iterator_index_7=0; r2={return:function(){}}; r3=function(){if(__iterator_index_7>=__iterator_values_7.length){r2=undefined;return {value:undefined}}return {value:__iterator_values_7[__iterator_index_7++]}};
case 20:
                r6 = r3().value;
                r7 = r2;
                if(!(r7 !== r4)) { _fun9477_ip = 53; continue _fun9477 }
case 31: // try_start_0
                r6 = r5[r6];
                if(!(r1 == r6)) { _fun9477_ip = 41; continue _fun9477 }
case 39: // try_end0
                _fun9477_ip = 20; continue _fun9477;
case 41:
                r2.return();
                return r1;
case 46: // catch_target0
                CatchBlockStart(arg_register=1);
                r2.return();
                throw r1;
case 53:
                r0 = _closure1_slot5;
                return r0;
            }
        };
        var _closure1_slot48 = r0;
        r0 = 0;
        r1 = r6[r0];
        r0 = undefined;
        r7 = r5.bind(r0)(r1);
        r4 = global;
        r10 = r4.Object;
        r9 = r10.defineProperty;
        r8 = {};
        r1 = true;
        r8['value'] = r1;
        r1 = '__esModule';
        r1 = r9.bind(r10)(r3, r1, r8);
        r3['EMOTION_MAPPING_LABELS'] = r0;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9478: for(var _fun9478_ip = 0; ; ) switch(_fun9478_ip) {
case 0:
                r1 = a0;
                r13 = null;
                r12 = undefined;
                r7 = undefined;
                r4 = undefined;
                r8 = undefined;
                r9 = undefined;
                r10 = undefined;
                r0 = _closure1_slot48;
                r0 = r0.bind(r12)(r1);
                if(!(r13 == r0)) { _fun9478_ip = 1319; continue _fun9478 }
case 38:
                r5 = r1.mel_spectrogram_32;
                if(!(r13 != r5)) { _fun9478_ip = 69; continue _fun9478 }
case 48:
                r5 = r1.mel_spectrogram_32;
                r6 = r5.length;
                r5 = 32;
                if(!(r5 === r6)) { _fun9478_ip = 1270; continue _fun9478 }
case 69:
                r6 = r1.rhythm_complexity;
                r5 = null;
                if(!(r5 != r6)) { _fun9478_ip = 84; continue _fun9478 }
case 81:
                r5 = r6;
case 84:
                r7 = r5;
                r5 = r13 != r5;
                if(!r5) { _fun9478_ip = 104; continue _fun9478 }
case 94:
                r14 = r7;
                r6 = 1;
                r5 = r14 > r6;
case 104:
                if(!r5) { _fun9478_ip = 117; continue _fun9478 }
case 107:
                r6 = r7;
                r5 = 10;
                r7 = r6 / r5;
case 117:
                r4 = {};
                r14 = _closure1_slot2;
                r5 = r14;
                var __iterator_values_8=Array.from(r5), __iterator_index_8=0; r6={return:function(){}}; r5=function(){if(__iterator_index_8>=__iterator_values_8.length){r6=undefined;return {value:undefined}}return {value:__iterator_values_8[__iterator_index_8++]}};
case 129:
                r14 = r5().value;
                r15 = r6;
                if(!(r15 !== r12)) { _fun9478_ip = 1244; continue _fun9478 }
case 143: // try_start_0
                r8 = r14;
                r9 = 0;
                r10 = 0;
                r14 = r1.mel_spectrogram_32;
                if(!(r13 != r14)) { _fun9478_ip = 207; continue _fun9478 }
case 160:
                r14 = _closure1_slot3;
                r15 = r14.mel_spectrogram_32;
                r16 = r9;
                r18 = _closure1_slot34;
                r17 = r8;
                r14 = r1.mel_spectrogram_32;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 207:
                r14 = r1.repbpm;
                if(!(r13 != r14)) { _fun9478_ip = 264; continue _fun9478 }
case 217:
                r14 = _closure1_slot3;
                r15 = r14.repbpm;
                r16 = r9;
                r18 = _closure1_slot29;
                r17 = r8;
                r14 = r1.repbpm;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 264:
                r14 = r1.key_mode;
                if(!(r13 != r14)) { _fun9478_ip = 340; continue _fun9478 }
case 274:
                r15 = r1.key_mode;
                r14 = r15.trim;
                r14 = r14.bind(r15)();
                if(!r14) { _fun9478_ip = 340; continue _fun9478 }
case 293:
                r14 = _closure1_slot3;
                r15 = r14.key_mode;
                r16 = r9;
                r18 = _closure1_slot30;
                r17 = r8;
                r14 = r1.key_mode;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 340:
                r14 = r1.loudness_lufs;
                if(!(r13 != r14)) { _fun9478_ip = 397; continue _fun9478 }
case 350:
                r14 = _closure1_slot3;
                r15 = r14.loudness_lufs;
                r16 = r9;
                r18 = _closure1_slot31;
                r17 = r8;
                r14 = r1.loudness_lufs;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 397:
                r14 = r1.chorus_loudness;
                if(!(r13 != r14)) { _fun9478_ip = 454; continue _fun9478 }
case 407:
                r14 = _closure1_slot3;
                r15 = r14.chorus_loudness;
                r16 = r9;
                r18 = _closure1_slot36;
                r17 = r8;
                r14 = r1.chorus_loudness;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 454:
                r14 = r1.dynamic_range;
                if(!(r13 != r14)) { _fun9478_ip = 511; continue _fun9478 }
case 464:
                r14 = _closure1_slot3;
                r15 = r14.dynamic_range;
                r16 = r9;
                r18 = _closure1_slot32;
                r17 = r8;
                r14 = r1.dynamic_range;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 511:
                r14 = r1.genre_id;
                if(!(r13 != r14)) { _fun9478_ip = 568; continue _fun9478 }
case 521:
                r14 = _closure1_slot3;
                r15 = r14.genre_id;
                r16 = r9;
                r18 = _closure1_slot33;
                r17 = r8;
                r14 = r1.genre_id;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 568:
                r14 = r1.stereo_width_ratio;
                if(!(r13 != r14)) { _fun9478_ip = 625; continue _fun9478 }
case 578:
                r14 = _closure1_slot3;
                r15 = r14.stereo_width_ratio;
                r16 = r9;
                r18 = _closure1_slot35;
                r17 = r8;
                r14 = r1.stereo_width_ratio;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 625:
                r14 = r7;
                if(!(r13 != r14)) { _fun9478_ip = 676; continue _fun9478 }
case 632:
                r14 = _closure1_slot3;
                r15 = r14.rhythm_complexity;
                r16 = r9;
                r18 = _closure1_slot37;
                r17 = r8;
                r14 = r7;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 676:
                r14 = r1.drum_time_ratio;
                if(!(r13 != r14)) { _fun9478_ip = 733; continue _fun9478 }
case 686:
                r14 = _closure1_slot3;
                r15 = r14.drum_time_ratio;
                r16 = r9;
                r18 = _closure1_slot38;
                r17 = r8;
                r14 = r1.drum_time_ratio;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 733:
                r14 = r1.vocal_time_ratio;
                if(!(r13 != r14)) { _fun9478_ip = 790; continue _fun9478 }
case 743:
                r14 = _closure1_slot3;
                r15 = r14.vocal_time_ratio;
                r16 = r9;
                r18 = _closure1_slot39;
                r17 = r8;
                r14 = r1.vocal_time_ratio;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 790:
                r14 = r1.harmonic_intensity_ratio;
                if(!(r13 != r14)) { _fun9478_ip = 847; continue _fun9478 }
case 800:
                r14 = _closure1_slot3;
                r15 = r14.harmonic_intensity_ratio;
                r16 = r9;
                r18 = _closure1_slot40;
                r17 = r8;
                r14 = r1.harmonic_intensity_ratio;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 847:
                r14 = r1.drum_energy_ratio;
                if(!(r13 != r14)) { _fun9478_ip = 904; continue _fun9478 }
case 857:
                r14 = _closure1_slot3;
                r15 = r14.drum_energy_ratio;
                r16 = r9;
                r18 = _closure1_slot41;
                r17 = r8;
                r14 = r1.drum_energy_ratio;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 904:
                r14 = r1.vocal_dynamic_range;
                if(!(r13 != r14)) { _fun9478_ip = 961; continue _fun9478 }
case 914:
                r14 = _closure1_slot3;
                r15 = r14.vocal_dynamic_range;
                r16 = r9;
                r18 = _closure1_slot42;
                r17 = r8;
                r14 = r1.vocal_dynamic_range;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 961:
                r14 = r1.vocal_energy_ratio;
                if(!(r13 != r14)) { _fun9478_ip = 1018; continue _fun9478 }
case 971:
                r14 = _closure1_slot3;
                r15 = r14.vocal_energy_ratio;
                r16 = r9;
                r18 = _closure1_slot43;
                r17 = r8;
                r14 = r1.vocal_energy_ratio;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 1018:
                r14 = r1.vocal_main_freq;
                if(!(r13 != r14)) { _fun9478_ip = 1075; continue _fun9478 }
case 1028:
                r14 = _closure1_slot3;
                r15 = r14.vocal_main_freq;
                r16 = r9;
                r18 = _closure1_slot44;
                r17 = r8;
                r14 = r1.vocal_main_freq;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 1075:
                r14 = r1.bass_energy_ratio;
                if(!(r13 != r14)) { _fun9478_ip = 1132; continue _fun9478 }
case 1085:
                r14 = _closure1_slot3;
                r15 = r14.bass_energy_ratio;
                r16 = r9;
                r18 = _closure1_slot45;
                r17 = r8;
                r14 = r1.bass_energy_ratio;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 1132:
                r14 = r1.other_instrument_energy_ratio;
                if(!(r13 != r14)) { _fun9478_ip = 1189; continue _fun9478 }
case 1142:
                r14 = _closure1_slot3;
                r15 = r14.other_instrument_energy_ratio;
                r16 = r9;
                r18 = _closure1_slot46;
                r17 = r8;
                r14 = r1.other_instrument_energy_ratio;
                r14 = r18.bind(r12)(r17, r14);
                r14 = r15 * r14;
                r9 = r16 + r14;
                r14 = r10;
                r10 = r14 + r15;
case 1189:
                r16 = r4;
                r15 = r8;
                r14 = _closure1_slot1;
                r17 = r14.floatIsZero;
                r14 = r10;
                r17 = r17.bind(r12)(r14);
                r14 = 0;
                if(r17) { _fun9478_ip = 1228; continue _fun9478 }
case 1218:
                r18 = r9;
                r17 = r10;
                r14 = r18 / r17;
case 1228:
                r16[r15] = r14;
case 1232: // try_end0
                _fun9478_ip = 129; continue _fun9478;
case 1237: // catch_target0
                CatchBlockStart(arg_register=5);
                r6.return();
                throw r5;
case 1244:
                var _closure2_slot0 = r4;
                r4 = _closure1_slot2;
                r3 = r4.reduce;
                r2 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
                    _fun9479: for(var _fun9479_ip = 0; ; ) switch(_fun9479_ip) {
case 0:
                        r0 = a0;
                        r1 = a1;
                        r2 = _closure2_slot0;
                        r3 = r2[r1];
                        r2 = r2[r0];
                        if(!(r3 > r2)) { _fun9479_ip = 28; continue _fun9479 }
case 25:
                        r0 = r1;
case 28:
                        return r0;
                    }
                };
                r2 = r3.bind(r4)(r2);
                return r2;
case 1270:
                r2 = global;
                r3 = r2.Error;
                r1 = r1.mel_spectrogram_32;
                r2 = r1.length;
                r1 = 'Độ dài mảng mel_spectrogram_32 phải là 32, hiện tại là ';
                r20 = r1 + r2;
                r2 = r3.prototype;
                r2 = Object.create(r2, {constructor: {value: r3}});
                r21 = r2;
                r1 = originalConstruct(r3, [r20, r19]);
                r1 = r1 instanceof Object ? r1 : r2;
                throw r1;
case 1319:
                return r0;
            }
        };
        r3['predictEmotionLabel'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9480: for(var _fun9480_ip = 0; ; ) switch(_fun9480_ip) {
case 0:
                r0 = a0;
                r1 = new Array(0);
                r7 = _closure1_slot47;
                r3 = r0.genre_id;
                r6 = undefined;
                r2 = 'genre_id';
                r2 = r7.bind(r6)(r3, r2, r1);
                r3 = r0.repbpm;
                r2 = 'repbpm';
                r2 = r7.bind(r6)(r3, r2, r1);
                r3 = r0.loudness_lufs;
                r2 = 'loudness_lufs';
                r2 = r7.bind(r6)(r3, r2, r1);
                r3 = r0.dynamic_range;
                r2 = 'dynamic_range';
                r2 = r7.bind(r6)(r3, r2, r1);
                r13 = r0.stereo_width_ratio;
                r12 = 'stereo_width_ratio';
                r3 = 0;
                r5 = 1;
                r14 = undefined;
                r11 = r1;
                r10 = 0;
                r9 = r5;
                r2 = r7.call(r14, r13, r12, r11, r10, r9, r8);
                r4 = r0.chorus_loudness;
                r2 = 'chorus_loudness';
                r2 = r7.bind(r6)(r4, r2, r1);
                r13 = r0.rhythm_complexity;
                r12 = 'rhythm_complexity';
                r9 = 10;
                r14 = undefined;
                r11 = r1;
                r2 = r7.call(r14, r13, r12, r11, r10, r9, r8);
                r13 = r0.drum_time_ratio;
                r12 = 'drum_time_ratio';
                r11 = r1;
                r9 = r5;
                r2 = r7.call(r14, r13, r12, r11, r10, r9, r8);
                r13 = r0.vocal_time_ratio;
                r12 = 'vocal_time_ratio';
                r11 = r1;
                r2 = r7.call(r14, r13, r12, r11, r10, r9, r8);
                r13 = r0.harmonic_intensity_ratio;
                r12 = 'harmonic_intensity_ratio';
                r11 = r1;
                r2 = r7.call(r14, r13, r12, r11, r10, r9, r8);
                r13 = r0.drum_energy_ratio;
                r12 = 'drum_energy_ratio';
                r11 = r1;
                r2 = r7.call(r14, r13, r12, r11, r10, r9, r8);
                r13 = r0.vocal_dynamic_range;
                r12 = 'vocal_dynamic_range';
                r9 = 30;
                r11 = r1;
                r2 = r7.call(r14, r13, r12, r11, r10, r9, r8);
                r13 = r0.vocal_energy_ratio;
                r12 = 'vocal_energy_ratio';
                r11 = r1;
                r9 = r5;
                r2 = r7.call(r14, r13, r12, r11, r10, r9, r8);
                r13 = r0.vocal_main_freq;
                r12 = 'vocal_main_freq';
                r9 = 1200;
                r11 = r1;
                r2 = r7.call(r14, r13, r12, r11, r10, r9, r8);
                r13 = r0.bass_energy_ratio;
                r12 = 'bass_energy_ratio';
                r11 = r1;
                r9 = r5;
                r2 = r7.call(r14, r13, r12, r11, r10, r9, r8);
                r13 = r0.other_instrument_energy_ratio;
                r12 = 'other_instrument_energy_ratio';
                r11 = r1;
                r2 = r7.call(r14, r13, r12, r11, r10, r9, r8);
                r2 = r0.key_mode;
                r4 = null;
                r2 = r4 == r2;
                if(r2) { _fun9480_ip = 386; continue _fun9480 }
case 338:
                r5 = r0.key_mode;
                r6 = 'string';
                r5 = typeof r5;
                r5 = r6 === r5;
                if(!r5) { _fun9480_ip = 383; continue _fun9480 }
case 358:
                r7 = r0.key_mode;
                r6 = r7.trim;
                r6 = r6.bind(r7)();
                r6 = r6.length;
                r5 = r3 !== r6;
case 383:
                r2 = r5;
case 386:
                if(r2) { _fun9480_ip = 403; continue _fun9480 }
case 389:
                r5 = r1.push;
                r2 = 'key_mode phải là chuỗi không rỗng';
                r2 = r5.bind(r1)(r2);
case 403:
                r2 = r0.mel_spectrogram_32;
                if(!(r4 != r2)) { _fun9480_ip = 549; continue _fun9480 }
case 416:
                r2 = global;
                r5 = r2.Array;
                r4 = r5.isArray;
                r2 = r0.mel_spectrogram_32;
                r2 = r4.bind(r5)(r2);
                if(r2) { _fun9480_ip = 459; continue _fun9480 }
case 443:
                r4 = r1.push;
                r2 = 'mel_spectrogram_32 phải là mảng';
                r2 = r4.bind(r1)(r2);
                _fun9480_ip = 549; continue _fun9480;
case 459:
                r2 = r0.mel_spectrogram_32;
                r4 = r2.length;
                r2 = 32;
                if(!(r2 === r4)) { _fun9480_ip = 520; continue _fun9480 }
case 477:
                r5 = r0.mel_spectrogram_32;
                r4 = r5.some;
                r2 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
                    r2 = _closure1_slot26;
                    r1 = undefined;
                    r0 = a0;
                    r0 = r2.bind(r1)(r0);
                    r0 = !r0;
                    return r0;
                };
                r2 = r4.bind(r5)(r2);
                if(!r2) { _fun9480_ip = 549; continue _fun9480 }
case 504:
                r4 = r1.push;
                r2 = 'Tất cả phần tử trong mảng mel_spectrogram_32 phải là giá trị hữu hạn';
                r2 = r4.bind(r1)(r2);
                _fun9480_ip = 549; continue _fun9480;
case 520:
                r2 = r1.push;
                r0 = r0.mel_spectrogram_32;
                r4 = r0.length;
                r0 = 'Độ dài mảng mel_spectrogram_32 phải là 32, hiện tại là ';
                r0 = r0 + r4;
                r0 = r2.bind(r1)(r0);
case 549:
                r2 = r1.length;
                r0 = {};
                r2 = r3 === r2;
                r0['valid'] = r2;
                r0['issues'] = r1;
                return r0;
            }
        };
        r3['validateEmotionMappingFeatures'] = r1;
        r1 = 1;
        r1 = r6[r1];
        r1 = r5.bind(r0)(r1);
        r1 = r7.bind(r0)(r1);
        var _closure1_slot0 = r1;
        r1 = 2;
        r1 = r6[r1];
        r1 = r5.bind(r0)(r1);
        var _closure1_slot1 = r1;
        r1 = ['ecstatic', 'passionate', 'energetic', 'joyful', 'sweet', 'confident', 'romantic', 'relaxed', 'healing', 'calm', 'nostalgic', 'lonely', 'sad', 'bitter', 'depressed'];
        r3['EMOTION_MAPPING_LABELS'] = r1;
        var _closure1_slot2 = r1;
        r1 = {'mel_spectrogram_32': 2.7, 'repbpm': 2.5, 'key_mode': 2, 'loudness_lufs': 1.5, 'chorus_loudness': 1.5, 'dynamic_range': 1, 'genre_id': 0.5, 'stereo_width_ratio': 0.2, 'rhythm_complexity': 2, 'drum_time_ratio': 1.5, 'vocal_time_ratio': 1.5, 'drum_energy_ratio': 2, 'harmonic_intensity_ratio': 1, 'vocal_dynamic_range': 1, 'vocal_energy_ratio': 1, 'vocal_main_freq': 0.5, 'bass_energy_ratio': 0.5, 'other_instrument_energy_ratio': 0.5};
        var _closure1_slot3 = r1;
        r1 = ['mel_spectrogram_32', 'repbpm'];
        var _closure1_slot4 = r1;
        r1 = 'default';
        var _closure1_slot5 = r1;
        r1 = {};
        r3 = [120, 28.17];
        r1['ecstatic'] = r3;
        r3 = [105, 32.62];
        r1['passionate'] = r3;
        r3 = [105, 31.13];
        r1['energetic'] = r3;
        r3 = [105, 29.65];
        r1['joyful'] = r3;
        r3 = [106, 31.13];
        r1['sweet'] = r3;
        r3 = [94, 31.13];
        r1['confident'] = r3;
        r3 = [86, 26.69];
        r1['romantic'] = r3;
        r3 = [89, 28.17];
        r1['relaxed'] = r3;
        r3 = [84, 25.2];
        r1['healing'] = r3;
        r3 = [84, 28.17];
        r1['calm'] = r3;
        r3 = [82, 25.2];
        r1['nostalgic'] = r3;
        r3 = [80, 23.72];
        r1['lonely'] = r3;
        r3 = [75, 17.79];
        r1['sad'] = r3;
        r3 = [76, 19.27];
        r1['bitter'] = r3;
        r3 = [84, 25.2];
        r1['depressed'] = r3;
        var _closure1_slot6 = r1;
        r1 = {'sweet': 0.88, 'romantic': 0.92, 'healing': 0.98, 'calm': 0.92, 'nostalgic': 0.96, 'sad': 0.97, 'bitter': 0.96, 'depressed': 0.93, 'lonely': 0.9, 'relaxed': 0.85, 'joyful': 0.78, 'confident': 0.72, 'passionate': 0.64, 'energetic': 0.67, 'ecstatic': 0.57};
        var _closure1_slot7 = r1;
        r22 = new Array(2);
        r1 = -8.21;
        r22[0] = r1;
        r1 = 1.9;
        r22[1] = r1;
        r21 = new Array(2);
        r1 = -8.64;
        r21[0] = r1;
        r1 = 2.55;
        r21[1] = r1;
        r20 = new Array(2);
        r1 = -8.87;
        r20[0] = r1;
        r1 = 2.34;
        r20[1] = r1;
        r19 = new Array(2);
        r1 = -9.45;
        r19[0] = r1;
        r1 = 2.7;
        r19[1] = r1;
        r18 = new Array(2);
        r1 = -9.75;
        r18[0] = r1;
        r5 = 3.05;
        r18[1] = r5;
        r17 = new Array(2);
        r1 = -9.25;
        r17[0] = r1;
        r1 = 2.05;
        r17[1] = r1;
        r16 = new Array(2);
        r3 = -10.51;
        r16[0] = r3;
        r7 = 2.65;
        r16[1] = r7;
        r15 = new Array(2);
        r1 = -11.02;
        r15[0] = r1;
        r6 = 3.4;
        r15[1] = r6;
        r14 = new Array(2);
        r1 = -10.68;
        r14[0] = r1;
        r1 = 2.94;
        r14[1] = r1;
        r13 = new Array(2);
        r8 = -13.23;
        r13[0] = r8;
        r8 = 4.31;
        r13[1] = r8;
        r12 = new Array(2);
        r8 = -10.4;
        r12[0] = r8;
        r8 = 2.49;
        r12[1] = r8;
        r11 = new Array(2);
        r8 = -11.53;
        r11[0] = r8;
        r8 = 3.2;
        r11[1] = r8;
        r10 = new Array(2);
        r8 = -10.66;
        r10[0] = r8;
        r8 = 2.36;
        r10[1] = r8;
        r9 = new Array(2);
        r8 = -10.86;
        r9[0] = r8;
        r8 = 2.56;
        r9[1] = r8;
        r8 = new Array(2);
        r23 = -10.65;
        r8[0] = r23;
        r8[1] = r7;
        r7 = {};
        r7['ecstatic'] = r22;
        r7['passionate'] = r21;
        r7['energetic'] = r20;
        r7['joyful'] = r19;
        r7['sweet'] = r18;
        r7['confident'] = r17;
        r7['romantic'] = r16;
        r7['relaxed'] = r15;
        r7['healing'] = r14;
        r7['calm'] = r13;
        r7['nostalgic'] = r12;
        r7['lonely'] = r11;
        r7['sad'] = r10;
        r7['bitter'] = r9;
        r7['depressed'] = r8;
        var _closure1_slot8 = r7;
        r7 = {};
        r8 = [4.5, 2.37];
        r7['ecstatic'] = r8;
        r8 = [5.4, 3.26];
        r7['passionate'] = r8;
        r8 = [5.1, 2.97];
        r7['energetic'] = r8;
        r8 = [5.1, 2.82];
        r7['joyful'] = r8;
        r8 = [5.2, 2.82];
        r7['sweet'] = r8;
        r8 = [6.2, 3.56];
        r7['confident'] = r8;
        r8 = [7.4, 4];
        r7['romantic'] = r8;
        r8 = [6.7, 3.56];
        r7['relaxed'] = r8;
        r8 = [8, 4.15];
        r7['healing'] = r8;
        r8 = [8, 4];
        r7['calm'] = r8;
        r8 = [8.1, 4.15];
        r7['nostalgic'] = r8;
        r8 = [8.7, 4.3];
        r7['lonely'] = r8;
        r8 = [9.1, 4.15];
        r7['sad'] = r8;
        r8 = [9.8, 4.15];
        r7['bitter'] = r8;
        r8 = [8.2, 4.15];
        r7['depressed'] = r8;
        var _closure1_slot9 = r7;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1015, 1017, 1008];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r22 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1008, 1017, 1015];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r21 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1017, 1015, 1008];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r20 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1017, 1015];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r19 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1017];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r18 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1017, 1015];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r17 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1028];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r16 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1028, 1003];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r15 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1003, 1006, 1007];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r14 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1003, 1006, 1007];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r13 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1010, 1003];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r12 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1003, 1010];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r11 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1003, 1010];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r10 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1010, 1003];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r9 = r7 instanceof Object ? r7 : r8;
        r23 = r4.Set;
        r7 = r23.prototype;
        r8 = Object.create(r7, {constructor: {value: r23}});
        r26 = [1010, 1003];
        r27 = r8;
        r7 = originalConstruct(r23, [r26, r25]);
        r8 = r7 instanceof Object ? r7 : r8;
        r7 = {};
        r7['ecstatic'] = r22;
        r7['passionate'] = r21;
        r7['energetic'] = r20;
        r7['joyful'] = r19;
        r7['sweet'] = r18;
        r7['confident'] = r17;
        r7['romantic'] = r16;
        r7['relaxed'] = r15;
        r7['healing'] = r14;
        r7['calm'] = r13;
        r7['nostalgic'] = r12;
        r7['lonely'] = r11;
        r7['sad'] = r10;
        r7['bitter'] = r9;
        r7['depressed'] = r8;
        var _closure1_slot10 = r7;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1006, 1007, 1003];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r11 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1006, 1007];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r10 = r7 instanceof Object ? r7 : r8;
        r9 = r4.Set;
        r7 = r9.prototype;
        r8 = Object.create(r7, {constructor: {value: r9}});
        r26 = [1017, 1015, 1008];
        r27 = r8;
        r7 = originalConstruct(r9, [r26, r25]);
        r9 = r7 instanceof Object ? r7 : r8;
        r12 = r4.Set;
        r7 = r12.prototype;
        r8 = Object.create(r7, {constructor: {value: r12}});
        r26 = [1015, 1008, 1017];
        r27 = r8;
        r7 = originalConstruct(r12, [r26, r25]);
        r8 = r7 instanceof Object ? r7 : r8;
        r12 = r4.Set;
        r4 = r12.prototype;
        r7 = Object.create(r4, {constructor: {value: r12}});
        r26 = [1017, 1015, 1008];
        r27 = r7;
        r4 = originalConstruct(r12, [r26, r25]);
        r7 = r4 instanceof Object ? r4 : r7;
        r4 = {};
        r4['ecstatic'] = r11;
        r4['energetic'] = r10;
        r4['healing'] = r9;
        r4['depressed'] = r8;
        r4['calm'] = r7;
        var _closure1_slot11 = r4;
        r21 = new Array(2);
        r4 = -55.72;
        r21[0] = r4;
        r4 = 5.7;
        r21[1] = r4;
        r20 = new Array(2);
        r4 = -56.96;
        r20[0] = r4;
        r4 = 6.63;
        r20[1] = r4;
        r19 = new Array(2);
        r4 = -57.72;
        r19[0] = r4;
        r4 = 6.71;
        r19[1] = r4;
        r18 = new Array(2);
        r4 = -58.01;
        r18[0] = r4;
        r4 = 6.83;
        r18[1] = r4;
        r17 = new Array(2);
        r4 = -58.02;
        r17[0] = r4;
        r4 = 7.7;
        r17[1] = r4;
        r16 = new Array(2);
        r4 = -59.73;
        r16[0] = r4;
        r4 = 5.4;
        r16[1] = r4;
        r15 = new Array(2);
        r4 = -62.44;
        r15[0] = r4;
        r4 = 5.68;
        r15[1] = r4;
        r14 = new Array(2);
        r4 = -64.13;
        r14[0] = r4;
        r4 = 8.01;
        r14[1] = r4;
        r13 = new Array(2);
        r4 = -63.22;
        r13[0] = r4;
        r4 = 6.65;
        r13[1] = r4;
        r12 = new Array(2);
        r4 = -68.88;
        r12[0] = r4;
        r4 = 9.41;
        r12[1] = r4;
        r11 = new Array(2);
        r4 = -62.02;
        r11[0] = r4;
        r4 = 5.28;
        r11[1] = r4;
        r10 = new Array(2);
        r4 = -64.83;
        r10[0] = r4;
        r4 = 6.26;
        r10[1] = r4;
        r9 = new Array(2);
        r4 = -62.88;
        r9[0] = r4;
        r4 = 4.7;
        r9[1] = r4;
        r8 = new Array(2);
        r4 = -62.59;
        r8[0] = r4;
        r4 = 4.45;
        r8[1] = r4;
        r7 = new Array(2);
        r4 = -63.85;
        r7[0] = r4;
        r4 = 6.01;
        r7[1] = r4;
        r4 = {};
        r4['ecstatic'] = r21;
        r4['passionate'] = r20;
        r4['energetic'] = r19;
        r4['joyful'] = r18;
        r4['sweet'] = r17;
        r4['confident'] = r16;
        r4['romantic'] = r15;
        r4['relaxed'] = r14;
        r4['healing'] = r13;
        r4['calm'] = r12;
        r4['nostalgic'] = r11;
        r4['lonely'] = r10;
        r4['sad'] = r9;
        r4['bitter'] = r8;
        r4['depressed'] = r7;
        var _closure1_slot12 = r4;
        r19 = new Array(2);
        r4 = -24.69;
        r19[0] = r4;
        r4 = 3.09;
        r19[1] = r4;
        r18 = new Array(2);
        r4 = -24.02;
        r18[0] = r4;
        r4 = 3.19;
        r18[1] = r4;
        r17 = new Array(2);
        r4 = -25.11;
        r17[0] = r4;
        r4 = 3.5;
        r17[1] = r4;
        r16 = new Array(2);
        r4 = -24.87;
        r16[0] = r4;
        r4 = 3.44;
        r16[1] = r4;
        r15 = new Array(2);
        r4 = -24.98;
        r15[0] = r4;
        r4 = 3.74;
        r15[1] = r4;
        r14 = new Array(2);
        r7 = -25.71;
        r14[0] = r7;
        r7 = 3.21;
        r14[1] = r7;
        r13 = new Array(2);
        r7 = -26.07;
        r13[0] = r7;
        r13[1] = r6;
        r12 = new Array(2);
        r6 = -26.6;
        r12[0] = r6;
        r6 = 3.99;
        r12[1] = r6;
        r11 = new Array(2);
        r6 = -25.44;
        r11[0] = r6;
        r6 = 3.58;
        r11[1] = r6;
        r10 = new Array(2);
        r6 = -27.12;
        r10[0] = r6;
        r6 = 4.05;
        r10[1] = r6;
        r9 = new Array(2);
        r6 = -25.69;
        r9[0] = r6;
        r9[1] = r5;
        r8 = new Array(2);
        r5 = -26.47;
        r8[0] = r5;
        r5 = 3.48;
        r8[1] = r5;
        r7 = new Array(2);
        r5 = -25.97;
        r7[0] = r5;
        r5 = 3;
        r7[1] = r5;
        r6 = new Array(2);
        r20 = -26.08;
        r6[0] = r20;
        r6[1] = r5;
        r5 = new Array(2);
        r20 = -26.44;
        r5[0] = r20;
        r5[1] = r4;
        r4 = {};
        r4['ecstatic'] = r19;
        r4['passionate'] = r18;
        r4['energetic'] = r17;
        r4['joyful'] = r16;
        r4['sweet'] = r15;
        r4['confident'] = r14;
        r4['romantic'] = r13;
        r4['relaxed'] = r12;
        r4['healing'] = r11;
        r4['calm'] = r10;
        r4['nostalgic'] = r9;
        r4['lonely'] = r8;
        r4['sad'] = r7;
        r4['bitter'] = r6;
        r4['depressed'] = r5;
        var _closure1_slot13 = r4;
        r4 = {'ecstatic': 0.255, 'passionate': 0.284, 'energetic': 0.258, 'joyful': 0.258, 'sweet': 0.257, 'confident': 0.248, 'romantic': 0.262, 'relaxed': 0.267, 'healing': 0.288, 'calm': 0.3, 'nostalgic': 0.269, 'lonely': 0.266, 'sad': 0.267, 'bitter': 0.267, 'depressed': 0.258};
        var _closure1_slot14 = r4;
        r17 = new Array(2);
        r4 = -7.71;
        r17[0] = r4;
        r4 = 2.01;
        r17[1] = r4;
        r16 = new Array(2);
        r4 = -8.08;
        r16[0] = r4;
        r4 = 2.59;
        r16[1] = r4;
        r15 = new Array(2);
        r4 = -8.37;
        r15[0] = r4;
        r4 = 2.43;
        r15[1] = r4;
        r14 = new Array(2);
        r4 = -8.9;
        r14[0] = r4;
        r4 = 2.79;
        r14[1] = r4;
        r13 = new Array(2);
        r4 = -9.14;
        r13[0] = r4;
        r4 = 3.07;
        r13[1] = r4;
        r12 = new Array(2);
        r4 = -8.69;
        r12[0] = r4;
        r4 = 2.18;
        r12[1] = r4;
        r11 = new Array(2);
        r4 = -9.96;
        r11[0] = r4;
        r4 = 2.84;
        r11[1] = r4;
        r10 = new Array(2);
        r4 = -10.37;
        r10[0] = r4;
        r4 = 3.39;
        r10[1] = r4;
        r9 = new Array(2);
        r4 = -10.03;
        r9[0] = r4;
        r4 = 3.11;
        r9[1] = r4;
        r8 = new Array(2);
        r4 = -12.4;
        r8[0] = r4;
        r4 = 4.14;
        r8[1] = r4;
        r7 = new Array(2);
        r4 = -9.83;
        r7[0] = r4;
        r4 = 2.77;
        r7[1] = r4;
        r6 = new Array(2);
        r4 = -11.03;
        r6[0] = r4;
        r4 = 3.36;
        r6[1] = r4;
        r5 = new Array(2);
        r4 = -10.18;
        r5[0] = r4;
        r4 = 2.75;
        r5[1] = r4;
        r4 = new Array(2);
        r4[0] = r3;
        r3 = 2.98;
        r4[1] = r3;
        r3 = new Array(2);
        r18 = -10.11;
        r3[0] = r18;
        r3[1] = r1;
        r1 = {};
        r1['ecstatic'] = r17;
        r1['passionate'] = r16;
        r1['energetic'] = r15;
        r1['joyful'] = r14;
        r1['sweet'] = r13;
        r1['confident'] = r12;
        r1['romantic'] = r11;
        r1['relaxed'] = r10;
        r1['healing'] = r9;
        r1['calm'] = r8;
        r1['nostalgic'] = r7;
        r1['lonely'] = r6;
        r1['sad'] = r5;
        r1['bitter'] = r4;
        r1['depressed'] = r3;
        var _closure1_slot15 = r1;
        r1 = {};
        r3 = [0.63, 0.18];
        r1['ecstatic'] = r3;
        r3 = [0.64, 0.22];
        r1['passionate'] = r3;
        r3 = [0.63, 0.21];
        r1['energetic'] = r3;
        r3 = [0.62, 0.21];
        r1['joyful'] = r3;
        r3 = [0.64, 0.22];
        r1['sweet'] = r3;
        r3 = [0.59, 0.21];
        r1['confident'] = r3;
        r3 = [0.52, 0.19];
        r1['romantic'] = r3;
        r3 = [0.56, 0.21];
        r1['relaxed'] = r3;
        r3 = [0.52, 0.22];
        r1['healing'] = r3;
        r3 = [0.48, 0.22];
        r1['calm'] = r3;
        r3 = [0.48, 0.18];
        r1['nostalgic'] = r3;
        r3 = [0.46, 0.18];
        r1['lonely'] = r3;
        r3 = [0.45, 0.16];
        r1['sad'] = r3;
        r3 = [0.45, 0.16];
        r1['bitter'] = r3;
        r3 = [0.5, 0.19];
        r1['depressed'] = r3;
        var _closure1_slot16 = r1;
        r1 = {};
        r3 = [0.72, 0.3];
        r1['ecstatic'] = r3;
        r3 = [0.79, 0.24];
        r1['passionate'] = r3;
        r3 = [0.65, 0.36];
        r1['energetic'] = r3;
        r3 = [0.58, 0.43];
        r1['joyful'] = r3;
        r3 = [0.55, 0.49];
        r1['sweet'] = r3;
        r3 = [0.46, 0.4];
        r1['confident'] = r3;
        r3 = [0.32, 0.37];
        r1['romantic'] = r3;
        r3 = [0.25, 0.37];
        r1['relaxed'] = r3;
        r3 = [0.36, 0.43];
        r1['healing'] = r3;
        r3 = [0.02, 0.05];
        r1['calm'] = r3;
        r3 = [0.31, 0.33];
        r1['nostalgic'] = r3;
        r3 = [0.2, 0.3];
        r1['lonely'] = r3;
        r3 = [0.28, 0.3];
        r1['sad'] = r3;
        r3 = [0.3, 0.27];
        r1['bitter'] = r3;
        r3 = [0.28, 0.36];
        r1['depressed'] = r3;
        var _closure1_slot17 = r1;
        r1 = {};
        r3 = [0.83, 0.16];
        r1['ecstatic'] = r3;
        r3 = [0.75, 0.21];
        r1['passionate'] = r3;
        r3 = [0.8, 0.19];
        r1['energetic'] = r3;
        r3 = [0.78, 0.18];
        r1['joyful'] = r3;
        r3 = [0.77, 0.18];
        r1['sweet'] = r3;
        r3 = [0.86, 0.12];
        r1['confident'] = r3;
        r3 = [0.78, 0.16];
        r1['romantic'] = r3;
        r3 = [0.72, 0.28];
        r1['relaxed'] = r3;
        r3 = [0.72, 0.21];
        r1['healing'] = r3;
        r3 = [0.62, 0.4];
        r1['calm'] = r3;
        r3 = [0.79, 0.13];
        r1['nostalgic'] = r3;
        r3 = [0.74, 0.16];
        r1['lonely'] = r3;
        r3 = [0.78, 0.12];
        r1['sad'] = r3;
        r3 = [0.78, 0.1];
        r1['bitter'] = r3;
        r3 = [0.77, 0.16];
        r1['depressed'] = r3;
        var _closure1_slot18 = r1;
        r1 = {};
        r3 = [0.32, 0.07];
        r1['ecstatic'] = r3;
        r3 = [0.31, 0.07];
        r1['passionate'] = r3;
        r3 = [0.31, 0.07];
        r1['energetic'] = r3;
        r3 = [0.3, 0.07];
        r1['joyful'] = r3;
        r3 = [0.31, 0.09];
        r1['sweet'] = r3;
        r3 = [0.31, 0.07];
        r1['confident'] = r3;
        r3 = [0.26, 0.07];
        r1['romantic'] = r3;
        r3 = [0.26, 0.07];
        r1['relaxed'] = r3;
        r3 = [0.26, 0.07];
        r1['healing'] = r3;
        r3 = [0.23, 0.07];
        r1['calm'] = r3;
        r3 = [0.27, 0.06];
        r1['nostalgic'] = r3;
        r3 = [0.25, 0.07];
        r1['lonely'] = r3;
        r3 = [0.26, 0.06];
        r1['sad'] = r3;
        r3 = [0.26, 0.06];
        r1['bitter'] = r3;
        r3 = [0.26, 0.07];
        r1['depressed'] = r3;
        var _closure1_slot19 = r1;
        r1 = {};
        r3 = [0.34, 0.16];
        r1['ecstatic'] = r3;
        r3 = [0.26, 0.16];
        r1['passionate'] = r3;
        r3 = [0.3, 0.18];
        r1['energetic'] = r3;
        r3 = [0.23, 0.16];
        r1['joyful'] = r3;
        r3 = [0.21, 0.18];
        r1['sweet'] = r3;
        r3 = [0.23, 0.18];
        r1['confident'] = r3;
        r3 = [0.14, 0.15];
        r1['romantic'] = r3;
        r3 = [0.15, 0.21];
        r1['relaxed'] = r3;
        r3 = [0.12, 0.15];
        r1['healing'] = r3;
        r3 = [0, 0.05];
        r1['calm'] = r3;
        r3 = [0.14, 0.13];
        r1['nostalgic'] = r3;
        r3 = [0.08, 0.12];
        r1['lonely'] = r3;
        r3 = [0.11, 0.1];
        r1['sad'] = r3;
        r3 = [0.11, 0.09];
        r1['bitter'] = r3;
        r3 = [0.16, 0.16];
        r1['depressed'] = r3;
        var _closure1_slot20 = r1;
        r1 = {};
        r3 = [16.76, 1.76];
        r1['ecstatic'] = r3;
        r3 = [16.57, 1.9];
        r1['passionate'] = r3;
        r3 = [16.83, 1.91];
        r1['energetic'] = r3;
        r3 = [16.32, 1.78];
        r1['joyful'] = r3;
        r3 = [16.21, 1.84];
        r1['sweet'] = r3;
        r3 = [16.88, 1.79];
        r1['confident'] = r3;
        r3 = [16.64, 1.91];
        r1['romantic'] = r3;
        r3 = [16.81, 1.94];
        r1['relaxed'] = r3;
        r3 = [16.3, 1.88];
        r1['healing'] = r3;
        r3 = [16.26, 1.96];
        r1['calm'] = r3;
        r3 = [16.32, 1.78];
        r1['nostalgic'] = r3;
        r3 = [16.47, 1.96];
        r1['lonely'] = r3;
        r3 = [16.23, 1.79];
        r1['sad'] = r3;
        r3 = [16.58, 1.82];
        r1['bitter'] = r3;
        r3 = [16.78, 2.09];
        r1['depressed'] = r3;
        var _closure1_slot21 = r1;
        r1 = {};
        r3 = [0.23, 0.13];
        r1['ecstatic'] = r3;
        r3 = [0.24, 0.19];
        r1['passionate'] = r3;
        r3 = [0.23, 0.16];
        r1['energetic'] = r3;
        r3 = [0.3, 0.18];
        r1['joyful'] = r3;
        r3 = [0.32, 0.21];
        r1['sweet'] = r3;
        r3 = [0.27, 0.18];
        r1['confident'] = r3;
        r3 = [0.36, 0.22];
        r1['romantic'] = r3;
        r3 = [0.23, 0.28];
        r1['relaxed'] = r3;
        r3 = [0.33, 0.29];
        r1['healing'] = r3;
        r3 = [0.27, 0.4];
        r1['calm'] = r3;
        r3 = [0.4, 0.19];
        r1['nostalgic'] = r3;
        r3 = [0.41, 0.22];
        r1['lonely'] = r3;
        r3 = [0.44, 0.16];
        r1['sad'] = r3;
        r3 = [0.44, 0.15];
        r1['bitter'] = r3;
        r3 = [0.33, 0.22];
        r1['depressed'] = r3;
        var _closure1_slot22 = r1;
        r1 = {};
        r3 = [502.77, 137.85];
        r1['ecstatic'] = r3;
        r3 = [511.44, 154.11];
        r1['passionate'] = r3;
        r3 = [479.26, 154.15];
        r1['energetic'] = r3;
        r3 = [457.65, 157.73];
        r1['joyful'] = r3;
        r3 = [448.09, 163.65];
        r1['sweet'] = r3;
        r3 = [449.32, 136.38];
        r1['confident'] = r3;
        r3 = [368.9, 133.48];
        r1['romantic'] = r3;
        r3 = [398.27, 144.6];
        r1['relaxed'] = r3;
        r3 = [381.97, 140.94];
        r1['healing'] = r3;
        r3 = [327.4, 105.95];
        r1['calm'] = r3;
        r3 = [334.69, 112.69];
        r1['nostalgic'] = r3;
        r3 = [328.6, 108.35];
        r1['lonely'] = r3;
        r3 = [326.5, 97.71];
        r1['sad'] = r3;
        r3 = [333.07, 97.15];
        r1['bitter'] = r3;
        r3 = [347.3, 135.11];
        r1['depressed'] = r3;
        var _closure1_slot23 = r1;
        r1 = {};
        r3 = [0.223, 0.11];
        r1['ecstatic'] = r3;
        r3 = [0.231, 0.109];
        r1['passionate'] = r3;
        r3 = [0.255, 0.108];
        r1['energetic'] = r3;
        r3 = [0.226, 0.1];
        r1['joyful'] = r3;
        r3 = [0.202, 0.099];
        r1['sweet'] = r3;
        r3 = [0.29, 0.135];
        r1['confident'] = r3;
        r3 = [0.221, 0.098];
        r1['romantic'] = r3;
        r3 = [0.227, 0.099];
        r1['relaxed'] = r3;
        r3 = [0.215, 0.067];
        r1['healing'] = r3;
        r3 = [0.236, 0.094];
        r1['calm'] = r3;
        r3 = [0.208, 0.09];
        r1['nostalgic'] = r3;
        r3 = [0.23, 0.098];
        r1['lonely'] = r3;
        r3 = [0.241, 0.094];
        r1['sad'] = r3;
        r3 = [0.224, 0.09];
        r1['bitter'] = r3;
        r3 = [0.213, 0.096];
        r1['depressed'] = r3;
        var _closure1_slot24 = r1;
        r1 = {};
        r3 = [0.13, 0.085];
        r1['ecstatic'] = r3;
        r3 = [0.148, 0.096];
        r1['passionate'] = r3;
        r3 = [0.115, 0.067];
        r1['energetic'] = r3;
        r3 = [0.139, 0.08];
        r1['joyful'] = r3;
        r3 = [0.152, 0.096];
        r1['sweet'] = r3;
        r3 = [0.127, 0.073];
        r1['confident'] = r3;
        r3 = [0.181, 0.084];
        r1['romantic'] = r3;
        r3 = [0.19, 0.109];
        r1['relaxed'] = r3;
        r3 = [0.23, 0.1];
        r1['healing'] = r3;
        r3 = [0.218, 0.098];
        r1['calm'] = r3;
        r3 = [0.209, 0.089];
        r1['nostalgic'] = r3;
        r3 = [0.2, 0.082];
        r1['lonely'] = r3;
        r3 = [0.201, 0.09];
        r1['sad'] = r3;
        r3 = [0.194, 0.079];
        r1['bitter'] = r3;
        r3 = [0.206, 0.081];
        r1['depressed'] = r3;
        var _closure1_slot25 = r1;
        return r0;
    };
factories[959]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r3 = a1;
        r6 = a5;
        r4 = a6;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: s, environment: r2
            _fun9483: for(var _fun9483_ip = 0; ; ) switch(_fun9483_ip) {
case 0:
                r4 = a0;
                r0 = null;
                r0 = r0 != r4;
                if(!r0) { _fun9483_ip = 44; continue _fun9483 }
case 12:
                r1 = global;
                r1 = r1.Object;
                r1 = r1.prototype;
                r3 = r1.hasOwnProperty;
                r2 = r3.call;
                r1 = a1;
                r0 = r2.bind(r3)(r4, r1);
case 44:
                return r0;
            }
        };
        var _closure1_slot6 = r0;
        r0 = function(a0, a1, a2, a3) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: u, environment: r2
            _fun9484: for(var _fun9484_ip = 0; ; ) switch(_fun9484_ip) {
case 0:
                r4 = a0;
                r6 = a1;
                r3 = a2;
                r1 = a3;
                r0 = undefined;
                r5 = _closure1_slot6;
                r5 = r5.bind(r0)(r4, r6);
                if(!r5) { _fun9484_ip = 323; continue _fun9484 }
case 33:
                r7 = null;
                r5 = undefined;
                if(!(r7 != r4)) { _fun9484_ip = 45; continue _fun9484 }
case 41:
                r5 = r4[r6];
case 45:
                r8 = 'number';
                r4 = typeof r5;
                if(!(r8 === r4)) { _fun9484_ip = 78; continue _fun9484 }
case 56:
                r4 = global;
                r9 = r4.Number;
                r8 = r9.isFinite;
                r8 = r8.bind(r9)(r5);
                if(r8) { _fun9484_ip = 105; continue _fun9484 }
case 78:
                r9 = r3.push;
                r8 = ' phải là giá trị hữu hạn, giá trị thực tế: ';
                r8 = r6 + r8;
                r8 = r8 + r5;
                r8 = r9.bind(r3)(r8);
                _fun9484_ip = 323; continue _fun9484;
case 105:
                if(!(r7 != r1)) { _fun9484_ip = 165; continue _fun9484 }
case 109:
                r8 = r1.integer;
                if(!r8) { _fun9484_ip = 165; continue _fun9484 }
case 118:
                r8 = r4.Number;
                r4 = r8.isInteger;
                r4 = r4.bind(r8)(r5);
                if(r4) { _fun9484_ip = 165; continue _fun9484 }
case 138:
                r8 = r3.push;
                r4 = ' phải là số nguyên, giá trị thực tế: ';
                r4 = r6 + r4;
                r4 = r4 + r5;
                r4 = r8.bind(r3)(r4);
                _fun9484_ip = 323; continue _fun9484;
case 165:
                r4 = undefined;
                if(!(r7 != r1)) { _fun9484_ip = 176; continue _fun9484 }
case 171:
                r4 = r1.min;
case 176:
                if(!(r7 != r4)) { _fun9484_ip = 203; continue _fun9484 }
case 180:
                r8 = r1.min;
                r4 = _closure1_slot5;
                r4 = r4.RANGE_EPSILON;
                r4 = r8 - r4;
                if(!(!(r5 < r4))) { _fun9484_ip = 284; continue _fun9484 }
case 203:
                r4 = undefined;
                if(!(r7 != r1)) { _fun9484_ip = 214; continue _fun9484 }
case 209:
                r4 = r1.max;
case 214:
                if(!(r7 != r4)) { _fun9484_ip = 241; continue _fun9484 }
case 218:
                r4 = r1.max;
                r2 = _closure1_slot5;
                r2 = r2.RANGE_EPSILON;
                r2 = r4 + r2;
                if(!(!(r5 > r2))) { _fun9484_ip = 243; continue _fun9484 }
case 241:
                return r5;
case 243:
                r4 = r3.push;
                r2 = ' = ';
                r2 = r6 + r2;
                r8 = r2 + r5;
                r7 = r1.max;
                r2 = ' vượt quá giới hạn trên ';
                r2 = r8 + r2;
                r2 = r2 + r7;
                r2 = r4.bind(r3)(r2);
                _fun9484_ip = 323; continue _fun9484;
case 284:
                r2 = r3.push;
                r4 = ' = ';
                r4 = r6 + r4;
                r5 = r4 + r5;
                r4 = r1.min;
                r1 = ' thấp hơn giới hạn dưới ';
                r1 = r5 + r1;
                r1 = r1 + r4;
                r1 = r2.bind(r3)(r1);
case 323:
                return r0;
            }
        };
        var _closure1_slot7 = r0;
        r0 = function(a0, a1, a2, a3, a4) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: d, environment: r2
            _fun9485: for(var _fun9485_ip = 0; ; ) switch(_fun9485_ip) {
case 0:
                r10 = a0;
                r7 = a1;
                r6 = a2;
                r8 = a3;
                r5 = a4;
                r1 = 'number';
                r0 = typeof r10;
                if(!(r1 === r0)) { _fun9485_ip = 48; continue _fun9485 }
case 26:
                r0 = global;
                r1 = r0.Number;
                r0 = r1.isFinite;
                r0 = r0.bind(r1)(r10);
                if(r0) { _fun9485_ip = 82; continue _fun9485 }
case 48:
                r1 = r5.push;
                r0 = ' = ';
                r0 = r8 + r0;
                r3 = r0 + r10;
                r0 = ' không phải là số hợp lệ';
                r0 = r3 + r0;
                r0 = r1.bind(r5)(r0);
                r0 = false;
                _fun9485_ip = 203; continue _fun9485;
case 82:
                r3 = _closure1_slot5;
                r3 = r3.RANGE_EPSILON;
                r3 = r7 - r3;
                r3 = r10 < r3;
                if(r3) { _fun9485_ip = 124; continue _fun9485 }
case 106:
                r1 = _closure1_slot5;
                r1 = r1.RANGE_EPSILON;
                r1 = r6 + r1;
                r3 = r10 > r1;
case 124:
                r1 = !r3;
                if(!r3) { _fun9485_ip = 200; continue _fun9485 }
case 130:
                r4 = r5.push;
                r3 = ' = ';
                r8 = r8 + r3;
                r9 = r10.toFixed;
                r3 = 4;
                r3 = r9.bind(r10)(r3);
                r8 = r8 + r3;
                r3 = ' nằm ngoài phạm vi [';
                r3 = r8 + r3;
                r7 = r3 + r7;
                r3 = ', ';
                r3 = r7 + r3;
                r6 = r3 + r6;
                r3 = ']';
                r3 = r6 + r3;
                r3 = r4.bind(r5)(r3);
                r1 = false;
case 200:
                r0 = r1;
case 203:
                return r0;
            }
        };
        var _closure1_slot8 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: p, environment: r2
            _fun9486: for(var _fun9486_ip = 0; ; ) switch(_fun9486_ip) {
case 0:
                r0 = a0;
                r3 = 0;
                r12 = ']';
                r11 = -12;
                r10 = 12;
                r1 = new Array(0);
                if(r0) { _fun9486_ip = 61; continue _fun9486 }
case 25:
                r4 = new Array(1);
                r5 = 'output.aev2';
                r2 = ' là null/undefined';
                r2 = r5 + r2;
                r4[0] = r2;
                r2 = {};
                r5 = false;
                r2['valid'] = r5;
                r2['issues'] = r4;
                return r2;
case 61:
                r2 = global;
                r7 = r2.Array;
                r5 = r7.isArray;
                r4 = r0.eq_gain_db;
                r4 = r5.bind(r7)(r4);
                if(r4) { _fun9486_ip = 110; continue _fun9486 }
case 88:
                r5 = r1.push;
                r7 = 'output.aev2';
                r4 = '.eq_gain_db phải là mảng';
                r4 = r7 + r4;
                r4 = r5.bind(r1)(r4);
case 110:
                r5 = r2.Array;
                r4 = r5.isArray;
                r2 = r0.eq_gain_db;
                r2 = r4.bind(r5)(r2);
                if(r2) { _fun9486_ip = 141; continue _fun9486 }
case 135:
                r9 = new Array(0);
                _fun9486_ip = 147; continue _fun9486;
case 141:
                r9 = r0.eq_gain_db;
case 147:
                r5 = r9.length;
                r4 = _closure1_slot2;
                r4 = r4.AEV2_EQ_GAIN_DB_SIZE;
                if(!(r5 !== r4)) { _fun9486_ip = 222; continue _fun9486 }
case 169:
                r5 = r1.push;
                r4 = _closure1_slot2;
                r7 = r4.AEV2_EQ_GAIN_DB_SIZE;
                r8 = 'output.aev2';
                r4 = 'Độ dài .eq_gain_db phải là ';
                r4 = r8 + r4;
                r8 = r4 + r7;
                r7 = r9.length;
                r4 = ', hiện tại là ';
                r4 = r8 + r4;
                r4 = r4 + r7;
                r4 = r5.bind(r1)(r4);
case 222:
                r4 = r9.length;
                r7 = 'output.aev2';
                r5 = '.eq_gain_db[';
                r8 = r7 + r5;
                r5 = 0;
                if(!(r5 < r4)) { _fun9486_ip = 288; continue _fun9486 }
case 245:
                r14 = _closure1_slot8;
                r19 = r9[r5];
                r4 = r8 + r5;
                r16 = r4 + r12;
                r20 = undefined;
                r18 = r11;
                r17 = r10;
                r15 = r1;
                r4 = r14.call(r20, r19, r18, r17, r16, r15, r14);
                r5 = r5 + 1;
                r4 = r9.length;
                if(r5 < r4) { _fun9486_ip = 245; continue _fun9486 }
case 288:
                r4 = r0.reverb;
                if(!r4) { _fun9486_ip = 445; continue _fun9486 }
case 300:
                r4 = r0.reverb;
                r9 = _closure1_slot8;
                r19 = r4.reverb_time;
                r17 = 10;
                r5 = '.reverb.reverb_time';
                r16 = r7 + r5;
                r20 = undefined;
                r18 = 0;
                r15 = r1;
                r5 = r9.call(r20, r19, r18, r17, r16, r15, r14);
                r19 = r4.dry_level;
                r10 = -96.3;
                r5 = '.reverb.dry_level';
                r16 = r7 + r5;
                r18 = r10;
                r17 = 0;
                r15 = r1;
                r5 = r9.call(r20, r19, r18, r17, r16, r15, r14);
                r19 = r4.wet_level;
                r5 = '.reverb.wet_level';
                r16 = r7 + r5;
                r15 = r1;
                r5 = r9.call(r20, r19, r18, r17, r16, r15, r14);
                r19 = r4.room_size;
                r8 = 1;
                r5 = '.reverb.room_size';
                r16 = r7 + r5;
                r18 = 0;
                r17 = r8;
                r15 = r1;
                r5 = r9.call(r20, r19, r18, r17, r16, r15, r14);
                r19 = r4.diffusion;
                r4 = '.reverb.diffusion';
                r16 = r7 + r4;
                r15 = r1;
                r4 = r9.call(r20, r19, r18, r17, r16, r15, r14);
case 445:
                r4 = r0.delay;
                if(!r4) { _fun9486_ip = 519; continue _fun9486 }
case 454:
                r4 = r0.delay;
                r9 = _closure1_slot8;
                r19 = r4.delay_time;
                r17 = 5000;
                r5 = '.delay.delay_time';
                r16 = r7 + r5;
                r20 = undefined;
                r18 = 0;
                r15 = r1;
                r5 = r9.call(r20, r19, r18, r17, r16, r15, r14);
                r19 = r4.feedback;
                r17 = 1;
                r4 = '.delay.feedback';
                r16 = r7 + r4;
                r15 = r1;
                r4 = r9.call(r20, r19, r18, r17, r16, r15, r14);
case 519:
                r4 = r0.stereo_enhance;
                if(!r4) { _fun9486_ip = 590; continue _fun9486 }
case 528:
                r0 = r0.stereo_enhance;
                r5 = _closure1_slot8;
                r19 = r0.stereo_width;
                r4 = 10;
                r2 = '.stereo_enhance.stereo_width';
                r16 = r7 + r2;
                r20 = undefined;
                r18 = 0;
                r17 = r4;
                r15 = r1;
                r2 = r5.call(r20, r19, r18, r17, r16, r15, r14);
                r19 = r0.stereo_depth;
                r0 = '.stereo_enhance.stereo_depth';
                r16 = r7 + r0;
                r15 = r1;
                r0 = r5.call(r20, r19, r18, r17, r16, r15, r14);
case 590:
                r2 = r1.length;
                r0 = {};
                r2 = r3 === r2;
                r0['valid'] = r2;
                r0['issues'] = r1;
                return r0;
            }
        };
        var _closure1_slot9 = r0;
        r0 = 0;
        r1 = r4[r0];
        r0 = undefined;
        r5 = r3.bind(r0)(r1);
        r1 = global;
        r9 = r1.Object;
        r8 = r9.defineProperty;
        r7 = {};
        r1 = true;
        r7['value'] = r1;
        r1 = '__esModule';
        r1 = r8.bind(r9)(r6, r1, r7);
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9487: for(var _fun9487_ip = 0; ; ) switch(_fun9487_ip) {
case 0:
                r2 = a0;
                r32 = a1;
                r3 = undefined;
                r8 = true;
                r0 = new Array(0);
                r9 = null;
                if(!(r9 != r2)) { _fun9487_ip = 1528; continue _fun9487 }
case 25:
                r5 = 'object';
                r4 = typeof r2;
                if(!(r5 === r4)) { _fun9487_ip = 1528; continue _fun9487 }
case 39:
                r5 = _closure1_slot6;
                r4 = 'emotion_label';
                r4 = r5.bind(r3)(r2, r4);
                r29 = undefined;
                r28 = false;
                if(!r4) { _fun9487_ip = 220; continue _fun9487 }
case 66:
                r4 = r2.emotion_label;
                r5 = 'string';
                r4 = typeof r4;
                if(!(r5 === r4)) { _fun9487_ip = 189; continue _fun9487 }
case 83:
                r5 = r2.emotion_label;
                r4 = r5.trim;
                r7 = r4.bind(r5)();
                r5 = r7.length;
                r4 = 0;
                r29 = undefined;
                r28 = false;
                if(!(r4 !== r5)) { _fun9487_ip = 220; continue _fun9487 }
case 114:
                r4 = _closure1_slot3;
                r4 = r4.isEmotionLabel;
                r4 = r4.bind(r3)(r7);
                r29 = r7;
                r28 = false;
                if(r4) { _fun9487_ip = 220; continue _fun9487 }
case 137:
                r4 = _closure1_slot4;
                r4 = r4.mapEmotionTags;
                r29 = r4.bind(r3)(r7);
                r28 = true;
                if(r29) { _fun9487_ip = 220; continue _fun9487 }
case 157:
                r5 = r0.push;
                r4 = 'Không tìm thấy ánh xạ nhãn tiếng Trung cho emotion_label: ';
                r7 = r4 + r7;
                r4 = ', sẽ thử emotion_mapping';
                r4 = r7 + r4;
                r4 = r5.bind(r0)(r4);
                r29 = undefined;
                r28 = false;
                _fun9487_ip = 220; continue _fun9487;
case 189:
                r5 = r0.push;
                r4 = r2.emotion_label;
                r7 = 'emotion_label phải là chuỗi, kiểu thực tế: ';
                r4 = typeof r4;
                r4 = r7 + r4;
                r4 = r5.bind(r0)(r4);
                r29 = undefined;
                r28 = false;
case 220:
                r7 = _closure1_slot7;
                r5 = {};
                r5['integer'] = r8;
                r38 = 'genre_id';
                r40 = undefined;
                r39 = r2;
                r37 = r0;
                r36 = r5;
                r27 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'dynamic_range';
                r36 = {'min': 0, 'max': 30};
                r39 = r2;
                r37 = r0;
                r24 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'loudness_lufs';
                r36 = {'min': -40, 'max': 0};
                r39 = r2;
                r37 = r0;
                r23 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'stereo_width_ratio';
                r36 = {'min': 0, 'max': 1};
                r39 = r2;
                r37 = r0;
                r22 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'repbpm';
                r36 = {'min': 30, 'max': 300};
                r39 = r2;
                r37 = r0;
                r21 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'chorus_loudness';
                r36 = {'min': -40, 'max': 0};
                r39 = r2;
                r37 = r0;
                r20 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'vocal_main_freq';
                r36 = {'min': 0, 'max': 1200};
                r39 = r2;
                r37 = r0;
                r19 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'vocal_time_ratio';
                r36 = {'min': 0, 'max': 1};
                r39 = r2;
                r37 = r0;
                r18 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'harmonic_intensity_ratio';
                r36 = {'min': 0, 'max': 1};
                r39 = r2;
                r37 = r0;
                r17 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'vocal_dynamic_range';
                r36 = {'min': 0, 'max': 30};
                r39 = r2;
                r37 = r0;
                r16 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'drum_time_ratio';
                r36 = {'min': 0, 'max': 1};
                r39 = r2;
                r37 = r0;
                r15 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'rhythm_complexity';
                r36 = {'min': 0, 'max': 10};
                r39 = r2;
                r37 = r0;
                r14 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'vocal_energy_ratio';
                r36 = {'min': 0, 'max': 1};
                r39 = r2;
                r37 = r0;
                r13 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'drum_energy_ratio';
                r36 = {'min': 0, 'max': 1};
                r39 = r2;
                r37 = r0;
                r12 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'bass_energy_ratio';
                r36 = {'min': 0, 'max': 1};
                r39 = r2;
                r37 = r0;
                r11 = r7.call(r40, r39, r38, r37, r36, r35);
                r38 = 'other_instrument_energy_ratio';
                r36 = {'min': 0, 'max': 1};
                r39 = r2;
                r37 = r0;
                r10 = r7.call(r40, r39, r38, r37, r36, r35);
                r5 = _closure1_slot6;
                r4 = 'key_mode';
                r4 = r5.bind(r3)(r2, r4);
                r26 = undefined;
                if(!r4) { _fun9487_ip = 710; continue _fun9487 }
case 629:
                r4 = r2.key_mode;
                r5 = 'string';
                r4 = typeof r4;
                if(!(r5 === r4)) { _fun9487_ip = 691; continue _fun9487 }
case 646:
                r5 = r2.key_mode;
                r4 = r5.trim;
                r4 = r4.bind(r5)();
                r5 = r4.length;
                r4 = 0;
                if(!(r4 !== r5)) { _fun9487_ip = 691; continue _fun9487 }
case 673:
                r5 = r2.key_mode;
                r4 = r5.trim;
                r4 = r4.bind(r5)();
                _fun9487_ip = 707; continue _fun9487;
case 691:
                r7 = r0.push;
                r5 = 'key_mode phải là chuỗi không rỗng';
                r5 = r7.bind(r0)(r5);
                r4 = undefined;
case 707:
                r26 = r4;
case 710:
                r5 = _closure1_slot6;
                r4 = 'mel_spectrogram_32';
                r4 = r5.bind(r3)(r2, r4);
                r25 = undefined;
                r7 = false;
                if(!r4) { _fun9487_ip = 916; continue _fun9487 }
case 734:
                r4 = global;
                r30 = r4.Array;
                r5 = r30.isArray;
                r4 = r2.mel_spectrogram_32;
                r4 = r5.bind(r30)(r4);
                if(r4) { _fun9487_ip = 784; continue _fun9487 }
case 761:
                r5 = r0.push;
                r4 = 'mel_spectrogram_32 không phải là mảng';
                r4 = r5.bind(r0)(r4);
                r5 = undefined;
                r4 = false;
                _fun9487_ip = 910; continue _fun9487;
case 784:
                r30 = r2.mel_spectrogram_32;
                r33 = r30.length;
                r30 = 32;
                if(!(r30 === r33)) { _fun9487_ip = 871; continue _fun9487 }
case 802:
                r34 = r2.mel_spectrogram_32;
                r33 = r34.some;
                r30 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
                    _fun9488: for(var _fun9488_ip = 0; ; ) switch(_fun9488_ip) {
case 0:
                        r3 = a0;
                        r1 = 'number';
                        r0 = typeof r3;
                        r0 = r1 !== r0;
                        if(r0) { _fun9488_ip = 39; continue _fun9488 }
case 17:
                        r1 = global;
                        r2 = r1.Number;
                        r1 = r2.isFinite;
                        r1 = r1.bind(r2)(r3);
                        r0 = !r1;
case 39:
                        return r0;
                    }
                };
                r30 = r33.bind(r34)(r30);
                if(r30) { _fun9487_ip = 851; continue _fun9487 }
case 827:
                r6 = _closure1_slot1;
                r30 = r6.default;
                r6 = r2.mel_spectrogram_32;
                r30 = r30.bind(r3)(r6);
                r6 = true;
                _fun9487_ip = 869; continue _fun9487;
case 851:
                r34 = r0.push;
                r33 = 'Các phần tử trong mảng mel_spectrogram_32 phải đều là giá trị hữu hạn';
                r33 = r34.bind(r0)(r33);
                r30 = undefined;
                r6 = false;
case 869:
                _fun9487_ip = 904; continue _fun9487;
case 871:
                r34 = r0.push;
                r33 = r2.mel_spectrogram_32;
                r35 = r33.length;
                r33 = 'Độ dài mảng mel_spectrogram_32 phải là 32, hiện tại là ';
                r33 = r33 + r35;
                r33 = r34.bind(r0)(r33);
                r30 = undefined;
                r6 = false;
case 904:
                r5 = r30;
                r4 = r6;
case 910:
                r25 = r5;
                r7 = r4;
case 916:
                var _closure2_slot0 = r0;
                r0 = ['mel_spectrogram_32'];
                r0[1] = r7;
                r5 = new Array(16);
                r5[0] = r0;
                r4 = r9 != r24;
                r0 = ['dynamic_range'];
                r0[1] = r4;
                r5[1] = r0;
                r4 = r9 != r23;
                r0 = ['loudness_lufs'];
                r0[1] = r4;
                r5[2] = r0;
                r4 = r9 != r22;
                r0 = ['stereo_width_ratio'];
                r0[1] = r4;
                r5[3] = r0;
                r4 = r9 != r21;
                r0 = ['repbpm'];
                r0[1] = r4;
                r5[4] = r0;
                r4 = r9 != r20;
                r0 = ['chorus_loudness'];
                r0[1] = r4;
                r5[5] = r0;
                r4 = r9 != r19;
                r0 = ['vocal_main_freq'];
                r0[1] = r4;
                r5[6] = r0;
                r4 = r9 != r18;
                r0 = ['vocal_time_ratio'];
                r0[1] = r4;
                r5[7] = r0;
                r4 = r9 != r17;
                r0 = ['harmonic_intensity_ratio'];
                r0[1] = r4;
                r5[8] = r0;
                r4 = r9 != r16;
                r0 = ['vocal_dynamic_range'];
                r0[1] = r4;
                r5[9] = r0;
                r4 = r9 != r15;
                r0 = ['drum_time_ratio'];
                r0[1] = r4;
                r5[10] = r0;
                r4 = r9 != r14;
                r0 = ['rhythm_complexity'];
                r0[1] = r4;
                r5[11] = r0;
                r4 = r9 != r13;
                r0 = ['vocal_energy_ratio'];
                r0[1] = r4;
                r5[12] = r0;
                r4 = r9 != r12;
                r0 = ['drum_energy_ratio'];
                r0[1] = r4;
                r5[13] = r0;
                r4 = r9 != r11;
                r0 = ['bass_energy_ratio'];
                r0[1] = r4;
                r5[14] = r0;
                r4 = r9 != r10;
                r0 = ['other_instrument_energy_ratio'];
                r0[1] = r4;
                r5[15] = r0;
                r4 = r5.filter;
                r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
                    r0 = _closure1_slot0;
                    r3 = r0.default;
                    r2 = undefined;
                    r1 = a0;
                    r0 = 2;
                    r1 = r3.bind(r2)(r1, r0);
                    r0 = 1;
                    r0 = r1[r0];
                    r0 = !r0;
                    return r0;
                };
                r5 = r4.bind(r5)(r0);
                r4 = r5.map;
                r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
                    r0 = _closure1_slot0;
                    r3 = r0.default;
                    r2 = undefined;
                    r1 = a0;
                    r0 = 1;
                    r1 = r3.bind(r2)(r1, r0);
                    r0 = 0;
                    r0 = r1[r0];
                    return r0;
                };
                r5 = r4.bind(r5)(r0);
                r6 = r5.length;
                r4 = r5.map;
                r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
                    _fun9491: for(var _fun9491_ip = 0; ; ) switch(_fun9491_ip) {
case 0:
                        r2 = a0;
                        var _closure3_slot0 = r2;
                        r3 = _closure2_slot0;
                        r1 = r3.find;
                        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r0
                            r2 = a0;
                            r1 = r2.startsWith;
                            r3 = _closure3_slot0;
                            r0 = ' ';
                            r0 = r3 + r0;
                            r0 = r1.bind(r2)(r0);
                            return r0;
                        };
                        r0 = r1.bind(r3)(r0);
                        r1 = null;
                        if(!(r1 == r0)) { _fun9491_ip = 46; continue _fun9491 }
case 38:
                        r1 = ': missing or null';
                        r0 = r2 + r1;
case 46:
                        return r0;
                    }
                };
                r4 = r4.bind(r5)(r0);
                r0 = r7;
                if(r0) { _fun9487_ip = 1300; continue _fun9487 }
case 1296:
                r0 = r9 != r21;
case 1300:
                r5 = _closure2_slot0;
                r30 = r5.length;
                r5 = 0;
                if(!(r30 > r5)) { _fun9487_ip = 1346; continue _fun9487 }
case 1315:
                if(!(r9 != r32)) { _fun9487_ip = 1346; continue _fun9487 }
case 1319:
                r31 = r32.warn;
                r9 = _closure2_slot0;
                r30 = {};
                r30['issues'] = r9;
                r9 = 'input:sanitized';
                r9 = r31.bind(r32)(r9, r30);
case 1346:
                if(r28) { _fun9487_ip = 1351; continue _fun9487 }
case 1349:
                r28 = undefined;
case 1351:
                r9 = r2.model_signature;
                r2 = r2.supportStereo;
                r3 = {};
                r3['emotion_label'] = r29;
                r3['emotionFromTagMapping'] = r28;
                r3['genre_id'] = r27;
                r3['key_mode'] = r26;
                r3['mel_spectrogram_32'] = r25;
                r3['dynamic_range'] = r24;
                r3['loudness_lufs'] = r23;
                r3['stereo_width_ratio'] = r22;
                r3['repbpm'] = r21;
                r3['chorus_loudness'] = r20;
                r3['vocal_main_freq'] = r19;
                r3['vocal_time_ratio'] = r18;
                r3['harmonic_intensity_ratio'] = r17;
                r3['vocal_dynamic_range'] = r16;
                r3['drum_time_ratio'] = r15;
                r3['rhythm_complexity'] = r14;
                r3['vocal_energy_ratio'] = r13;
                r3['drum_energy_ratio'] = r12;
                r3['bass_energy_ratio'] = r11;
                r3['other_instrument_energy_ratio'] = r10;
                r3['model_signature'] = r9;
                r2 = r8 === r2;
                r3['supportStereo'] = r2;
                r2 = _closure2_slot0;
                r1 = {};
                r1['hasMelSpectrogram'] = r7;
                r5 = r5 === r6;
                r1['hasAllAudioFeatures'] = r5;
                r1['missingAudioFeatures'] = r4;
                r1['hasEmotionMappingFeatures'] = r0;
                r0 = {};
                r0['sanitized'] = r3;
                r0['issues'] = r2;
                r0['availability'] = r1;
                return r0;
case 1528:
                r1 = {'hasMelSpectrogram': false, 'hasAllAudioFeatures': false, 'missingAudioFeatures': null, 'hasEmotionMappingFeatures': false};
                r0 = ['all (input is null or not an object)'];
                r1['missingAudioFeatures'] = r0;
                r0 = {};
                r2 = {};
                r0['sanitized'] = r2;
                 r2 = ['input rỗng hoặc không phải đối tượng'];
                r0['issues'] = r2;
                r0['availability'] = r1;
                return r0;
            }
        };
        r6['sanitizeInput'] = r1;
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9493: for(var _fun9493_ip = 0; ; ) switch(_fun9493_ip) {
case 0:
                r4 = a1;
                r3 = _closure1_slot9;
                r2 = undefined;
                r1 = a0;
                r0 = 'output.aev2';
                r0 = r3.bind(r2)(r1, r0);
                r1 = r0.valid;
                if(r1) { _fun9493_ip = 40; continue _fun9493 }
case 34:
                r2 = null;
                r1 = r2 == r4;
case 40:
                if(r1) { _fun9493_ip = 72; continue _fun9493 }
case 43:
                r3 = r4.warn;
                r1 = r0.issues;
                r2 = {};
                r2['issues'] = r1;
                r1 = 'output:range:exceeded';
                r1 = r3.bind(r4)(r1, r2);
case 72:
                return r0;
            }
        };
        r6['validateOutputRanges'] = r1;
        r1 = 1;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        r1 = r5.bind(r0)(r1);
        var _closure1_slot0 = r1;
        r1 = 2;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        r1 = r5.bind(r0)(r1);
        var _closure1_slot1 = r1;
        r1 = 3;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot2 = r1;
        r1 = 4;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot3 = r1;
        r1 = 5;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot4 = r1;
        r1 = 6;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot5 = r1;
        return r0;
    };
factories[960]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r3 = a5;
        r0 = global;
        r6 = r0.Object;
        r5 = r6.defineProperty;
        r4 = {};
        r2 = true;
        r4['value'] = r2;
        r2 = '__esModule';
        r2 = r5.bind(r6)(r3, r2, r4);
        r2 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
            _fun9495: for(var _fun9495_ip = 0; ; ) switch(_fun9495_ip) {
case 0:
                r6 = a0;
                r0 = undefined;
                r7 = undefined;
                r8 = undefined;
                r4 = undefined;
                r5 = undefined;
                if(r6) { _fun9495_ip = 20; continue _fun9495 }
case 18:
                return r0;
case 20:
                r3 = r6.split;
                r2 = ',';
                r6 = r3.bind(r6)(r2);
                r3 = r6.map;
                r2 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
                    r1 = a0;
                    r0 = r1.trim;
                    r0 = r0.bind(r1)();
                    return r0;
                };
                r6 = r3.bind(r6)(r2);
                r3 = r6.filter;
                r2 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
                    r0 = a0;
                    r1 = r0.length;
                    r0 = 0;
                    r0 = r1 > r0;
                    return r0;
                };
                r6 = r3.bind(r6)(r2);
                r3 = r6.length;
                r2 = 0;
                if(!(r2 !== r3)) { _fun9495_ip = 301; continue _fun9495 }
case 78:
                r2 = r6.every;
                r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
                    r2 = _closure1_slot1;
                    r1 = r2.has;
                    r0 = a0;
                    r0 = r1.bind(r2)(r0);
                    return r0;
                };
                r1 = r2.bind(r6)(r1);
                if(r1) { _fun9495_ip = 299; continue _fun9495 }
case 100:
                r1 = r6;
                var __iterator_values_9=Array.from(r1), __iterator_index_9=0; r2={return:function(){}}; r1=function(){if(__iterator_index_9>=__iterator_values_9.length){r2=undefined;return {value:undefined}}return {value:__iterator_values_9[__iterator_index_9++]}};
case 109:
                r11 = r1().value;
                r9 = r2;
                if(!(r9 !== r0)) { _fun9495_ip = 200; continue _fun9495 }
case 120: // try_start_0
                r7 = r11;
                r10 = _closure1_slot0;
                r9 = r10.has;
                r9 = r9.bind(r10)(r11);
                if(r9) { _fun9495_ip = 191; continue _fun9495 }
case 140:
                r11 = _closure1_slot1;
                r10 = r11.has;
                r9 = r7;
                r9 = r10.bind(r11)(r9);
                if(r9) { _fun9495_ip = 191; continue _fun9495 }
case 160:
                r11 = _closure1_slot2;
                r10 = r11.get;
                r9 = r7;
                r9 = r10.bind(r11)(r9);
                r8 = r9;
                if(!r9) { _fun9495_ip = 191; continue _fun9495 }
case 183:
                r9 = r8;
case 186: // try_end0
                r2.return();
                return r9;
case 191:
                _fun9495_ip = 109; continue _fun9495;
case 193: // catch_target0
                CatchBlockStart(arg_register=1);
                r2.return();
                throw r1;
case 200:
                r1 = r6;
                var __iterator_values_10=Array.from(r1), __iterator_index_10=0; r2={return:function(){}}; r1=function(){if(__iterator_index_10>=__iterator_values_10.length){r2=undefined;return {value:undefined}}return {value:__iterator_values_10[__iterator_index_10++]}};
case 206:
                r8 = r1().value;
                r6 = r2;
                if(!(r6 !== r0)) { _fun9495_ip = 297; continue _fun9495 }
case 217: // try_start_1
                r4 = r8;
                r7 = _closure1_slot0;
                r6 = r7.has;
                r6 = r6.bind(r7)(r8);
                if(r6) { _fun9495_ip = 288; continue _fun9495 }
case 237:
                r8 = _closure1_slot1;
                r7 = r8.has;
                r6 = r4;
                r6 = r7.bind(r8)(r6);
                if(r6) { _fun9495_ip = 288; continue _fun9495 }
case 257:
                r8 = _closure1_slot3;
                r7 = r8.get;
                r6 = r4;
                r6 = r7.bind(r8)(r6);
                r5 = r6;
                if(!r6) { _fun9495_ip = 288; continue _fun9495 }
case 280:
                r6 = r5;
case 283: // try_end1
                r2.return();
                return r6;
case 288:
                _fun9495_ip = 206; continue _fun9495;
case 290: // catch_target1
                CatchBlockStart(arg_register=1);
                r2.return();
                throw r1;
case 297:
                return r0;
case 299:
                return r0;
case 301:
                return r0;
            }
        };
        r3['mapEmotionTags'] = r2;
        r4 = r0.Set;
        r2 = r4.prototype;
        r3 = Object.create(r2, {constructor: {value: r4}});
        r9 = ['Cảm xúc tích cực', 'Cảm xúc trung tính', 'Cảm xúc tiêu cực', 'Cảm xúc tiêu cực', 'Cảm xúc u sầu'];
        r10 = r3;
        r2 = originalConstruct(r4, [r9, r8]);
        r2 = r2 instanceof Object ? r2 : r3;
        var _closure1_slot0 = r2;
        r4 = r0.Set;
        r2 = r4.prototype;
        r3 = Object.create(r2, {constructor: {value: r4}});
        r9 = ['Không có nhãn nghiệp vụ phù hợp'];
        r10 = r3;
        r2 = originalConstruct(r4, [r9, r8]);
        r2 = r2 instanceof Object ? r2 : r3;
        var _closure1_slot1 = r2;
        r4 = r0.Map;
        r2 = new Array(15);
        r3 = ['Cuồng nhiệt', 'ecstatic'];
        r2[0] = r3;
        r3 = ['Nhiệt huyết truyền cảm hứng', 'passionate'];
        r2[1] = r3;
        r3 = ['Tràn đầy năng lượng', 'energetic'];
        r2[2] = r3;
        r3 = ['Vui tươi', 'joyful'];
        r2[3] = r3;
        r3 = ['Ngọt ngào', 'sweet'];
        r2[4] = r3;
        r3 = ['Tự tin', 'confident'];
        r2[5] = r3;
        r3 = ['Lãng mạn', 'romantic'];
        r2[6] = r3;
        r3 = ['Thư giãn', 'relaxed'];
        r2[7] = r3;
        r3 = ['Chữa lành', 'healing'];
        r2[8] = r3;
        r3 = ['Bình yên', 'calm'];
        r2[9] = r3;
        r3 = ['Nhớ nhung', 'nostalgic'];
        r2[10] = r3;
        r3 = ['Cô đơn', 'lonely'];
        r2[11] = r3;
        r3 = ['Buồn bã', 'sad'];
        r2[12] = r3;
        r3 = ['Đau khổ', 'bitter'];
        r2[13] = r3;
        r3 = ['Trầm cảm', 'depressed'];
        r2[14] = r3;
        r3 = r4.prototype;
        r3 = Object.create(r3, {constructor: {value: r4}});
        r10 = r3;
        r9 = r2;
        r2 = originalConstruct(r4, [r9, r8]);
        r2 = r2 instanceof Object ? r2 : r3;
        var _closure1_slot2 = r2;
        r3 = r0.Map;
        r0 = new Array(39);
        r2 = ['Sôi động', 'energetic'];
        r0[0] = r2;
        r2 = ['Bùng cháy', 'passionate'];
        r0[1] = r2;
        r2 = ['hùng hổ', 'passionate'];
        r0[2] = r2;
        r2 = ['mang tính công kích', 'passionate'];
        r0[3] = r2;
        r2 = ['căng thẳng', 'passionate'];
        r0[4] = r2;
        r2 = ['giận dữ', 'passionate'];
        r0[5] = r2;
        r2 = ['hào sảng', 'passionate'];
        r0[6] = r2;
        r2 = ['vui vẻ', 'joyful'];
        r0[7] = r2;
        r2 = ['hạnh phúc', 'joyful'];
        r0[8] = r2;
        r2 = ['nhẹ nhàng, vui tươi', 'joyful'];
        r0[9] = r2;
        r2 = ['tươi mới', 'joyful'];
        r0[10] = r2;
        r2 = ['hài hước', 'joyful'];
        r0[11] = r2;
        r2 = ['tươi sáng', 'joyful'];
        r0[12] = r2;
        r2 = ['ngợi ca', 'joyful'];
        r0[13] = r2;
        r2 = ['trữ tình', 'romantic'];
        r0[14] = r2;
        r2 = ['tình ca', 'romantic'];
        r0[15] = r2;
        r2 = ['tán tỉnh', 'romantic'];
        r0[16] = r2;
        r2 = ['tình yêu', 'romantic'];
        r0[17] = r2;
        r2 = ['đa tình', 'romantic'];
        r0[18] = r2;
        r2 = ['đáng yêu', 'sweet'];
        r0[19] = r2;
        r2 = ['tình bạn', 'sweet'];
        r0[20] = r2;
        r2 = ['tình thân', 'sweet'];
        r0[21] = r2;
        r2 = ['biết ơn', 'sweet'];
        r0[22] = r2;
        r2 = ['dịu dàng', 'sweet'];
        r0[23] = r2;
        r2 = ['chính khí', 'confident'];
        r0[24] = r2;
        r2 = ['chính trực', 'confident'];
        r0[25] = r2;
        r2 = ['Phóng khoáng', 'confident'];
        r0[26] = r2;
        r2 = ['Tự do', 'confident'];
        r0[27] = r2;
        r2 = ['Trang nghiêm', 'confident'];
        r0[28] = r2;
        r2 = ['Tự hào', 'confident'];
        r0[29] = r2;
        r2 = ['Lười biếng', 'relaxed'];
        r0[30] = r2;
        r2 = ['Bình hòa', 'calm'];
        r0[31] = r2;
        r2 = ['Mộng ảo', 'healing'];
        r0[32] = r2;
        r2 = ['Cảm động', 'healing'];
        r0[33] = r2;
        r2 = ['Mong đợi', 'nostalgic'];
        r0[34] = r2;
        r2 = ['Tiếc nuối', 'nostalgic'];
        r0[35] = r2;
        r2 = ['Tăm tối', 'depressed'];
        r0[36] = r2;
        r2 = ['Rùng rợn', 'depressed'];
        r0[37] = r2;
        r2 = ['Bực bội', 'bitter'];
        r0[38] = r2;
        r2 = r3.prototype;
        r2 = Object.create(r2, {constructor: {value: r3}});
        r10 = r2;
        r9 = r0;
        r0 = originalConstruct(r3, [r9, r8]);
        r0 = r0 instanceof Object ? r0 : r2;
        var _closure1_slot3 = r0;
        r0 = undefined;
        return r0;
    };
factories[961]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r3 = a5;
        r0 = global;
        r5 = r0.Object;
        r4 = r5.defineProperty;
        r1 = {};
        r0 = true;
        r1['value'] = r0;
        r0 = '__esModule';
        r0 = r4.bind(r5)(r3, r0, r1);
        r0 = undefined;
        r3['GENRE_TAG_ID_TO_FAMILY'] = r0;
        r3['GENRE_TAG_ID_TO_NAME'] = r0;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9500: for(var _fun9500_ip = 0; ; ) switch(_fun9500_ip) {
case 0:
                r2 = _closure1_slot1;
                r0 = a0;
                r0 = r2[r0];
                r2 = null;
                if(!(r2 == r0)) { _fun9500_ip = 30; continue _fun9500 }
case 20:
                r1 = _closure1_slot0;
                r0 = r1.DEFAULT_FAMILY;
case 30:
                return r0;
            }
        };
        r3['getFamily'] = r1;
        r1 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9501: for(var _fun9501_ip = 0; ; ) switch(_fun9501_ip) {
case 0:
                r0 = 'Thể loại nhạc không xác định';
                r2 = _closure1_slot2;
                r1 = a0;
                r1 = r2[r1];
                r2 = null;
                if(!(r2 != r1)) { _fun9501_ip = 27; continue _fun9501 }
case 24:
                r0 = r1;
case 27:
                return r0;
            }
        };
        r3['getGenreName'] = r1;
        r4 = a6;
        r1 = 0;
        r4 = r4[r1];
        r1 = a1;
        r1 = r1.bind(r0)(r4);
        var _closure1_slot0 = r1;
        r1 = {1000: 'pop-family', 1028: 'pop-family', 1010: 'pop-family', 1252: 'pop-family', 1005: 'pop-family', 1029: 'pop-family', 1044: 'pop-family', 1068: 'pop-family', 1138: 'pop-family', 1262: 'pop-family', 1439: 'pop-family', 79088: 'pop-family', 1003: 'folk-family', 1026: 'folk-family', 10119: 'folk-family', 1001: 'folk-family', 1077: 'folk-family', 1037: 'folk-family', 1012: 'folk-family', 1263: 'folk-family', 1071: 'folk-family', 1043: 'folk-family', 1017: 'rhythm-family', 1015: 'rhythm-family', 1008: 'rhythm-family', 1014: 'rhythm-family', 1041: 'rhythm-family', 152124: 'rhythm-family', 1049: 'rhythm-family', 1022: 'instrumental-family', 1007: 'instrumental-family', 1011: 'instrumental-family', 1006: 'instrumental-family', 1060683: 'instrumental-family'};
        r3['GENRE_TAG_ID_TO_FAMILY'] = r1;
        var _closure1_slot1 = r1;
        r1 = {1000: 'Pop', 1001: 'Đặc trưng truyền thống Trung Quốc', 1003: 'Dân ca', 1005: 'Khác', 1006: 'Nhạc nhẹ', 1007: 'New Age', 1008: 'Rock', 1010: 'Nhạc phim', 1011: 'Jazz', 1012: 'Nhạc thế giới', 1014: 'Alternative/Indie', 1015: 'Nhạc điện tử', 1017: 'Hip-hop/Rap', 1022: 'Cổ điển', 1026: 'Thiếu nhi', 1028: 'R&B', 1029: 'Funk', 1037: 'Blues', 1041: 'Metal', 1043: 'Reggae', 1044: 'Nhạc tôn giáo', 1049: 'Thể nghiệm', 1060683: 'Nhạc không lời', 1068: 'Nhạc sân khấu', 1071: 'Nhạc Latin', 1077: 'Nhạc đồng quê', 1138: 'Nội dung âm thanh', 1252: 'ACG', 1262: 'Nhạc lễ hội', 1263: 'Thể loại nhạc cụ thế giới', 1439: 'Ca sĩ kiêm nhạc sĩ', 10119: 'Phong cách Trung Hoa', 79088: 'DJ Slow', 152124: 'Punk'};
        r3['GENRE_TAG_ID_TO_NAME'] = r1;
        var _closure1_slot2 = r1;
        return r0;
    };
factories[962]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r2 = a5;
        r0 = global;
        r4 = r0.Object;
        r3 = r4.defineProperty;
        r1 = {};
        r0 = true;
        r1['value'] = r0;
        r0 = '__esModule';
        r0 = r3.bind(r4)(r2, r0, r1);
        r0 = undefined;
        r2['DEFAULT_EMOTION'] = r0;
        r2['DEFAULT_FAMILY'] = r0;
        r1 = 'default';
        r2['DEFAULT_EMOTION'] = r1;
        r1 = 'pop-family';
        r2['DEFAULT_FAMILY'] = r1;
        return r0;
    };
factories[963]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r3 = a1;
        r1 = a5;
        r4 = a6;
        r5 = global;
        r9 = r5.Object;
        r8 = r9.defineProperty;
        r7 = {};
        r0 = true;
        r7['value'] = r0;
        r6 = '__esModule';
        r6 = r8.bind(r9)(r1, r6, r7);
        r9 = r5.Object;
        r8 = r9.defineProperty;
        r7 = {};
        r7['enumerable'] = r0;
        r6 = function() {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: get, environment: r2
            r0 = _closure1_slot0;
            r0 = r0.V1FourFactorStrategy;
            return r0;
        };
        r7['get'] = r6;
        r6 = 'V1FourFactorStrategy';
        r6 = r8.bind(r9)(r1, r6, r7);
        r7 = r5.Object;
        r6 = r7.defineProperty;
        r5 = {};
        r5['enumerable'] = r0;
        r0 = function() {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: get, environment: r2
            r0 = _closure1_slot1;
            r0 = r0.VisualExtraStrategy;
            return r0;
        };
        r5['get'] = r0;
        r0 = 'VisualExtraStrategy';
        r0 = r6.bind(r7)(r1, r0, r5);
        r0 = function() {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            r0 = _closure1_slot0;
            r0 = r0.V1FourFactorStrategy;
            r1 = r0.prototype;
            r1 = Object.create(r1, {constructor: {value: r0}});
            r2 = r1;
            r0 = originalConstruct(r0, [r1]);
            r0 = r0 instanceof Object ? r0 : r1;
            return r0;
        };
        r1['getVisualExtraStrategy'] = r0;
        r0 = 0;
        r1 = r4[r0];
        r0 = undefined;
        r1 = r3.bind(r0)(r1);
        var _closure1_slot0 = r1;
        r1 = 1;
        r1 = r4[r1];
        r1 = r3.bind(r0)(r1);
        var _closure1_slot1 = r1;
        return r0;
    };
factories[964]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r4 = a1;
        r2 = a5;
        r5 = a6;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: v, environment: r1
            r0 = global;
            r3 = r0.Math;
            r2 = r3.max;
            r5 = r0.Math;
            r4 = r5.min;
            r1 = 1;
            r0 = a0;
            r1 = r4.bind(r5)(r1, r0);
            r0 = 0;
            r0 = r2.bind(r3)(r0, r1);
            return r0;
        };
        var _closure1_slot10 = r0;
        r0 = function(a0, a1, a2) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: y, environment: r1
            _fun9509: for(var _fun9509_ip = 0; ; ) switch(_fun9509_ip) {
case 0:
                r5 = a1;
                r1 = a2;
                r3 = _closure1_slot6;
                r4 = r3.floatEqual;
                r3 = undefined;
                r4 = r4.bind(r3)(r1, r5);
                r0 = 0;
                if(r4) { _fun9509_ip = 56; continue _fun9509 }
case 32:
                r2 = _closure1_slot10;
                r4 = a0;
                r4 = r4 - r5;
                r1 = r1 - r5;
                r1 = r4 / r1;
                r0 = r2.bind(r3)(r1);
case 56:
                return r0;
            }
        };
        var _closure1_slot11 = r0;
        r0 = function(a0, a1, a2) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: d, environment: r1
            _fun9510: for(var _fun9510_ip = 0; ; ) switch(_fun9510_ip) {
case 0:
                r0 = a2;
                r7 = undefined;
                r2 = null;
                r6 = 'object';
                r5 = undefined;
                r1 = a0;
                r3 = a1;
                var __iterator_values_11=Array.from(r3), __iterator_index_11=0; r4={return:function(){}}; r3=function(){if(__iterator_index_11>=__iterator_values_11.length){r4=undefined;return {value:undefined}}return {value:__iterator_values_11[__iterator_index_11++]}};
case 22:
                r8 = r3().value;
                r9 = r4;
                if(!(r9 !== r7)) { _fun9510_ip = 77; continue _fun9510 }
case 33: // try_start_0
                r5 = r8;
                r8 = r1;
                if(!(r2 != r8)) { _fun9510_ip = 65; continue _fun9510 }
case 43:
                r8 = r1;
                r8 = typeof r8;
                if(!(r6 === r8)) { _fun9510_ip = 65; continue _fun9510 }
case 53:
                r9 = r1;
                r8 = r5;
                r1 = r9[r8];
case 63: // try_end0
                _fun9510_ip = 22; continue _fun9510;
case 65:
                r4.return();
                return r0;
case 70: // catch_target0
                CatchBlockStart(arg_register=3);
                r4.return();
                throw r3;
case 77:
                if(!(r2 != r1)) { _fun9510_ip = 84; continue _fun9510 }
case 81:
                r0 = r1;
case 84:
                return r0;
            }
        };
        var _closure1_slot12 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: S, environment: r1
            r4 = _closure1_slot12;
            r3 = new Array(2);
            r0 = a1;
            r3[0] = r0;
            r0 = 'on';
            r3[1] = r0;
            r2 = undefined;
            r1 = a0;
            r0 = false;
            r0 = r4.bind(r2)(r1, r3, r0);
            r0 = !r0;
            r0 = !r0;
            return r0;
        };
        var _closure1_slot13 = r0;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: I, environment: r1
            r1 = a0;
            var _closure2_slot0 = r1;
            r1 = _closure1_slot7;
            r3 = r1.VISUAL_DIMENSIONS;
            r2 = r3.reduce;
            r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r0
                r0 = a0;
                r2 = a1;
                r4 = _closure1_slot9;
                r3 = r4[r2];
                r1 = _closure2_slot0;
                r1 = r3.bind(r4)(r1);
                r0[r2] = r1;
                return r0;
            };
            r0 = {};
            r0 = r2.bind(r3)(r1, r0);
            return r0;
        };
        var _closure1_slot14 = r0;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: A, environment: r1
            _fun9514: for(var _fun9514_ip = 0; ; ) switch(_fun9514_ip) {
case 0:
                r2 = a0;
                r33 = 120;
                r32 = -14;
                r31 = 15;
                r30 = 0.5;
                r20 = 0;
                r19 = undefined;
                r18 = 'high_freq';
                r17 = 2;
                r16 = 1;
                r15 = null;
                r14 = -70;
                r13 = 'loudness';
                r1 = undefined;
                r5 = undefined;
                r6 = undefined;
                r7 = undefined;
                r8 = undefined;
                r9 = undefined;
                r10 = undefined;
                r11 = undefined;
                r12 = undefined;
                if(r2) { _fun9514_ip = 110; continue _fun9514 }
case 74:
                r3 = global;
                r22 = r3.Object;
                r21 = r22.assign;
                r3 = _closure1_slot7;
                r4 = r3.FALLBACK_SONG_ACTIVATIONS;
                r3 = {};
                r3 = r21.bind(r22)(r3, r4);
                return r3;
case 110:
                r3 = r2.mel_spectrogram_32;
                r4 = r3.length;
                r3 = 32;
                if(!(r3 === r4)) { _fun9514_ip = 770; continue _fun9514 }
case 131:
                r3 = r2.repbpm;
                if(!(r15 != r3)) { _fun9514_ip = 144; continue _fun9514 }
case 141:
                r33 = r3;
case 144:
                r3 = r2.loudness_lufs;
                if(!(r15 != r3)) { _fun9514_ip = 157; continue _fun9514 }
case 154:
                r32 = r3;
case 157:
                r3 = r2.dynamic_range;
                if(!(r15 != r3)) { _fun9514_ip = 170; continue _fun9514 }
case 167:
                r31 = r3;
case 170:
                r3 = r2.stereo_width_ratio;
                if(!(r15 != r3)) { _fun9514_ip = 183; continue _fun9514 }
case 180:
                r30 = r3;
case 183:
                r3 = r2.vocal_main_freq;
                r29 = 0;
                if(!(r15 != r3)) { _fun9514_ip = 198; continue _fun9514 }
case 195:
                r29 = r3;
case 198:
                r3 = r2.vocal_time_ratio;
                r28 = 0;
                if(!(r15 != r3)) { _fun9514_ip = 213; continue _fun9514 }
case 210:
                r28 = r3;
case 213:
                r3 = r2.harmonic_intensity_ratio;
                r27 = 0;
                if(!(r15 != r3)) { _fun9514_ip = 228; continue _fun9514 }
case 225:
                r27 = r3;
case 228:
                r3 = r2.vocal_dynamic_range;
                r26 = 0;
                if(!(r15 != r3)) { _fun9514_ip = 243; continue _fun9514 }
case 240:
                r26 = r3;
case 243:
                r3 = r2.drum_time_ratio;
                r25 = 0;
                if(!(r15 != r3)) { _fun9514_ip = 258; continue _fun9514 }
case 255:
                r25 = r3;
case 258:
                r3 = r2.rhythm_complexity;
                r24 = 0;
                if(!(r15 != r3)) { _fun9514_ip = 273; continue _fun9514 }
case 270:
                r24 = r3;
case 273:
                r3 = r2.vocal_energy_ratio;
                r23 = 0;
                if(!(r15 != r3)) { _fun9514_ip = 288; continue _fun9514 }
case 285:
                r23 = r3;
case 288:
                r3 = r2.drum_energy_ratio;
                r22 = 0;
                if(!(r15 != r3)) { _fun9514_ip = 303; continue _fun9514 }
case 300:
                r22 = r3;
case 303:
                r3 = r2.bass_energy_ratio;
                r21 = 0;
                if(!(r15 != r3)) { _fun9514_ip = 318; continue _fun9514 }
case 315:
                r21 = r3;
case 318:
                r3 = r2.other_instrument_energy_ratio;
                r4 = 0;
                if(!(r15 != r3)) { _fun9514_ip = 333; continue _fun9514 }
case 330:
                r4 = r3;
case 333:
                r3 = {};
                r3['repbpm'] = r33;
                r3['loudness_lufs'] = r32;
                r3['dynamic_range'] = r31;
                r3['stereo_width_ratio'] = r30;
                r3['vocal_main_freq'] = r29;
                r3['vocal_time_ratio'] = r28;
                r3['harmonic_intensity_ratio'] = r27;
                r3['vocal_dynamic_range'] = r26;
                r3['drum_time_ratio'] = r25;
                r3['rhythm_complexity'] = r24;
                r3['vocal_energy_ratio'] = r23;
                r3['drum_energy_ratio'] = r22;
                r3['bass_energy_ratio'] = r21;
                r3['other_instrument_energy_ratio'] = r4;
                var _closure2_slot0 = r3;
                r4 = r2.mel_spectrogram_32;
                r3 = r4.forEach;
                r2 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r0
                    r2 = _closure2_slot0;
                    r1 = 'mel_';
                    r0 = a1;
                    r1 = r1 + r0;
                    r0 = a0;
                    r2[r1] = r0;
                    r0 = undefined;
                    return r0;
                };
                r2 = r3.bind(r4)(r2);
                r2 = _closure1_slot7;
                r22 = r2.VISUAL_DIMENSIONS;
                r21 = r22.reduce;
                r3 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r0
                    r0 = a0;
                    r2 = 0;
                    r1 = a1;
                    r0[r1] = r2;
                    return r0;
                };
                r2 = {};
                r1 = r21.bind(r22)(r3, r2);
                r2 = _closure1_slot7;
                r21 = r2.VISUAL_DIMENSIONS;
                r2 = r21;
                var __iterator_values_12=Array.from(r2), __iterator_index_12=0; r3={return:function(){}}; r2=function(){if(__iterator_index_12>=__iterator_values_12.length){r3=undefined;return {value:undefined}}return {value:__iterator_values_12[__iterator_index_12++]}};
case 478:
                r22 = r2().value;
                r21 = r3;
                if(!(r21 !== r19)) { _fun9514_ip = 768; continue _fun9514 }
case 492: // try_start_1
                r5 = r22;
                r6 = 0;
                r21 = _closure1_slot7;
                r21 = r21.SONG_FEATURE_WEIGHTS;
                r23 = r21[r22];
                r21 = r23;
                var __iterator_values_13=Array.from(r21), __iterator_index_13=0; r22={return:function(){}}; r21=function(){if(__iterator_index_13>=__iterator_values_13.length){r22=undefined;return {value:undefined}}return {value:__iterator_values_13[__iterator_index_13++]}};
case 517:
                r24 = r21().value;
                r23 = r22;
                if(!(r23 !== r19)) { _fun9514_ip = 684; continue _fun9514 }
case 531: // try_start_0
                r23 = _closure1_slot3;
                r23 = r23.default;
                r23 = r23.bind(r19)(r24, r17);
                r24 = r23[r20];
                r9 = r24;
                r10 = r23[r16];
                r23 = _closure1_slot7;
                r23 = r23.SONG_FEATURE_RANGES;
                r23 = r23[r24];
                r7 = r23;
                if(!(r15 == r23)) { _fun9514_ip = 592; continue _fun9514 }
case 578:
                r24 = new Array(2);
                r24[0] = r14;
                r24[1] = r20;
                _fun9514_ip = 595; continue _fun9514;
case 592:
                r24 = r7;
case 595:
                r23 = _closure1_slot3;
                r23 = r23.default;
                r23 = r23.bind(r19)(r24, r17);
                r11 = r23[r20];
                r12 = r23[r16];
                r24 = r6;
                r25 = r10;
                r28 = _closure1_slot11;
                r26 = _closure2_slot0;
                r23 = r9;
                r23 = r26[r23];
                r8 = r23;
                r27 = 0;
                if(!(r15 != r23)) { _fun9514_ip = 651; continue _fun9514 }
case 648:
                r27 = r8;
case 651:
                r26 = r11;
                r23 = r12;
                r23 = r28.bind(r19)(r27, r26, r23);
                r23 = r25 * r23;
                r6 = r24 + r23;
case 672: // try_end0
                _fun9514_ip = 517; continue _fun9514;
case 677: // catch_target0
                CatchBlockStart(arg_register=21);
                r22.return();
                throw r21;
case 684:
                r21 = r5;
                if(!(r18 !== r21)) { _fun9514_ip = 717; continue _fun9514 }
case 691:
                r21 = r5;
                if(!(r13 === r21)) { _fun9514_ip = 734; continue _fun9514 }
case 698:
                r22 = r6;
                r21 = _closure1_slot7;
                r21 = r21.LOUDNESS_SONG_ACT_COMPENSATION;
                r6 = r22 * r21;
                _fun9514_ip = 734; continue _fun9514;
case 717:
                r22 = r6;
                r21 = _closure1_slot7;
                r21 = r21.HIGH_FREQ_SONG_ACT_COMPENSATION;
                r6 = r22 * r21;
case 734:
                r23 = r1;
                r22 = r5;
                r24 = _closure1_slot10;
                r21 = r6;
                r21 = r24.bind(r19)(r21);
                r23[r22] = r21;
case 756: // try_end1
                _fun9514_ip = 478; continue _fun9514;
case 761: // catch_target1
                CatchBlockStart(arg_register=2);
                r3.return();
                throw r2;
case 768:
                return r1;
case 770:
                r1 = _closure1_slot7;
                r3 = r1.VISUAL_DIMENSIONS;
                r2 = r3.reduce;
                r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r0
                    r0 = a0;
                    r2 = 0;
                    r1 = a1;
                    r0[r1] = r2;
                    return r0;
                };
                r0 = {};
                r0 = r2.bind(r3)(r1, r0);
                return r0;
            }
        };
        var _closure1_slot15 = r0;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: b, environment: r1
            _fun9518: for(var _fun9518_ip = 0; ; ) switch(_fun9518_ip) {
case 0:
                r1 = a0;
                r2 = global;
                r4 = r2.Array;
                r3 = r4.isArray;
                r2 = r1.mel_spectrogram_32;
                r2 = r3.bind(r4)(r2);
                r0 = null;
                if(!r2) { _fun9518_ip = 205; continue _fun9518 }
case 35:
                r16 = r1.mel_spectrogram_32;
                r15 = r1.repbpm;
                r14 = r1.loudness_lufs;
                r13 = r1.dynamic_range;
                r12 = r1.stereo_width_ratio;
                r11 = r1.vocal_main_freq;
                r10 = r1.vocal_time_ratio;
                r9 = r1.harmonic_intensity_ratio;
                r8 = r1.vocal_dynamic_range;
                r7 = r1.drum_time_ratio;
                r6 = r1.rhythm_complexity;
                r5 = r1.vocal_energy_ratio;
                r4 = r1.drum_energy_ratio;
                r3 = r1.bass_energy_ratio;
                r2 = r1.other_instrument_energy_ratio;
                r1 = {};
                r1['mel_spectrogram_32'] = r16;
                r1['repbpm'] = r15;
                r1['loudness_lufs'] = r14;
                r1['dynamic_range'] = r13;
                r1['stereo_width_ratio'] = r12;
                r1['vocal_main_freq'] = r11;
                r1['vocal_time_ratio'] = r10;
                r1['harmonic_intensity_ratio'] = r9;
                r1['vocal_dynamic_range'] = r8;
                r1['drum_time_ratio'] = r7;
                r1['rhythm_complexity'] = r6;
                r1['vocal_energy_ratio'] = r5;
                r1['drum_energy_ratio'] = r4;
                r1['bass_energy_ratio'] = r3;
                r1['other_instrument_energy_ratio'] = r2;
                r0 = r1;
case 205:
                return r0;
            }
        };
        var _closure1_slot16 = r0;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: E, environment: r1
            r1 = a0;
            var _closure2_slot0 = r1;
            r1 = _closure1_slot7;
            r2 = r1.VISUAL_DIMENSIONS;
            r1 = r2.every;
            r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r0
                r2 = a0;
                r0 = _closure2_slot0;
                r1 = r0[r2];
                r0 = _closure1_slot7;
                r0 = r0.FALLBACK_SONG_ACTIVATIONS;
                r0 = r0[r2];
                r0 = r1 === r0;
                return r0;
            };
            r0 = r1.bind(r2)(r0);
            return r0;
        };
        var _closure1_slot17 = r0;
        r0 = function(a0, a1, a2, a3, a4, a5, a6, a7) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: N, environment: r1
            _fun9521: for(var _fun9521_ip = 0; ; ) switch(_fun9521_ip) {
case 0:
                r10 = a0;
                r16 = a3;
                r7 = undefined;
                r9 = null;
                r4 = 0;
                r3 = 1;
                r1 = undefined;
                r5 = undefined;
                r11 = undefined;
                r12 = undefined;
                r13 = undefined;
                r14 = undefined;
                r15 = undefined;
                r2 = a2;
                var _closure2_slot8 = r2;
                var _closure2_slot9 = r16;
                r2 = 0.25;
                var _closure2_slot10 = r2;
                r6 = 0.15;
                var _closure2_slot11 = r6;
                r6 = 0.35;
                var _closure2_slot12 = r6;
                var _closure2_slot13 = r2;
                r6 = _closure1_slot7;
                r6 = r6.EMOTION_BASELINES;
                r6 = r6[r10];
                var _closure2_slot0 = r6;
                r6 = _closure1_slot7;
                r8 = r6.GENRE_BASELINES;
                r6 = a1;
                r6 = r8[r6];
                var _closure2_slot1 = r6;
                r6 = _closure1_slot7;
                r18 = r6.VISUAL_DIMENSIONS;
                r17 = r18.reduce;
                r8 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r0
                    _fun9522: for(var _fun9522_ip = 0; ; ) switch(_fun9522_ip) {
case 0:
                        r0 = a0;
                        r2 = a1;
                        r1 = _closure2_slot8;
                        r4 = r1[r2];
                        r5 = _closure2_slot13;
                        r1 = _closure2_slot9;
                        r1 = r1[r2];
                        r5 = r5 * r1;
                        r1 = 1;
                        r1 = r1 + r5;
                        r5 = r4 * r1;
                        r4 = _closure2_slot10;
                        r8 = _closure2_slot0;
                        r7 = null;
                        r1 = undefined;
                        if(!(r7 != r8)) { _fun9522_ip = 68; continue _fun9522 }
case 60:
                        r8 = _closure2_slot0;
                        r1 = r8[r2];
case 68:
                        if(!(r7 == r1)) { _fun9522_ip = 79; continue _fun9522 }
case 72:
                        r1 = _closure1_slot8;
case 79:
                        r4 = r4 * r1;
                        r1 = _closure2_slot11;
                        r8 = _closure2_slot1;
                        r6 = undefined;
                        if(!(r7 != r8)) { _fun9522_ip = 105; continue _fun9522 }
case 97:
                        r8 = _closure2_slot1;
                        r6 = r8[r2];
case 105:
                        if(!(r7 == r6)) { _fun9522_ip = 116; continue _fun9522 }
case 109:
                        r6 = _closure1_slot8;
case 116:
                        r1 = r1 * r6;
                        r3 = _closure2_slot12;
                        r3 = r3 * r5;
                        r1 = r4 + r1;
                        r1 = r1 + r3;
                        r0[r2] = r1;
                        return r0;
                    }
                };
                r6 = {};
                r6 = r17.bind(r18)(r8, r6);
                var _closure2_slot2 = r6;
                r6 = _closure1_slot17;
                r6 = r6.bind(r7)(r16);
                var _closure2_slot3 = r6;
                r6 = _closure1_slot2;
                r8 = r6.default;
                r6 = _closure1_slot7;
                r6 = r6.VISUAL_DIMENSIONS;
                r17 = r8.bind(r7)(r6);
                r8 = r17.sort;
                r6 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r0
                    _fun9523: for(var _fun9523_ip = 0; ; ) switch(_fun9523_ip) {
case 0:
                        r4 = a0;
                        r3 = a1;
                        r0 = _closure2_slot3;
                        r6 = _closure2_slot2;
                        r8 = r6[r4];
                        if(r0) { _fun9523_ip = 96; continue _fun9523 }
case 24:
                        r7 = null;
                        r2 = 0;
                        if(!(r7 != r8)) { _fun9523_ip = 35; continue _fun9523 }
case 32:
                        r2 = r8;
case 35:
                        r0 = _closure2_slot9;
                        r9 = r0[r4];
                        r0 = 0;
                        if(!(r7 != r9)) { _fun9523_ip = 52; continue _fun9523 }
case 49:
                        r0 = r9;
case 52:
                        r2 = r2 - r0;
                        r0 = _closure2_slot2;
                        r9 = r0[r3];
                        r0 = 0;
                        if(!(r7 != r9)) { _fun9523_ip = 73; continue _fun9523 }
case 70:
                        r0 = r9;
case 73:
                        r5 = _closure2_slot9;
                        r5 = r5[r3];
                        r1 = 0;
                        if(!(r7 != r5)) { _fun9523_ip = 90; continue _fun9523 }
case 87:
                        r1 = r5;
case 90:
                        r1 = r0 - r1;
                        _fun9523_ip = 150; continue _fun9523;
case 96:
                        r5 = _closure1_slot7;
                        r5 = r5.FALLBACK_BEFORE_RATIOS;
                        r5 = r5[r4];
                        r7 = 1;
                        r5 = r7 - r5;
                        r2 = r8 * r5;
                        r6 = r6[r3];
                        r0 = _closure1_slot7;
                        r0 = r0.FALLBACK_BEFORE_RATIOS;
                        r0 = r0[r3];
                        r0 = r7 - r0;
                        r1 = r6 * r0;
case 150:
                        if(!(r2 === r1)) { _fun9523_ip = 167; continue _fun9523 }
case 154:
                        r0 = r4.localeCompare;
                        r0 = r0.bind(r4)(r3);
                        _fun9523_ip = 171; continue _fun9523;
case 167:
                        r0 = r1 - r2;
case 171:
                        return r0;
                    }
                };
                r17 = r8.bind(r17)(r6);
                r8 = r17.slice;
                r6 = 6;
                r17 = r8.bind(r17)(r4, r6);
                r1 = new Array(0);
                r5 = 0;
                r6 = r17;
                var __iterator_values_14=Array.from(r6), __iterator_index_14=0; r8={return:function(){}}; r6=function(){if(__iterator_index_14>=__iterator_values_14.length){r8=undefined;return {value:undefined}}return {value:__iterator_values_14[__iterator_index_14++]}};
case 242:
                r18 = r6().value;
                r17 = r8;
                if(!(r17 !== r7)) { _fun9521_ip = 407; continue _fun9521 }
case 256: // try_start_0
                r11 = r18;
                r17 = _closure2_slot2;
                r18 = r17[r18];
                r12 = r18;
                r17 = 0;
                if(!(r9 != r18)) { _fun9521_ip = 279; continue _fun9521 }
case 276:
                r17 = r12;
case 279:
                r14 = r17;
                r17 = _closure2_slot3;
                if(r17) { _fun9521_ip = 310; continue _fun9521 }
case 289:
                r17 = r11;
                r17 = r16[r17];
                r13 = r17;
                r18 = 0;
                if(!(r9 != r17)) { _fun9521_ip = 308; continue _fun9521 }
case 305:
                r18 = r13;
case 308:
                _fun9521_ip = 334; continue _fun9521;
case 310:
                r19 = r14;
                r17 = _closure1_slot7;
                r20 = r17.FALLBACK_BEFORE_RATIOS;
                r17 = r11;
                r17 = r20[r17];
                r18 = r19 * r17;
case 334:
                r15 = r18;
                r20 = r1;
                r19 = r20.push;
                r22 = r11;
                r21 = r14;
                r17 = {};
                r17['name'] = r22;
                r17['before'] = r18;
                r17['after'] = r21;
                r17 = r19.bind(r20)(r17);
                r17 = r5;
                if(!(r18 > r17)) { _fun9521_ip = 382; continue _fun9521 }
case 379:
                r5 = r15;
case 382:
                r18 = r14;
                r17 = r5;
                if(!(r18 > r17)) { _fun9521_ip = 395; continue _fun9521 }
case 392:
                r5 = r14;
case 395: // try_end0
                _fun9521_ip = 242; continue _fun9521;
case 400: // catch_target0
                CatchBlockStart(arg_register=6);
                r8.return();
                throw r6;
case 407:
                r11 = r5;
                r6 = 1e-06;
                r8 = r3;
                if(!(r11 > r6)) { _fun9521_ip = 444; continue _fun9521 }
case 427:
                r6 = r5;
                r5 = 0.9;
                r8 = r5 / r6;
case 444:
                r5 = _closure1_slot7;
                r11 = r5.VISUAL_DIMENSIONS;
                r6 = r11.every;
                r5 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r0
                    r1 = _closure2_slot9;
                    r0 = a0;
                    r1 = r1[r0];
                    r0 = 1e-06;
                    r0 = r1 < r0;
                    return r0;
                };
                r5 = r6.bind(r11)(r5);
                r6 = _closure1_slot7;
                r6 = r6.EMOTION_INTENSITY_THRESHOLDS;
                r6 = r6[r10];
                if(!(r9 == r6)) { _fun9521_ip = 498; continue _fun9521 }
case 488:
                r9 = _closure1_slot7;
                r6 = r9.INTENSITY_THRESHOLDS;
case 498:
                var _closure2_slot4 = r8;
                var _closure2_slot5 = r5;
                var _closure2_slot6 = r7;
                var _closure2_slot7 = r7;
                r2 = _closure1_slot3;
                r5 = r2.default;
                r2 = 2;
                r2 = r5.bind(r7)(r6, r2);
                r4 = r2[r4];
                _closure2_slot6 = r4;
                r2 = r2[r3];
                _closure2_slot7 = r2;
                r2 = r1;
                r1 = r2.map;
                r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r0
                    _fun9525: for(var _fun9525_ip = 0; ; ) switch(_fun9525_ip) {
case 0:
                        r0 = a0;
                        r7 = a1;
                        r2 = 'strong';
                        r4 = 'medium';
                        r1 = 'weak';
                        r5 = r0.name;
                        r9 = r0.before;
                        r8 = r0.after;
                        r0 = _closure2_slot4;
                        r11 = r9 * r0;
                        r12 = r8 * r0;
                        r9 = r12 - r11;
                        r0 = global;
                        r13 = r0.Math;
                        r8 = r13.round;
                        r10 = _closure2_slot8;
                        r10 = r10[r5];
                        r14 = null;
                        r6 = 0;
                        if(!(r14 != r10)) { _fun9525_ip = 86; continue _fun9525 }
case 83:
                        r6 = r10;
case 86:
                        r10 = 1000;
                        r6 = r10 * r6;
                        r6 = r8.bind(r13)(r6);
                        r8 = r6 / r10;
                        r6 = 3;
                        r6 = r7 < r6;
                        r14 = r0.Math;
                        r13 = r14.round;
                        r11 = r10 * r11;
                        r11 = r13.bind(r14)(r11);
                        r13 = r11 / r10;
                        r11 = new Array(2);
                        r11[0] = r13;
                        r14 = r0.Math;
                        r13 = r14.round;
                        r12 = r10 * r12;
                        r12 = r13.bind(r14)(r12);
                        r12 = r12 / r10;
                        r11[1] = r12;
                        r13 = r0.Math;
                        r12 = r13.round;
                        r0 = r10 * r9;
                        r0 = r12.bind(r13)(r0);
                        r10 = r0 / r10;
                        r0 = 1;
                        r7 = r7 + r0;
                        r0 = {};
                        r0['name'] = r5;
                        r0['value'] = r11;
                        r0['change'] = r10;
                        r0['fx_activation'] = r8;
                        r0['rank'] = r7;
                        r0['visual'] = r6;
                        if(!r6) { _fun9525_ip = 412; continue _fun9525 }
case 239:
                        r6 = _closure2_slot8;
                        r7 = r6[r5];
                        r6 = 1e-06;
                        if(!(!(r7 < r6))) { _fun9525_ip = 406; continue _fun9525 }
case 264:
                        r6 = _closure2_slot3;
                        if(r6) { _fun9525_ip = 353; continue _fun9525 }
case 271:
                        r6 = _closure2_slot5;
                        if(r6) { _fun9525_ip = 353; continue _fun9525 }
case 278:
                        r7 = _closure1_slot6;
                        r10 = r7.floatGte;
                        r7 = _closure2_slot7;
                        r8 = undefined;
                        r7 = r10.bind(r8)(r9, r7);
                        if(r7) { _fun9525_ip = 345; continue _fun9525 }
case 306:
                        r6 = _closure1_slot6;
                        r7 = r6.floatGte;
                        r6 = _closure2_slot6;
                        r6 = r7.bind(r8)(r9, r6);
                        if(r6) { _fun9525_ip = 337; continue _fun9525 }
case 329:
                        r0['intensity'] = r1;
                        _fun9525_ip = 412; continue _fun9525;
case 337:
                        r0['intensity'] = r4;
                        _fun9525_ip = 412; continue _fun9525;
case 345:
                        r0['intensity'] = r2;
                        _fun9525_ip = 412; continue _fun9525;
case 353:
                        r3 = _closure2_slot8;
                        r6 = r3[r5];
                        r3 = 0.5;
                        if(!(!(r6 >= r3))) { _fun9525_ip = 398; continue _fun9525 }
case 375:
                        r5 = 0.25;
                        r3 = r1;
                        if(!(r6 >= r5)) { _fun9525_ip = 395; continue _fun9525 }
case 392:
                        r3 = r4;
case 395:
                        r2 = r3;
case 398:
                        r0['intensity'] = r2;
                        _fun9525_ip = 412; continue _fun9525;
case 406:
                        r0['intensity'] = r1;
case 412:
                        return r0;
                    }
                };
                r0 = r1.bind(r2)(r0);
                return r0;
            }
        };
        var _closure1_slot18 = r0;
        r0 = 0;
        r3 = r5[r0];
        r0 = undefined;
        r6 = r4.bind(r0)(r3);
        r3 = global;
        r9 = r3.Object;
        r8 = r9.defineProperty;
        r7 = {};
        r3 = true;
        r7['value'] = r3;
        r3 = '__esModule';
        r3 = r8.bind(r9)(r2, r3, r7);
        r2['V1FourFactorStrategy'] = r0;
        r3 = 1;
        r3 = r5[r3];
        r3 = r4.bind(r0)(r3);
        r3 = r6.bind(r0)(r3);
        var _closure1_slot0 = r3;
        r3 = 2;
        r3 = r5[r3];
        r3 = r4.bind(r0)(r3);
        r3 = r6.bind(r0)(r3);
        var _closure1_slot1 = r3;
        r3 = 3;
        r3 = r5[r3];
        r3 = r4.bind(r0)(r3);
        r3 = r6.bind(r0)(r3);
        var _closure1_slot2 = r3;
        r3 = 4;
        r3 = r5[r3];
        r3 = r4.bind(r0)(r3);
        r3 = r6.bind(r0)(r3);
        var _closure1_slot3 = r3;
        r3 = 5;
        r3 = r5[r3];
        r3 = r4.bind(r0)(r3);
        var _closure1_slot4 = r3;
        r3 = 6;
        r3 = r5[r3];
        r3 = r4.bind(r0)(r3);
        var _closure1_slot5 = r3;
        r3 = 7;
        r3 = r5[r3];
        r3 = r4.bind(r0)(r3);
        var _closure1_slot6 = r3;
        r3 = 8;
        r3 = r5[r3];
        r3 = r4.bind(r0)(r3);
        var _closure1_slot7 = r3;
        r3 = 0.14285714285714285;
        var _closure1_slot8 = r3;
        r3 = {};
        r4 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: loudness, environment: r1
            _fun9526: for(var _fun9526_ip = 0; ; ) switch(_fun9526_ip) {
case 0:
                r9 = a0;
                r7 = 0;
                r3 = _closure1_slot13;
                r2 = undefined;
                r1 = 'eq';
                r1 = r3.bind(r2)(r9, r1);
                r4 = 0;
                if(!r1) { _fun9526_ip = 168; continue _fun9526 }
case 32:
                r6 = _closure1_slot11;
                r5 = _closure1_slot12;
                r1 = global;
                r8 = r1.Array;
                r1 = r8.prototype;
                r3 = Object.create(r1, {constructor: {value: r8}});
                r12 = 10;
                r13 = r3;
                r1 = originalConstruct(r8, [r12, r11]);
                r3 = r1 instanceof Object ? r1 : r3;
                r1 = r3.fill;
                r3 = r1.bind(r3)(r7);
                r1 = ['eq', 'eqs'];
                r5 = r5.bind(r2)(r9, r1, r3);
                r1 = r5.slice;
                r3 = 5;
                r8 = r1.bind(r5)(r7, r3);
                r5 = r8.reduce;
                r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
                    r1 = a0;
                    r0 = a1;
                    r0 = r1 + r0;
                    return r0;
                };
                r1 = r5.bind(r8)(r1, r7);
                r5 = r1 / r3;
                r3 = -12;
                r1 = 12;
                r3 = r6.bind(r2)(r5, r3, r1);
                r1 = 0.55;
                r1 = r1 * r3;
                r4 = r7 + r1;
case 168:
                r3 = _closure1_slot13;
                r1 = 'rvb';
                r3 = r3.bind(r2)(r9, r1);
                r1 = r4;
                if(!r3) { _fun9526_ip = 246; continue _fun9526 }
case 188:
                r6 = _closure1_slot11;
                r8 = _closure1_slot12;
                r3 = ['rvb', 'ol', 'rvb'];
                r5 = -96.3;
                r3 = r8.bind(r2)(r9, r3, r5);
                r5 = r6.bind(r2)(r3, r5, r7);
                r3 = 0.45;
                r3 = r3 * r5;
                r1 = r4 + r3;
case 246:
                r0 = _closure1_slot10;
                r0 = r0.bind(r2)(r1);
                return r0;
            }
        };
        r3['loudness'] = r4;
        r4 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: high_freq, environment: r1
            _fun9528: for(var _fun9528_ip = 0; ; ) switch(_fun9528_ip) {
case 0:
                r8 = a0;
                r6 = 0;
                r3 = _closure1_slot13;
                r2 = undefined;
                r1 = 'eq';
                r1 = r3.bind(r2)(r8, r1);
                r4 = 0;
                if(!r1) { _fun9528_ip = 216; continue _fun9528 }
case 32:
                r7 = _closure1_slot12;
                r1 = global;
                r1 = r1.Array;
                r3 = r1.prototype;
                r5 = Object.create(r3, {constructor: {value: r1}});
                r3 = 10;
                r15 = r5;
                r14 = r3;
                r1 = originalConstruct(r1, [r14, r13]);
                r5 = r1 instanceof Object ? r1 : r5;
                r1 = r5.fill;
                r5 = r1.bind(r5)(r6);
                r1 = ['eq', 'eqs'];
                r11 = r7.bind(r2)(r8, r1, r5);
                r1 = r11.length;
                r4 = 0;
                if(!(r1 >= r3)) { _fun9528_ip = 216; continue _fun9528 }
case 107:
                r10 = _closure1_slot11;
                r1 = 7;
                r1 = r11[r1];
                r9 = -12;
                r7 = 12;
                r3 = r10.bind(r2)(r1, r9, r7);
                r1 = 0.2;
                r1 = r1 * r3;
                r3 = 8;
                r3 = r11[r3];
                r5 = r10.bind(r2)(r3, r9, r7);
                r3 = 0.3;
                r5 = r3 * r5;
                r3 = 9;
                r3 = r11[r3];
                r7 = r10.bind(r2)(r3, r9, r7);
                r3 = 0.25;
                r3 = r3 * r7;
                r1 = r6 + r1;
                r1 = r1 + r5;
                r4 = r1 + r3;
case 216:
                r3 = _closure1_slot13;
                r1 = 'bt';
                r3 = r3.bind(r2)(r8, r1);
                r1 = r4;
                if(!r3) { _fun9528_ip = 293; continue _fun9528 }
case 236:
                r7 = _closure1_slot11;
                r5 = _closure1_slot12;
                r3 = ['bt', 'treble'];
                r6 = r5.bind(r2)(r8, r3, r6);
                r5 = -12;
                r3 = 12;
                r5 = r7.bind(r2)(r6, r5, r3);
                r3 = 0.25;
                r3 = r3 * r5;
                r1 = r4 + r3;
case 293:
                r0 = _closure1_slot10;
                r0 = r0.bind(r2)(r1);
                return r0;
            }
        };
        r3['high_freq'] = r4;
        r4 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: vocal, environment: r1
            _fun9529: for(var _fun9529_ip = 0; ; ) switch(_fun9529_ip) {
case 0:
                r8 = a0;
                r7 = 0;
                r3 = _closure1_slot13;
                r2 = undefined;
                r1 = 'eq';
                r1 = r3.bind(r2)(r8, r1);
                r4 = 0;
                if(!r1) { _fun9529_ip = 251; continue _fun9529 }
case 32:
                r6 = _closure1_slot12;
                r1 = global;
                r1 = r1.Array;
                r3 = r1.prototype;
                r5 = Object.create(r3, {constructor: {value: r1}});
                r3 = 10;
                r16 = r5;
                r15 = r3;
                r1 = originalConstruct(r1, [r15, r14]);
                r5 = r1 instanceof Object ? r1 : r5;
                r1 = r5.fill;
                r5 = r1.bind(r5)(r7);
                r1 = ['eq', 'eqs'];
                r12 = r6.bind(r2)(r8, r1, r5);
                r1 = r12.length;
                r4 = 0;
                if(!(r1 >= r3)) { _fun9529_ip = 251; continue _fun9529 }
case 110:
                r11 = _closure1_slot11;
                r1 = 4;
                r1 = r12[r1];
                r10 = -12;
                r9 = 12;
                r3 = r11.bind(r2)(r1, r10, r9);
                r1 = 0.22;
                r1 = r1 * r3;
                r3 = 5;
                r3 = r12[r3];
                r5 = r11.bind(r2)(r3, r10, r9);
                r3 = 0.26;
                r6 = r3 * r5;
                r3 = 6;
                r3 = r12[r3];
                r5 = r11.bind(r2)(r3, r10, r9);
                r3 = 0.18;
                r5 = r3 * r5;
                r3 = 7;
                r3 = r12[r3];
                r9 = r11.bind(r2)(r3, r10, r9);
                r3 = 0.15;
                r3 = r3 * r9;
                r1 = r7 + r1;
                r1 = r1 + r6;
                r1 = r1 + r5;
                r4 = r1 + r3;
case 251:
                r3 = _closure1_slot13;
                r1 = 'cmp';
                r3 = r3.bind(r2)(r8, r1);
                r1 = r4;
                if(!r3) { _fun9529_ip = 322; continue _fun9529 }
case 271:
                r6 = _closure1_slot11;
                r5 = _closure1_slot12;
                r3 = ['cmp', 'attack'];
                r5 = r5.bind(r2)(r8, r3, r7);
                r3 = 2;
                r5 = r6.bind(r2)(r5, r7, r3);
                r3 = 0.19;
                r3 = r3 * r5;
                r1 = r4 + r3;
case 322:
                r0 = _closure1_slot10;
                r0 = r0.bind(r2)(r1);
                return r0;
            }
        };
        r3['vocal'] = r4;
        r4 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: rhythm, environment: r1
            _fun9530: for(var _fun9530_ip = 0; ; ) switch(_fun9530_ip) {
case 0:
                r8 = a0;
                r7 = 0;
                r3 = _closure1_slot13;
                r2 = undefined;
                r1 = 'cmp';
                r1 = r3.bind(r2)(r8, r1);
                r3 = 0;
                if(!r1) { _fun9530_ip = 167; continue _fun9530 }
case 32:
                r10 = _closure1_slot11;
                r4 = _closure1_slot12;
                r1 = ['cmp', 'attack'];
                r1 = r4.bind(r2)(r8, r1, r7);
                r6 = 2;
                r1 = r10.bind(r2)(r1, r7, r6);
                r5 = 1;
                r9 = r5 - r1;
                r1 = ['cmp', 'release'];
                r1 = r4.bind(r2)(r8, r1, r7);
                r1 = r10.bind(r2)(r1, r7, r6);
                r6 = r5 - r1;
                r1 = ['cmp', 'ratio'];
                r4 = r4.bind(r2)(r8, r1, r5);
                r1 = 50;
                r4 = r10.bind(r2)(r4, r5, r1);
                r1 = 0.13;
                r4 = r1 * r4;
                r5 = 0.22;
                r5 = r5 * r9;
                r5 = r7 + r5;
                r1 = r1 * r6;
                r1 = r5 + r1;
                r3 = r1 + r4;
case 167:
                r4 = _closure1_slot13;
                r1 = 'limiter';
                r1 = r4.bind(r2)(r8, r1);
                r5 = r3;
                if(!r1) { _fun9530_ip = 262; continue _fun9530 }
case 187:
                r9 = _closure1_slot11;
                r4 = _closure1_slot12;
                r1 = ['limiter', 'attack'];
                r6 = 0.001;
                r4 = r4.bind(r2)(r8, r1, r6);
                r1 = 0.2;
                r4 = r9.bind(r2)(r4, r6, r1);
                r1 = 1;
                r4 = r1 - r4;
                r1 = 0.13;
                r1 = r1 * r4;
                r5 = r3 + r1;
case 262:
                r3 = _closure1_slot13;
                r1 = 'eq';
                r1 = r3.bind(r2)(r8, r1);
                r3 = r5;
                if(!r1) { _fun9530_ip = 476; continue _fun9530 }
case 285:
                r9 = _closure1_slot12;
                r1 = global;
                r1 = r1.Array;
                r4 = r1.prototype;
                r6 = Object.create(r4, {constructor: {value: r1}});
                r4 = 10;
                r17 = r6;
                r16 = r4;
                r1 = originalConstruct(r1, [r16, r15]);
                r6 = r1 instanceof Object ? r1 : r6;
                r1 = r6.fill;
                r6 = r1.bind(r6)(r7);
                r1 = ['eq', 'eqs'];
                r13 = r9.bind(r2)(r8, r1, r6);
                r1 = r13.length;
                r3 = r5;
                if(!(r1 >= r4)) { _fun9530_ip = 476; continue _fun9530 }
case 361:
                r10 = _closure1_slot11;
                r4 = r13[r7];
                r1 = 1;
                r1 = r13[r1];
                r4 = r4 + r1;
                r11 = 2;
                r1 = r13[r11];
                r1 = r4 + r1;
                r4 = 3;
                r1 = r1 / r4;
                r9 = -12;
                r6 = 12;
                r12 = r10.bind(r2)(r1, r9, r6);
                r1 = 0.17;
                r1 = r1 * r12;
                r12 = r13[r4];
                r4 = 4;
                r4 = r13[r4];
                r4 = r12 + r4;
                r4 = r4 / r11;
                r6 = r10.bind(r2)(r4, r9, r6);
                r4 = 0.1;
                r4 = r4 * r6;
                r1 = r5 + r1;
                r3 = r1 + r4;
case 476:
                r4 = _closure1_slot13;
                r1 = 'bt';
                r1 = r4.bind(r2)(r8, r1);
                r4 = r3;
                if(!r1) { _fun9530_ip = 553; continue _fun9530 }
case 496:
                r9 = _closure1_slot11;
                r5 = _closure1_slot12;
                r1 = ['bt', 'bass'];
                r6 = r5.bind(r2)(r8, r1, r7);
                r5 = -12;
                r1 = 12;
                r5 = r9.bind(r2)(r6, r5, r1);
                r1 = 0.08;
                r1 = r1 * r5;
                r4 = r3 + r1;
case 553:
                r3 = _closure1_slot13;
                r1 = 'dly';
                r3 = r3.bind(r2)(r8, r1);
                r1 = r4;
                if(!r3) { _fun9530_ip = 631; continue _fun9530 }
case 573:
                r6 = _closure1_slot11;
                r5 = _closure1_slot12;
                r3 = ['dly', 'feedback'];
                r5 = r5.bind(r2)(r8, r3, r7);
                r3 = 0.2;
                r5 = r6.bind(r2)(r5, r7, r3);
                r3 = 0.12;
                r3 = r3 * r5;
                r1 = r4 + r3;
case 631:
                r0 = _closure1_slot10;
                r0 = r0.bind(r2)(r1);
                return r0;
            }
        };
        r3['rhythm'] = r4;
        r4 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: stereo_width, environment: r1
            _fun9531: for(var _fun9531_ip = 0; ; ) switch(_fun9531_ip) {
case 0:
                r8 = a0;
                r7 = 0;
                r3 = _closure1_slot13;
                r2 = undefined;
                r1 = 'se';
                r1 = r3.bind(r2)(r8, r1);
                r4 = 0;
                if(!r1) { _fun9531_ip = 126; continue _fun9531 }
case 29:
                r9 = _closure1_slot11;
                r5 = _closure1_slot12;
                r1 = ['se', 'stereoizer'];
                r1 = r5.bind(r2)(r8, r1, r7);
                r6 = 10;
                r3 = r9.bind(r2)(r1, r7, r6);
                r1 = 0.4;
                r1 = r1 * r3;
                r3 = ['se', 'presence'];
                r5 = r5.bind(r2)(r8, r3, r7);
                r3 = -10;
                r5 = r9.bind(r2)(r5, r3, r6);
                r3 = 0.25;
                r3 = r3 * r5;
                r1 = r7 + r1;
                r4 = r1 + r3;
case 126:
                r3 = _closure1_slot13;
                r1 = 'rvb';
                r3 = r3.bind(r2)(r8, r1);
                r1 = r4;
                if(!r3) { _fun9531_ip = 197; continue _fun9531 }
case 146:
                r6 = _closure1_slot11;
                r5 = _closure1_slot12;
                r3 = ['rvb', 'rvb', 'swidth'];
                r5 = r5.bind(r2)(r8, r3, r7);
                r3 = 180;
                r5 = r6.bind(r2)(r5, r7, r3);
                r3 = 0.35;
                r3 = r3 * r5;
                r1 = r4 + r3;
case 197:
                r0 = _closure1_slot10;
                r0 = r0.bind(r2)(r1);
                return r0;
            }
        };
        r3['stereo_width'] = r4;
        r4 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: space_size, environment: r1
            _fun9532: for(var _fun9532_ip = 0; ; ) switch(_fun9532_ip) {
case 0:
                r8 = a0;
                r7 = 0;
                r3 = _closure1_slot13;
                r2 = undefined;
                r1 = 'rvb';
                r1 = r3.bind(r2)(r8, r1);
                r4 = 0;
                if(!r1) { _fun9532_ip = 182; continue _fun9532 }
case 32:
                r10 = _closure1_slot11;
                r11 = _closure1_slot12;
                r1 = ['rvb', 'rvb', 'dtime'];
                r3 = r11.bind(r2)(r8, r1, r7);
                r6 = 0.2;
                r1 = 10;
                r3 = r10.bind(r2)(r3, r6, r1);
                r1 = 0.35;
                r1 = r1 * r3;
                r3 = ['rvb', 'er', 'rsize'];
                r9 = r11.bind(r2)(r8, r3, r7);
                r5 = -100;
                r3 = 100;
                r5 = r10.bind(r2)(r9, r5, r3);
                r3 = 0.25;
                r5 = r3 * r5;
                r3 = ['rvb', 'ol', 'rvb'];
                r9 = -96.3;
                r3 = r11.bind(r2)(r8, r3, r9);
                r3 = r10.bind(r2)(r3, r9, r7);
                r3 = r6 * r3;
                r1 = r7 + r1;
                r1 = r1 + r5;
                r4 = r1 + r3;
case 182:
                r3 = _closure1_slot13;
                r1 = 'dly';
                r3 = r3.bind(r2)(r8, r1);
                r1 = r4;
                if(!r3) { _fun9532_ip = 256; continue _fun9532 }
case 202:
                r6 = _closure1_slot11;
                r5 = _closure1_slot12;
                r3 = ['dly', 'delay'];
                r5 = r5.bind(r2)(r8, r3, r7);
                r3 = 5000;
                r5 = r6.bind(r2)(r5, r7, r3);
                r3 = 0.2;
                r3 = r3 * r5;
                r1 = r4 + r3;
case 256:
                r0 = _closure1_slot10;
                r0 = r0.bind(r2)(r1);
                return r0;
            }
        };
        r3['space_size'] = r4;
        r4 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: richness, environment: r1
            _fun9533: for(var _fun9533_ip = 0; ; ) switch(_fun9533_ip) {
case 0:
                r7 = a0;
                r8 = 0;
                r3 = _closure1_slot13;
                r2 = undefined;
                r1 = 'chorus';
                r1 = r3.bind(r2)(r7, r1);
                r4 = 0;
                if(!r1) { _fun9533_ip = 209; continue _fun9533 }
case 32:
                r11 = _closure1_slot11;
                r9 = _closure1_slot12;
                r1 = ['chorus', 'mix'];
                r1 = r9.bind(r2)(r7, r1, r8);
                r5 = 1;
                r3 = r11.bind(r2)(r1, r8, r5);
                r1 = 0.22;
                r1 = r1 * r3;
                r3 = ['chorus', 'voices'];
                r6 = r9.bind(r2)(r7, r3, r5);
                r3 = 4;
                r6 = r11.bind(r2)(r6, r5, r3);
                r3 = 0.18;
                r6 = r3 * r6;
                r3 = ['chorus', 'depth'];
                r3 = r9.bind(r2)(r7, r3, r8);
                r3 = r11.bind(r2)(r3, r8, r5);
                r10 = 0.1;
                r5 = r10 * r3;
                r3 = ['chorus', 'rate'];
                r9 = r9.bind(r2)(r7, r3, r10);
                r3 = 5;
                r9 = r11.bind(r2)(r9, r10, r3);
                r3 = 0.08;
                r3 = r3 * r9;
                r1 = r8 + r1;
                r1 = r1 + r6;
                r1 = r1 + r5;
                r4 = r1 + r3;
case 209:
                r3 = _closure1_slot13;
                r1 = 'rvb';
                r1 = r3.bind(r2)(r7, r1);
                r5 = r4;
                if(!r1) { _fun9533_ip = 333; continue _fun9533 }
case 229:
                r10 = _closure1_slot11;
                r6 = _closure1_slot12;
                r1 = ['rvb', 'rvb', 'diffusion'];
                r3 = r6.bind(r2)(r7, r1, r8);
                r1 = 100;
                r3 = r10.bind(r2)(r3, r8, r1);
                r1 = 0.15;
                r1 = r1 * r3;
                r3 = ['rvb', 'rvb', 'dtime'];
                r9 = r6.bind(r2)(r7, r3, r8);
                r6 = 0.2;
                r3 = 10;
                r6 = r10.bind(r2)(r9, r6, r3);
                r3 = 0.12;
                r3 = r3 * r6;
                r1 = r4 + r1;
                r5 = r1 + r3;
case 333:
                r3 = _closure1_slot13;
                r1 = 'dly';
                r1 = r3.bind(r2)(r7, r1);
                r3 = r5;
                if(!r1) { _fun9533_ip = 457; continue _fun9533 }
case 353:
                r9 = _closure1_slot11;
                r6 = _closure1_slot12;
                r1 = ['dly', 'wet'];
                r4 = -40;
                r1 = r6.bind(r2)(r7, r1, r4);
                r4 = r9.bind(r2)(r1, r4, r8);
                r1 = 0.12;
                r1 = r1 * r4;
                r4 = ['dly', 'feedback'];
                r6 = r6.bind(r2)(r7, r4, r8);
                r4 = 0.2;
                r6 = r9.bind(r2)(r6, r8, r4);
                r4 = 0.08;
                r4 = r4 * r6;
                r1 = r5 + r1;
                r3 = r1 + r4;
case 457:
                r4 = _closure1_slot13;
                r1 = 'se';
                r1 = r4.bind(r2)(r7, r1);
                r4 = r3;
                if(!r1) { _fun9533_ip = 534; continue _fun9533 }
case 477:
                r9 = _closure1_slot11;
                r5 = _closure1_slot12;
                r1 = ['se', 'presence'];
                r6 = r5.bind(r2)(r7, r1, r8);
                r5 = -10;
                r1 = 10;
                r5 = r9.bind(r2)(r6, r5, r1);
                r1 = 0.1;
                r1 = r1 * r5;
                r4 = r3 + r1;
case 534:
                r3 = _closure1_slot13;
                r1 = 'eq';
                r3 = r3.bind(r2)(r7, r1);
                r1 = r4;
                if(!r3) { _fun9533_ip = 707; continue _fun9533 }
case 557:
                r6 = _closure1_slot12;
                r3 = global;
                r9 = r3.Array;
                r3 = r9.prototype;
                r5 = Object.create(r3, {constructor: {value: r9}});
                r14 = 10;
                r15 = r5;
                r3 = originalConstruct(r9, [r14, r13]);
                r5 = r3 instanceof Object ? r3 : r5;
                r3 = r5.fill;
                r5 = r3.bind(r5)(r8);
                r3 = ['eq', 'eqs'];
                r6 = r6.bind(r2)(r7, r3, r5);
                r5 = r6.length;
                r3 = 7;
                r1 = r4;
                if(!(r5 >= r3)) { _fun9533_ip = 707; continue _fun9533 }
case 633:
                r7 = _closure1_slot11;
                r3 = 4;
                r5 = r6[r3];
                r3 = 5;
                r3 = r6[r3];
                r5 = r5 + r3;
                r3 = 6;
                r3 = r6[r3];
                r5 = r5 + r3;
                r3 = 3;
                r6 = r5 / r3;
                r5 = -12;
                r3 = 12;
                r5 = r7.bind(r2)(r6, r5, r3);
                r3 = 0.13;
                r3 = r3 * r5;
                r1 = r4 + r3;
case 707:
                r0 = _closure1_slot10;
                r0 = r0.bind(r2)(r1);
                return r0;
            }
        };
        r3['richness'] = r4;
        var _closure1_slot9 = r3;
        r1 = function() {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
            r0 = _closure1_slot1;
            r3 = r0.default;
            r2 = function() {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: r, environment: r1
                r2 = this;
                r0 = _closure1_slot0;
                r3 = r0.default;
                r1 = _closure2_slot0;
                r0 = undefined;
                r1 = r3.bind(r0)(r2, r1);
                r1 = 'emofx-visual';
                r2['name'] = r1;
                return r0;
            };
            var _closure2_slot0 = r2;
            r0 = {};
            r4 = 'compute';
            r0['key'] = r4;
            r1 = function(a0, a1, a2) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: value, environment: r1
                _fun9536: for(var _fun9536_ip = 0; ; ) switch(_fun9536_ip) {
case 0:
                    r3 = a0;
                    r4 = a1;
                    r7 = a2;
                    r1 = undefined;
                    if(r4) { _fun9536_ip = 29; continue _fun9536 }
case 17:
                    r2 = _closure1_slot5;
                    r2 = r2.EMPTY_VISUAL_EXTRA;
                    return r2;
case 29:
                    r2 = _closure1_slot14;
                    r17 = r2.bind(r1)(r4);
                    r4 = _closure1_slot15;
                    r2 = _closure1_slot16;
                    r2 = r2.bind(r1)(r3);
                    r16 = r4.bind(r1)(r2);
                    r9 = _closure1_slot18;
                    r19 = r3.emotion_label;
                    r18 = r3.family;
                    r5 = 0.25;
                    r14 = 0.15;
                    r13 = 0.35;
                    r20 = undefined;
                    r15 = r5;
                    r12 = r5;
                    r2 = r9.call(r20, r19, r18, r17, r16, r15, r14, r13, r12, r11);
                    r6 = r7.debug;
                    r9 = r3.emotion_label;
                    r8 = r3.family;
                    r4 = 0;
                    r5 = r2[r4];
                    r10 = null;
                    r4 = undefined;
                    if(!(r10 != r5)) { _fun9536_ip = 151; continue _fun9536 }
case 146:
                    r4 = r5.name;
case 151:
                    r5 = {};
                    r5['emotion'] = r9;
                    r5['family'] = r8;
                    r5['topDimension'] = r4;
                    r4 = 'visual:result';
                    r4 = r6.bind(r7)(r4, r5);
                    r0 = _closure1_slot4;
                    r0 = r0.resolveVisualCopywriting;
                    r1 = r0.bind(r1)(r3, r2);
                    r0 = {};
                    r0['dimensions'] = r2;
                    r0['copywriting'] = r1;
                    return r0;
                }
            };
            r0['value'] = r1;
            r1 = new Array(1);
            r1[0] = r0;
            r0 = undefined;
            r0 = r3.bind(r0)(r2, r1);
            return r0;
        };
        r1 = r1.bind(r0)();
        r2['V1FourFactorStrategy'] = r1;
        return r0;
    };
factories[965]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r4 = a1;
        r6 = a5;
        r5 = a6;
        r0 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: s, environment: r2
            _fun9538: for(var _fun9538_ip = 0; ; ) switch(_fun9538_ip) {
case 0:
                r3 = a0;
                r2 = r3.length;
                r1 = 2;
                r0 = null;
                if(!(!(r2 < r1))) { _fun9538_ip = 52; continue _fun9538 }
case 17:
                r1 = 0;
                r1 = r3[r1];
                r2 = r1.name;
                r1 = '+';
                r2 = r2 + r1;
                r1 = 1;
                r1 = r3[r1];
                r1 = r1.name;
                r0 = r2 + r1;
case 52:
                return r0;
            }
        };
        var _closure1_slot4 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: f, environment: r2
            _fun9539: for(var _fun9539_ip = 0; ; ) switch(_fun9539_ip) {
case 0:
                r3 = a0;
                r2 = a1;
                r1 = null;
                r0 = r3.dimPairs;
                r0 = r0[r2];
                r6 = 'string';
                r5 = typeof r0;
                if(!(r6 !== r5)) { _fun9539_ip = 173; continue _fun9539 }
case 34:
                var _closure2_slot0 = r2;
                r2 = global;
                r7 = r2.Object;
                r6 = r7.keys;
                r5 = r3.dimPairs;
                r7 = r6.bind(r7)(r5);
                r6 = r7.filter;
                r5 = function(a0) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r4
                    _fun9540: for(var _fun9540_ip = 0; ; ) switch(_fun9540_ip) {
case 0:
                        r3 = a0;
                        r0 = _closure2_slot0;
                        r0 = r3 === r0;
                        if(r0) { _fun9540_ip = 40; continue _fun9540 }
case 17:
                        r2 = r3.startsWith;
                        r4 = _closure2_slot0;
                        r1 = '_';
                        r1 = r4 + r1;
                        r0 = r2.bind(r3)(r1);
case 40:
                        return r0;
                    }
                };
                r6 = r6.bind(r7)(r5);
                r5 = r6.sort;
                r4 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r4
                    r2 = a0;
                    r1 = r2.localeCompare;
                    r0 = a1;
                    r0 = r1.bind(r2)(r0);
                    return r0;
                };
                r4 = r5.bind(r6)(r4);
                r6 = r4.length;
                r5 = 0;
                if(!(r5 !== r6)) { _fun9539_ip = 171; continue _fun9539 }
case 104:
                r6 = r2.Math;
                r5 = r6.floor;
                r7 = r2.Math;
                r2 = r7.random;
                r7 = r2.bind(r7)();
                r2 = r4.length;
                r2 = r7 * r2;
                r2 = r5.bind(r6)(r2);
                r3 = r3.dimPairs;
                r2 = r4[r2];
                r3 = r3[r2];
                r2 = null;
                if(!(r2 != r3)) { _fun9539_ip = 169; continue _fun9539 }
case 166:
                r2 = r3;
case 169:
                return r2;
case 171:
                return r1;
case 173:
                return r0;
            }
        };
        var _closure1_slot5 = r0;
        r0 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Original name: v, environment: r2
            _fun9542: for(var _fun9542_ip = 0; ; ) switch(_fun9542_ip) {
case 0:
                r5 = a0;
                r1 = a1;
                r2 = _closure1_slot5;
                r4 = undefined;
                r2 = r2.bind(r4)(r5, r1);
                if(r2) { _fun9542_ip = 111; continue _fun9542 }
case 24:
                r3 = r1.split;
                r6 = '+';
                r7 = r3.bind(r1)(r6);
                r8 = r7.length;
                r3 = 2;
                if(!(r3 === r8)) { _fun9542_ip = 84; continue _fun9542 }
case 50:
                r3 = 1;
                r3 = r7[r3];
                r6 = r3 + r6;
                r3 = 0;
                r3 = r7[r3];
                r3 = r6 + r3;
                r0 = _closure1_slot5;
                r4 = r0.bind(r4)(r5, r3);
                if(r4) { _fun9542_ip = 88; continue _fun9542 }
case 84:
                r0 = null;
                return r0;
case 88:
                r0 = {};
                r0['description'] = r4;
                r0['matchedKey'] = r3;
                r3 = 'matched_reversed';
                r0['matchLevel'] = r3;
                return r0;
case 111:
                r0 = {};
                r0['description'] = r2;
                r0['matchedKey'] = r1;
                r1 = 'matched_rank_order';
                r0['matchLevel'] = r1;
                return r0;
            }
        };
        var _closure1_slot6 = r0;
        r0 = 0;
        r1 = r5[r0];
        r0 = undefined;
        r3 = r4.bind(r0)(r1);
        r1 = global;
        r9 = r1.Object;
        r8 = r9.defineProperty;
        r7 = {};
        r1 = true;
        r7['value'] = r1;
        r1 = '__esModule';
        r1 = r8.bind(r9)(r6, r1, r7);
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r2
            _fun9543: for(var _fun9543_ip = 0; ; ) switch(_fun9543_ip) {
case 0:
                r0 = a0;
                r6 = r0.emotion_label;
                r2 = _closure1_slot4;
                r4 = undefined;
                r1 = a1;
                r2 = r2.bind(r4)(r1);
                r1 = _closure1_slot0;
                r1 = r1.isEmotionLabel;
                r1 = r1.bind(r4)(r6);
                if(!r1) { _fun9543_ip = 83; continue _fun9543 }
case 44:
                r1 = global;
                r1 = r1.Object;
                r1 = r1.prototype;
                r5 = r1.hasOwnProperty;
                r3 = r5.call;
                r1 = _closure1_slot2;
                r1 = r3.bind(r5)(r1, r6);
                if(!r1) { _fun9543_ip = 83; continue _fun9543 }
case 80:
                if(r2) { _fun9543_ip = 155; continue _fun9543 }
case 83:
                r1 = _closure1_slot3;
                r7 = r1.keyword;
                r1 = _closure1_slot3;
                r5 = r1.description;
                r1 = null;
                r3 = r2;
                if(!(r1 == r3)) { _fun9543_ip = 122; continue _fun9543 }
case 112:
                r1 = _closure1_slot1;
                r3 = r1.FALLBACK_DESCRIPTION_KEY;
case 122:
                r1 = {};
                r1['emotion'] = r6;
                r1['keyword'] = r7;
                r1['description'] = r5;
                r1['descriptionKey'] = r3;
                r3 = 'fallback';
                r1['matchLevel'] = r3;
                return r1;
case 155:
                r1 = _closure1_slot2;
                r3 = r1[r6];
                r1 = _closure1_slot6;
                r1 = r1.bind(r4)(r3, r2);
                r5 = r3.keyword;
                if(r1) { _fun9543_ip = 225; continue _fun9543 }
case 182:
                r0 = _closure1_slot3;
                r3 = r0.description;
                r0 = {};
                r0['emotion'] = r6;
                r0['keyword'] = r5;
                r0['description'] = r3;
                r0['descriptionKey'] = r2;
                r2 = 'emotion_only';
                r0['matchLevel'] = r2;
                _fun9543_ip = 273; continue _fun9543;
case 225:
                r4 = r1.description;
                r3 = r1.matchedKey;
                r2 = r1.matchLevel;
                r1 = {};
                r1['emotion'] = r6;
                r1['keyword'] = r5;
                r1['description'] = r4;
                r1['descriptionKey'] = r3;
                r1['matchLevel'] = r2;
                r0 = r1;
case 273:
                return r0;
            }
        };
        r6['resolveVisualCopywriting'] = r1;
        r1 = 1;
        r1 = r5[r1];
        r1 = r4.bind(r0)(r1);
        var _closure1_slot0 = r1;
        r1 = 2;
        r1 = r5[r1];
        r1 = r4.bind(r0)(r1);
        var _closure1_slot1 = r1;
        r1 = 3;
        r1 = r5[r1];
        r1 = r4.bind(r0)(r1);
        r1 = r3.bind(r0)(r1);
        r3 = r1.default;
        var _closure1_slot2 = r3;
        r1 = r1.default;
        r1 = r1.fallback;
        var _closure1_slot3 = r1;
        return r0;
    };
factories[966]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r2 = a5;
        r0 = global;
        r4 = r0.Object;
        r3 = r4.defineProperty;
        r1 = {};
        r0 = true;
        r1['value'] = r0;
        r0 = '__esModule';
        r0 = r3.bind(r4)(r2, r0, r1);
        r0 = undefined;
        r2['DEFAULT_COPYWRITING'] = r0;
        r2['EMPTY_VISUAL_EXTRA'] = r0;
        r2['FALLBACK_DESCRIPTION_KEY'] = r0;
        r3 = a6;
        r1 = 0;
        r3 = r3[r1];
        r1 = a1;
        r1 = r1.bind(r0)(r3);
        r3 = 'fallback';
        r2['FALLBACK_DESCRIPTION_KEY'] = r3;
        r1 = r1.DEFAULT_EMOTION;
        r3 = {'emotion': null, 'keyword': 'Mỗi tâm trạng đều có tiếng vọng', 'description': 'Nhạc đang phát, từng chi tiết âm thanh đều được thể hiện cho bạn', 'descriptionKey': 'fallback', 'matchLevel': 'fallback'};
        r3['emotion'] = r1;
        r2['DEFAULT_COPYWRITING'] = r3;
        r1 = {'name': 'stereo_width', 'value': null, 'change': 0.199, 'fx_activation': 0.357, 'rank': 1, 'visual': true, 'intensity': 'medium'};
        r4 = [0.455, 0.655];
        r1['value'] = r4;
        r4 = new Array(6);
        r4[0] = r1;
        r1 = {'name': 'loudness', 'value': null, 'change': 0.183, 'fx_activation': 0.647, 'rank': 2, 'visual': true, 'intensity': 'medium'};
        r5 = [0.445, 0.628];
        r1['value'] = r5;
        r4[1] = r1;
        r1 = {'name': 'rhythm', 'value': null, 'change': 0.183, 'fx_activation': 0.245, 'rank': 3, 'visual': true, 'intensity': 'medium'};
        r5 = [0.442, 0.625];
        r1['value'] = r5;
        r4[2] = r1;
        r1 = {'name': 'high_freq', 'value': null, 'change': 0.177, 'fx_activation': 0.408, 'rank': 4, 'visual': false};
        r5 = [0.402, 0.579];
        r1['value'] = r5;
        r4[3] = r1;
        r1 = {'name': 'space_size', 'value': null, 'change': 0.164, 'fx_activation': 0.32, 'rank': 5, 'visual': false};
        r5 = [0.418, 0.582];
        r1['value'] = r5;
        r4[4] = r1;
        r1 = {'name': 'vocal', 'value': null, 'change': 0.11, 'fx_activation': 0.425, 'rank': 6, 'visual': false};
        r5 = [0.743, 0.853];
        r1['value'] = r5;
        r4[5] = r1;
        r1 = {};
        r1['dimensions'] = r4;
        r1['copywriting'] = r3;
        r2['EMPTY_VISUAL_EXTRA'] = r1;
        return r0;
    };
factories[967]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r16 = {};
        r0 = 'Cuồng nhiệt là nhịp điệu disco';
        r16['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm dày đan xen âm cao trong trẻo, độ phân giải rõ nét tạo nên âm sắc đầy sức căng, tận hưởng trọn vẹn không khí cuồng nhiệt của disco', 'loudness+richness': 'Hòa âm dày dặn kết hợp âm lượng mạnh mẽ, áp lực âm thanh đầy uy lực tạo cộng hưởng trong lồng ngực, khiến cơ thể đung đưa theo', 'rhythm+richness': 'Hòa âm phong phú cuốn theo nhịp điệu disco, phản hồi tức thời gọn gàng gõ vào lòng, giải phóng sức mạnh cuồng nhiệt', 'richness+space_size': 'Hòa âm đa lớp lấp đầy không gian vang rộng mở, chiều sâu âm học đầy đặn khiến bầu không khí cuồng nhiệt lan tỏa khắp sàn nhảy', 'richness+stereo_width': 'Trường âm thanh nổi rộng đưa hòa âm dày dặn lan sang hai bên, hình ảnh âm thanh căng tràn, để đôi tai tận hưởng trọn vẹn bữa tiệc', 'richness+vocal_1': 'Giọng hát giàu chất cảm hòa quyện với các dải hòa âm đa lớp, khả năng định vị chắc chắn khiến không khí disco cuồng nhiệt đầy sức lan tỏa', 'richness+vocal_2': 'Hòa âm dày dặn nâng đỡ vững vàng giọng hát chủ đạo, cộng hưởng trung âm đầy đặn mang đến sức căng tột độ, niềm vui tự nhiên lan tỏa', 'richness+vocal_3': 'Các lớp hòa âm ôm trọn giọng hát giàu chất cảm, sự hòa quyện dải tần xuất sắc đưa cảm xúc cuồng nhiệt thẳng đến trái tim', 'richness+vocal_4': 'Hòa âm chắc chắn cộng hưởng âm học với giọng hát, sức mạnh chồng lớp, bầu không khí tiệc tùng sôi động bùng cháy tức thì', 'richness+vocal_5': 'Hòa âm dâng trào bao quanh giọng hát giàu chất cảm, nền âm thanh dày dặn nâng đỡ trọn vẹn niềm vui và sự cuồng nhiệt', 'high_freq+loudness_1': 'Âm cao trong trẻo kết hợp âm lượng đầy đặn, áp lực âm chắc chắn khiến sóng âm dâng cao, tâm trạng cuồng nhiệt bùng cháy tức thì', 'high_freq+loudness_2': 'Âm bội cao rõ nét kết hợp dải động âm lượng ổn định, không khí disco cuồng nhiệt bung tỏa qua áp lực âm tinh tế', 'high_freq+rhythm_1': 'Âm cao thông thoáng bám sát nhịp điệu gọn gàng, phản hồi tức thời vui tươi vang rõ từng âm, khiến cơ thể tự động nhảy múa', 'high_freq+rhythm_2': 'Âm cao sáng bật lên theo nhịp trống mạnh mẽ, bắt nhịp chính xác đẩy sóng âm lên cao, cảm xúc cuồng nhiệt liên tục dâng trào', 'high_freq+rhythm_3': 'Âm cao trong trẻo điểm xuyết nhịp mạnh, phản hồi động rõ ràng truyền tải nhiệt huyết chính xác, giác quan cũng sôi sục theo', 'high_freq+space_size': 'Âm cao nổi bật xuyên qua tiếng vang không gian thoáng rộng, âm bội tự nhiên lan tỏa khắp nơi, tạo nên sàn nhảy disco toàn cảnh', 'high_freq+stereo_width_1': 'Âm cao trong trẻo lướt nhanh qua trường âm thanh nổi, độ kéo giãn hình ảnh âm thanh vừa phải khiến sức căng cuồng nhiệt thêm mãnh liệt', 'high_freq+stereo_width_2': 'Âm cao sạch sẽ lan tỏa trong trường âm thanh nổi rộng, kiểm soát pha ổn định khiến nốt nhạc lung linh vờn quanh đôi tai', 'high_freq+vocal_1': 'Độ phân giải âm cao giúp giọng hát xuyên thấu hơn, khả năng định vị trong trẻo đưa cảm xúc vượt khỏi giới hạn thính giác', 'high_freq+vocal_2': 'Tăng âm cao làm giọng hát thêm xuyên thấu, giọng ca sáng rõ kết hợp dải tần sạch sẽ, từng câu đều tràn đầy sức căng cuồng nhiệt', 'high_freq+vocal_3': 'Giọng hát ở dải cao thông thoáng, năng lượng âm thanh đầy đặn khiến niềm vui bùng nổ theo sóng âm, sự hân hoan lan tỏa trong không khí', 'high_freq+vocal_4': 'Âm cao rõ nét đồng hành cùng giọng hát chủ đạo đến cao trào, nốt cao ngân tự nhiên khiến không khí cuồng nhiệt nóng dần theo giai điệu', 'high_freq+vocal_5': 'Giọng hát ở dải cao tinh tế thể hiện chất âm rõ nét, sức mạnh âm thanh vững chắc tự nhiên bộc lộ, đánh thức những tế bào cuồng nhiệt', 'loudness+rhythm_1': 'Âm lượng đầy đặn thúc đẩy nhịp điệu disco, áp lực âm chắc chắn gõ vào giác quan, cảm xúc cuồng nhiệt tăng dần', 'loudness+rhythm_2': 'Âm lượng dồi dào đẩy sức căng nhịp điệu lên cao, âm trầm sâu mạnh mẽ tạo cộng hưởng trong lồng ngực, khiến cơ thể đung đưa theo', 'loudness+space_size': 'Âm lượng chắc chắn lấp đầy tiếng vang không gian rộng mở, sóng âm rung động và lan tỏa tự nhiên, khí thế cuồng nhiệt bao trùm xung quanh', 'loudness+stereo_width': 'Âm lượng dồi dào trải khắp trường âm thanh nổi rộng lớn, sóng âm vòm toàn cảnh khuếch đại cảm xúc cuồng nhiệt dâng trào vừa đủ', 'loudness+vocal_1': 'Giọng hát chủ đạo cất tiếng theo âm lượng đầy đặn, mức áp suất âm hợp lý mang đến sức mạnh vững chắc, khuấy động cả khán phòng', 'loudness+vocal_2': 'Giọng hát giàu chất cảm kết hợp mức tăng âm lượng ổn định, chất giọng dày dặn đầy sức xuyên thấu, truyền tải trọn vẹn không khí cuồng nhiệt', 'loudness+vocal_3': 'Âm lượng dồi dào mang đến sức bùng nổ vững chắc cho giọng hát, hiệu ứng cộng hưởng lồng ngực khiến giai điệu tràn đầy tinh thần tiệc tùng nóng bỏng', 'rhythm+space_size': 'Nhịp trống mạnh mẽ lấp đầy tiếng vang không gian rộng lớn, chiều sâu âm học rõ ràng khiến cảm xúc cuồng nhiệt tự nhiên tuôn trào', 'rhythm+stereo_width': 'Nhịp điệu dồn dập luân phiên trái phải trong trường âm thanh nổi, tiết tấu rõ ràng cuốn đi mọi hướng, cơ thể bị nhịp phách điều khiển', 'space_size+stereo_width': 'Tiếng vang không gian rộng mở kết hợp trường âm thanh nổi toàn cảnh, sóng âm tự nhiên cuốn đi mọi hướng, tạo nên khí thế âm học đầy đặn', 'space_size+vocal_1': 'Giọng hát giàu chất cảm vang vọng trong tiếng vang không gian rộng, âm ngân hùng hậu lan tỏa tự nhiên, đẩy bầu không khí cuồng nhiệt lên cao', 'space_size+vocal_2': 'Giọng hát xuyên thấu cất sức trong tiếng vang không gian, cộng hưởng âm học tuôn chảy ổn định, cảm xúc tiệc tùng mãnh liệt lan tỏa khắp nơi', 'stereo_width+vocal_1': 'Giọng hát giàu chất cảm ngân vang trong trường âm thanh nổi rộng, định vị hình ảnh âm thanh chính xác tạo tác động thính giác sống động hơn', 'stereo_width+vocal_2': 'Giọng hát chủ đạo trải rộng trong trường âm thanh nổi rộng lớn, cộng hưởng vòm đầy đặn truyền tải trọn vẹn nhiệt huyết tiệc tùng', 'stereo_width+vocal_3': 'Trường âm thanh nổi rộng nâng đỡ vững vàng giọng hát đầy đặn, áp lực âm lan tỏa đa chiều, khiến sóng âm cuồng nhiệt ập đến', 'rhythm+vocal': 'Nhịp trống dồn dập đối đáp với giọng hát nóng bỏng, tiết tấu cuốn giọng ca lên đỉnh cao, cơ thể sinh ra để cuồng nhiệt'};
        r16['dimPairs'] = r0;
        r15 = {};
        r0 = 'Nhiệt huyết là sóng âm cuộn trào';
        r15['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm đầy đặn đan xen âm cao trong trẻo, độ phân giải rõ nét tạo nên âm sắc đầy sức căng, nhiệt huyết trào dâng theo giai điệu', 'loudness+richness': 'Hòa âm dày dặn kết hợp âm lượng chắc chắn, áp lực âm ổn định và mạnh mẽ tạo cộng hưởng trong lồng ngực, cảm xúc cũng dâng cao', 'rhythm+richness': 'Hòa âm phong phú cuốn theo nhịp điệu lên xuống, phản hồi tức thời sạch gọn gõ vào lòng, giải phóng sức mạnh kiên định', 'richness+space_size': 'Hòa âm đa lớp lấp đầy tiếng vang không gian rộng, chiều sâu âm học đầy đặn khiến cảm xúc nhiệt huyết vững vàng dâng lên từ đáy lòng', 'richness+stereo_width': 'Trường âm thanh nổi rộng đưa hòa âm dày dặn sang hai bên, hình ảnh âm thanh phân bố đều, đôi tai tràn đầy sức căng nhiệt huyết', 'richness+vocal_1': 'Giọng hát giàu chất cảm hòa quyện với các dải hòa âm đa lớp, khả năng định vị chắc chắn khiến từng câu hô vang đều tràn đầy sức mạnh hướng lên', 'richness+vocal_2': 'Hòa âm dày dặn nâng đỡ vững vàng giọng hát chủ đạo, cộng hưởng trung âm đầy đặn khiến nhiệt huyết bùng cháy trong giai điệu kiên định', 'richness+vocal_3': 'Các lớp hòa âm ôm trọn giọng hát giàu chất cảm, sự hòa quyện dải tần xuất sắc đưa sức căng cảm xúc điệp khúc thẳng đến trái tim', 'richness+vocal_4': 'Hòa âm chắc chắn cộng hưởng âm học với giọng hát chủ đạo, sức mạnh chồng lớp, cảm xúc nhiệt huyết tự nhiên tuôn trào', 'richness+vocal_5': 'Hòa âm dâng trào bao quanh giọng hát giàu chất cảm, nền âm thanh dày dặn thổi bùng nhiệt huyết và ý chí trong lòng', 'high_freq+loudness_1': 'Âm cao trong trẻo kết hợp âm lượng đầy đặn, áp lực âm chắc chắn khiến sóng âm dâng cao, tâm trạng được nguồn sức mạnh này thắp lên', 'high_freq+loudness_2': 'Âm bội cao rõ nét kết hợp dải động âm lượng ổn định, nhiệt huyết thể hiện sức mạnh kiên định qua áp lực âm tinh tế', 'high_freq+rhythm_1': 'Âm cao thông thoáng bám sát nhịp điệu gọn gàng, phản hồi tức thời vui tươi vang rõ từng âm, khiến cảm xúc nóng dần', 'high_freq+rhythm_2': 'Âm cao sáng bật lên theo nhịp điệu mạnh mẽ, bắt nhịp chính xác đẩy sóng âm lên cao, cảm xúc nhiệt huyết liên tục dâng trào', 'high_freq+rhythm_3': 'Âm cao trong trẻo điểm xuyết nhịp mạnh, phản hồi động rõ ràng truyền tải nhiệt huyết chính xác, giác quan cũng nóng lên', 'high_freq+space_size': 'Âm cao nổi bật xuyên qua tiếng vang không gian thoáng rộng, âm bội tự nhiên lan tỏa khắp nơi, bầu không khí âm học hào hùng được đẩy lên cao', 'high_freq+stereo_width_1': 'Âm cao trong trẻo lướt qua trường âm thanh nổi, độ kéo giãn hình ảnh âm thanh vừa phải khiến sức căng cảm xúc thêm mãnh liệt', 'high_freq+stereo_width_2': 'Âm cao sạch sẽ lan tỏa trong trường âm thanh nổi rộng, kiểm soát pha ổn định thể hiện sắc bén sự sôi sục qua đôi tai', 'high_freq+vocal_1': 'Độ phân giải âm cao giúp giọng hát xuyên thấu hơn, khả năng định vị trong trẻo đưa cảm xúc vượt khỏi giới hạn thính giác', 'high_freq+vocal_2': 'Tăng âm cao làm giọng hát thêm xuyên thấu, giọng ca sáng rõ kết hợp dải tần sạch sẽ, từng câu đều tràn đầy sức căng chân thực', 'high_freq+vocal_3': 'Giọng hát ở dải cao thông thoáng, năng lượng âm thanh đầy đặn khiến nhiệt huyết bùng nổ theo sóng âm, đam mê lan tỏa trong không khí', 'high_freq+vocal_4': 'Âm cao rõ nét đồng hành cùng giọng hát chủ đạo đến cao trào, nốt cao ngân tự nhiên khiến nhiệt huyết nóng dần theo giai điệu', 'high_freq+vocal_5': 'Giọng hát dải cao thể hiện chất âm rõ nét, sức mạnh âm thanh vững chắc tự nhiên bộc lộ, đánh thức tình yêu từ đáy lòng', 'loudness+rhythm_1': 'Âm lượng đầy đặn thúc đẩy nhịp điệu, áp lực âm chắc chắn tạo nên những nhịp mạnh gõ vào giác quan, cảm xúc tăng dần ổn định', 'loudness+rhythm_2': 'Âm lượng dồi dào đẩy sức căng nhịp điệu lên cao, âm trầm sâu mạnh mẽ tạo cộng hưởng trong lồng ngực, nhiệt huyết cũng sôi sục', 'loudness+space_size': 'Âm lượng chắc chắn lấp đầy tiếng vang không gian rộng mở, sóng âm rung động và lan tỏa tự nhiên, khí thế đầy đặn bao trùm bốn phía', 'loudness+stereo_width': 'Âm lượng dồi dào trải khắp trường âm thanh nổi rộng lớn, sóng âm vòm truyền tải và khuếch đại cảm xúc dâng trào vừa đủ', 'loudness+vocal_1': 'Giọng hát chủ đạo cất tiếng theo âm lượng đầy đặn, mức áp suất âm hợp lý mang đến sức mạnh vững chắc, từng câu vang dội mạnh mẽ', 'loudness+vocal_2': 'Giọng hát giàu chất cảm kết hợp mức tăng âm lượng ổn định, chất giọng dày dặn đầy sức xuyên thấu, truyền tải trọn vẹn nhiệt huyết', 'loudness+vocal_3': 'Âm lượng dồi dào mang đến sức bùng nổ vững chắc cho giọng hát, hiệu ứng cộng hưởng lồng ngực khiến ca từ tràn đầy niềm tin nóng bỏng', 'rhythm+space_size': 'Nhịp trống mạnh mẽ lấp đầy tiếng vang không gian rộng lớn, chiều sâu âm học rõ ràng khiến cảm xúc phấn chấn tự nhiên tuôn trào', 'rhythm+stereo_width': 'Nhịp điệu dồn dập luân phiên trái phải trong trường âm thanh nổi, tiết tấu rõ ràng cuốn đi mọi hướng, đánh thức các giác quan thính giác', 'rhythm+vocal': 'Giọng hát giàu chất cảm cất tiếng theo sát nhịp trống, âm sắc tức thời gọn gàng hòa quyện giọng ca với tiết tấu, đẩy cảm xúc hào hùng lên cao', 'space_size+stereo_width': 'Tiếng vang không gian rộng mở kết hợp trường âm thanh nổi toàn cảnh, sóng âm tự nhiên cuốn đi mọi hướng, tạo nên khí thế âm học đầy đặn', 'space_size+vocal_1': 'Giọng hát giàu chất cảm vang vọng trong tiếng vang không gian rộng, âm ngân hùng hậu lan tỏa tự nhiên, đẩy bầu không khí hào hùng lên cao', 'space_size+vocal_2': 'Giọng hát xuyên thấu cất sức trong tiếng vang không gian, cộng hưởng âm học tuôn chảy ổn định, cảm xúc mãnh liệt lan tỏa khắp nơi', 'stereo_width+vocal_1': 'Giọng hát giàu chất cảm ngân vang trong trường âm thanh nổi rộng, định vị hình ảnh âm thanh chính xác khiến tiếng hô vang thêm sống động', 'stereo_width+vocal_2': 'Giọng hát chủ đạo trải rộng trong trường âm thanh nổi rộng lớn, cộng hưởng vòm đầy đặn truyền tải trọn vẹn nhiệt huyết', 'stereo_width+vocal_3': 'Trường âm thanh nổi rộng nâng đỡ vững vàng giọng hát đầy đặn, áp lực âm lan tỏa đa chiều, khiến sóng âm nóng bỏng ập đến'};
        r15['dimPairs'] = r0;
        r14 = {};
        r0 = 'Sức sống là ánh sáng hướng lên';
        r14['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm tươi vui đan xen âm cao trong trẻo, độ phân giải cao mang đến cảm giác linh hoạt, cơ thể đung đưa theo những làn sóng âm nhẹ nhàng', 'loudness+richness': 'Hòa âm đầy đặn kết hợp âm lượng gọn gàng, phản hồi động chắc chắn mang đến cảm giác nảy bật tuyệt vời, tiếp thêm sức sống', 'rhythm+richness': 'Hòa âm bừng sức sống theo sát nhịp điệu nhẹ nhàng, phản hồi tức thời rõ nét khiến người ta không khỏi đung đưa theo', 'richness+space_size': 'Hòa âm nhẹ nhàng hòa vào tiếng vang không gian trong trẻo, chiều sâu âm học tươi sáng khiến cảm xúc tràn đầy sức sống khẽ nhảy theo nhịp', 'richness+stereo_width': 'Hòa âm linh hoạt ngân vang trong trường âm thanh nổi, hình ảnh âm thanh phân bố đồng đều khiến sức sống nhẹ nhàng lan đến đôi tai', 'richness+vocal_1': 'Giọng hát nhẹ nhàng hòa quyện tự nhiên với hòa âm linh hoạt, xử lý dải tần giàu độ nảy khiến người nghe muốn nhảy múa theo giai điệu', 'richness+vocal_2': 'Hòa âm linh hoạt nâng đỡ ổn định giọng hát trong trẻo, khả năng định vị trung âm rõ ràng giúp sức sống bộc lộ theo nhịp điệu nhẹ nhàng', 'richness+vocal_3': 'Hòa âm nhẹ nhàng bao quanh giọng hát đầy đặn vút lên, năng lượng âm thanh sống động khiến niềm vui nhảy múa theo nhịp điệu', 'richness+vocal_4': 'Hòa âm bừng sức sống hòa âm tinh tế với giọng hát trong trẻo, độ đàn hồi âm học tràn đầy đánh thức từng tế bào', 'richness+vocal_5': 'Hòa âm đầy đặn bao bọc giọng hát đang vút lên, nền âm thanh chắc chắn mà linh hoạt khiến giai điệu tràn đầy sức sống', 'high_freq+loudness_1': 'Âm cao trong trẻo kết hợp dải động âm lượng gọn gàng, biến đổi áp lực âm tươi vui khiến những làn sóng âm nhỏ nhảy múa bên tai', 'high_freq+loudness_2': 'Âm cao trong trẻo hòa vào nhịp âm lượng nhẹ nhàng, áp lực âm ổn định và giàu độ đàn hồi khiến nốt nhạc nhảy nhót như tinh linh', 'high_freq+rhythm_1': 'Âm cao linh hoạt khớp chính xác với nhịp điệu nhẹ nhàng, phản hồi tức thời gọn gàng khiến giác quan tự nhiên phấn chấn theo tiết tấu', 'high_freq+rhythm_2': 'Âm cao tươi sáng bật nhảy theo nhịp điệu vui vẻ, khả năng bám theo dải động tuyệt vời khiến cơ thể lập tức hòa theo nhịp phách', 'high_freq+rhythm_3': 'Âm cao trong trẻo điểm xuyết nhịp trống tươi vui, phản hồi âm học rõ ràng khiến cơ thể tràn đầy sức mạnh hướng lên', 'high_freq+space_size': 'Âm cao trong vắt khúc xạ trong tiếng vang không gian thông thoáng, âm bội âm học tươi sáng lập tức thắp lên bầu không khí vui vẻ', 'high_freq+stereo_width_1': 'Âm cao linh hoạt nhẹ nhàng nhảy múa trong trường âm thanh nổi, sức căng hình ảnh âm thanh sống động khiến sức sống nhẹ nhàng trào đến từ khắp nơi', 'high_freq+stereo_width_2': 'Âm cao trong trẻo lan tỏa trong trường âm thanh nổi rộng lớn, khả năng định vị âm thanh tươi vui khiến sức sống như nắng tràn ngập khắp nơi', 'high_freq+vocal_1': 'Độ phân giải âm cao giúp giọng hát trong trẻo vút lên, chất âm nhẹ nhàng đàn hồi khiến sức sống nội tâm bùng nổ tức thì', 'high_freq+vocal_2': 'Tăng âm cao khiến giọng hát trong trẻo và vui tươi hơn, xử lý dải tần gọn gàng khiến từng câu ca từ sống động nhảy múa', 'high_freq+vocal_3': 'Giọng hát ở dải cao thông thoáng vút lên theo giai điệu, năng lượng âm học tươi vui khiến sức sống tràn đầy tự nhiên tỏa sáng', 'high_freq+vocal_4': 'Giọng hát dải cao thể hiện chất âm trong trẻo tươi sáng, khả năng phân giải âm thanh linh hoạt tự nhiên khiến niềm vui nhảy múa giữa các nốt nhạc', 'high_freq+vocal_5': 'Âm cao tinh tế làm sáng giọng hát nhẹ nhàng, nền âm học chắc chắn loại bỏ cảm giác chói gắt, khiến từng âm thanh đều tràn đầy sức sống', 'loudness+rhythm_1': 'Âm lượng chắc chắn thúc đẩy nhịp điệu dồn dập, phản hồi động gọn gàng khiến mỗi nhịp mạnh đều khơi dậy mong muốn nhảy múa', 'loudness+rhythm_2': 'Âm lượng ổn định đẩy sức căng nhịp điệu vui tươi lên cao, kiểm soát áp lực âm gọn gàng khiến cơ thể nhẹ nhàng lắc lư theo tiết tấu', 'loudness+space_size': 'Âm lượng đầy đặn lấp đầy tiếng vang không gian tươi sáng, sức căng áp lực âm lan tỏa tự nhiên bao bọc cơ thể bằng sức sống ấm áp', 'loudness+stereo_width': 'Âm lượng dồi dào trải khắp trường âm thanh nổi rộng lớn, dải động vòm đầy đặn khiến sức sống nhẹ nhàng bùng nổ khắp bốn phương', 'loudness+vocal_1': 'Giọng hát nhẹ nhàng cất lên cùng âm lượng chắc chắn, mức áp suất âm vừa đủ mang đến cảm giác nghe tươi sáng và đầy đàn hồi', 'loudness+vocal_2': 'Giọng hát đầy đặn kết hợp mức tăng âm lượng ổn định, chất giọng gọn gàng đầy sức xuyên thấu, khiến sức sống tự nhiên tỏa sáng', 'loudness+vocal_3': 'Âm lượng dồi dào mang đến sức bùng nổ nhẹ nhàng cho giọng hát, cộng hưởng lồng ngực rõ nét khiến từng câu tràn đầy năng lượng sống động', 'rhythm+space_size': 'Nhịp trống nhẹ nhàng lấp đầy tiếng vang không gian thông thoáng, chiều sâu âm học tươi sáng khiến sức sống hướng lên tự nhiên lan tỏa', 'rhythm+stereo_width': 'Nhịp điệu linh hoạt luân phiên trái phải trong trường âm thanh nổi, tiết tấu rõ ràng cuốn đi mọi hướng, đánh thức bước chân nhẹ nhàng', 'rhythm+vocal': 'Giọng hát giàu chất cảm bám sát nhịp trống nhẹ nhàng, phản hồi tức thời tuyệt vời hòa quyện giọng ca với tiết tấu, đẩy sức sống hướng lên cao', 'space_size+stereo_width': 'Tiếng vang không gian thông thoáng kết hợp trường âm thanh nổi toàn cảnh, sóng âm vui tươi tự nhiên cuốn đi, tạo nên khí thế đầy đặn hướng lên', 'space_size+vocal_1': 'Giọng hát trong trẻo vang vọng trong tiếng vang không gian tươi sáng, âm ngân nhẹ nhàng lan tỏa tự nhiên, khẽ khàng đẩy bầu không khí tràn đầy sức sống lên cao', 'space_size+vocal_2': 'Giọng hát vui tươi nhẹ nhàng cất sức trong tiếng vang không gian, cộng hưởng âm học ổn định khiến cảm xúc hướng lên lan tỏa khắp nơi', 'stereo_width+vocal_1': 'Giọng hát linh hoạt ngân vang trong trường âm thanh nổi rộng, định vị hình ảnh âm thanh chính xác khiến giai điệu nhẹ nhàng đàn hồi hơn', 'stereo_width+vocal_2': 'Giọng hát trong trẻo trải rộng trong trường âm thanh nổi rộng lớn, cộng hưởng vòm đầy đặn tươi sáng dịu dàng truyền tải sức sống hướng lên', 'stereo_width+vocal_3': 'Trường âm thanh nổi rộng nâng đỡ vững vàng giọng hát nhẹ nhàng, âm thanh lan tỏa đa chiều, khiến năng lượng sống động nhảy múa ập đến'};
        r14['dimPairs'] = r0;
        r13 = {};
        r0 = 'Vui vẻ là nhịp trống nhảy nhót';
        r13['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm tươi vui đan xen âm cao trong trẻo, khả năng phân giải tinh tế khiến nốt nhạc đầy đàn hồi, niềm vui lấp lánh giữa giai điệu', 'loudness+richness': 'Hòa âm đầy đặn kết hợp nhịp âm lượng nhẹ nhàng, kiểm soát dải động ổn định khiến tâm trạng vui vẻ lan tỏa theo sức căng âm học', 'rhythm+richness': 'Hòa âm bừng sức sống cuốn theo nhịp điệu nhẹ nhàng, tiết tấu tức thời gọn gàng mang đến cảm giác nghe vui tươi, niềm vui tràn đầy tự nhiên bộc lộ', 'richness+space_size': 'Hòa âm tươi sáng hòa vào tiếng vang không gian rộng, chiều sâu âm học thông thoáng khiến cảm xúc hân hoan lan tỏa khắp mọi ngóc ngách', 'richness+stereo_width': 'Hòa âm tinh tế được trường âm thanh nổi rộng lớn gom lại, hình ảnh âm thanh phân bố đồng đều khiến niềm vui nhẹ nhàng vương vấn bên tai', 'richness+vocal_1': 'Giọng hát nhẹ nhàng hòa quyện liền mạch với hòa âm linh hoạt, xử lý dải tần mượt mà khiến từng câu ca từ đong đầy niềm vui ngọt ngào', 'richness+vocal_2': 'Hòa âm dịu dàng nâng đỡ ổn định giọng hát trong trẻo, sự hòa quyện bè hát tuyệt vời khiến niềm vui đậm dần từng lớp, dịu dàng tươi sáng', 'richness+vocal_3': 'Hòa âm rạng rỡ bao quanh giọng hát đầy đặn vút lên, khả năng định vị trung âm rõ nét khiến tâm trạng nhẹ nhàng không khỏi trào dâng', 'richness+vocal_4': 'Hòa âm bừng sức sống hòa âm tinh tế với giọng hát trong trẻo, độ đàn hồi âm học tràn đầy khiến niềm vui sống động vương vấn mãi bên tai', 'richness+vocal_5': 'Hòa âm đầy đặn nhẹ nhàng bao bọc giọng hát, nền âm thanh chắc chắn mà linh hoạt khiến niềm vui trong lòng từ từ trào dâng', 'high_freq+loudness_1': 'Âm cao trong trẻo kết hợp âm lượng tươi vui tăng dần, kiểm soát áp lực âm mượt mà khiến nốt nhạc nhảy nhót lấp lánh như ánh sao', 'high_freq+loudness_2': 'Âm cao trong vắt hòa vào nhịp âm lượng nhẹ nhàng, sức căng động dịu êm lập tức thắp sáng tâm trạng, sống động và thông thoáng', 'high_freq+rhythm_1': 'Âm cao linh hoạt khớp chính xác với nhịp điệu nhẹ nhàng, phản hồi tức thời tươi vui khiến người ta không khỏi khẽ đung đưa theo tiết tấu', 'high_freq+rhythm_2': 'Âm cao tươi sáng nhảy múa theo nhịp điệu vui vẻ, khả năng bám theo dải động tuyệt vời khiến cảm xúc vui sướng liên tục dâng cao theo nhịp điệu', 'high_freq+rhythm_3': 'Âm cao trong trẻo điểm xuyết nhịp trống tươi vui, phản hồi âm học rõ ràng khiến niềm vui sống động tự nhiên lan tỏa khắp người', 'high_freq+space_size': 'Âm cao trong vắt khúc xạ trong tiếng vang không gian thông thoáng, âm bội âm học tươi sáng khiến bầu không khí vui vẻ lan tỏa khắp nơi', 'high_freq+stereo_width_1': 'Âm cao linh hoạt nhảy múa trong trường âm thanh nổi rộng lớn, sức căng hình ảnh âm thanh sống động khiến niềm vui nhẹ nhàng ùa đến', 'high_freq+stereo_width_2': 'Âm cao trong trẻo lan tỏa trong trường âm thanh nổi, khả năng định vị âm thanh tươi vui khiến nốt nhạc hạnh phúc bao quanh đôi tai chính xác', 'high_freq+vocal_1': 'Độ phân giải âm cao giúp giọng hát trong trẻo vút lên, chất âm nhẹ nhàng đàn hồi đưa cảm xúc ngọt ngào thẳng đến trái tim', 'high_freq+vocal_2': 'Tăng âm cao làm giọng hát thêm xuyên thấu, xử lý dải tần gọn gàng khiến niềm vui nở rộ theo giai điệu tươi sáng', 'high_freq+vocal_3': 'Âm cao tinh tế làm nền cho giọng hát trong trẻo, năng lượng âm thanh linh hoạt khiến tâm trạng tốt tràn đầy giữa những nốt nhạc tinh nghịch', 'high_freq+vocal_4': 'Âm cao trong trẻo đồng hành cùng giọng hát chủ đạo vút lên, âm cao tự nhiên linh hoạt khiến niềm vui ẩn chứa trong từng âm tiết', 'high_freq+vocal_5': 'Âm cao mượt mà loại bỏ tạp âm trong giọng hát, chất âm chỉnh sửa tự nhiên trong sáng khiến tâm trạng vui vẻ nhảy nhót', 'loudness+rhythm_1': 'Âm lượng chắc chắn thúc đẩy nhịp điệu nhẹ nhàng, phản hồi động ổn định khiến mỗi nhịp mạnh đều ngập tràn niềm vui dịu dàng', 'loudness+rhythm_2': 'Âm lượng đầy đặn đẩy sức căng nhịp điệu vui tươi lên cao, kiểm soát áp lực âm gọn gàng khiến cảm xúc vui vẻ sôi nổi kéo dài mãi', 'loudness+space_size': 'Âm lượng tươi sáng lấp đầy tiếng vang không gian thông thoáng, sức căng áp lực âm lan tỏa tự nhiên khiến niềm vui nhẹ nhàng tràn ngập khắp nơi', 'loudness+stereo_width': 'Âm lượng tươi vui trải khắp trường âm thanh nổi rộng lớn, dải động vòm đầy đặn dịu dàng bao bọc niềm vui từ mọi phía', 'loudness+vocal_1': 'Giọng hát đầy đặn cất lên cùng âm lượng ổn định, mức áp suất âm vừa đủ khiến tâm trạng tốt trào dâng theo tiếng hát từng lớp', 'loudness+vocal_2': 'Giọng hát giàu chất cảm kết hợp mức tăng âm lượng tươi vui, chất giọng gọn gàng đầy sức sống, để niềm vui thoải mái lan tỏa', 'loudness+vocal_3': 'Âm lượng dồi dào mang đến sức bùng nổ nhẹ nhàng cho giọng hát, cộng hưởng rõ nét khiến cảm xúc tươi sáng vui vẻ tự nhiên nở rộ', 'rhythm+space_size': 'Nhịp trống nhẹ nhàng lấp đầy tiếng vang không gian tươi sáng, chiều sâu âm học thông thoáng khiến niềm vui dịu dàng tự nhiên lan tỏa', 'rhythm+stereo_width': 'Nhịp điệu linh hoạt luân phiên trái phải trong trường âm thanh nổi, tiết tấu rõ ràng cuốn đi mọi hướng, cơ thể nhẹ nhàng lắc lư theo niềm vui', 'rhythm+vocal': 'Giọng hát giàu chất cảm bám sát nhịp điệu nhẹ nhàng, phản hồi tức thời tuyệt vời hòa quyện giọng ca với nhịp phách, niềm vui từ từ dâng cao', 'space_size+stereo_width': 'Tiếng vang không gian thông thoáng kết hợp trường âm thanh nổi rộng lớn, sóng âm vui tươi tự nhiên cuốn đi mọi hướng, bầu không khí tươi sáng dịu êm', 'space_size+vocal_1': 'Giọng hát trong trẻo vang vọng trong tiếng vang không gian tươi sáng, âm ngân nhẹ nhàng lan tỏa tự nhiên, tâm trạng tốt âm thầm ấp ủ', 'space_size+vocal_2': 'Giọng hát vui tươi nhẹ nhàng cất sức trong tiếng vang không gian, cộng hưởng âm học ổn định khiến niềm vui chữa lành lan tỏa khắp nơi', 'stereo_width+vocal_1': 'Giọng hát linh hoạt ngân vang trong trường âm thanh nổi rộng, định vị hình ảnh âm thanh chính xác dịu dàng ôm lấy thính giác bằng nét tươi vui nhẹ nhàng', 'stereo_width+vocal_2': 'Trường âm thanh nổi rộng nâng đỡ vững vàng giọng hát trong trẻo, âm thanh vòm đầy đặn truyền niềm vui sống động đến tận đáy lòng', 'stereo_width+vocal_3': 'Giọng hát giàu chất cảm trải rộng trong trường âm thanh nổi rộng, khả năng định vị âm thanh tươi sáng khiến cảm xúc hạnh phúc tràn ngập trái tim'};
        r13['dimPairs'] = r0;
        r12 = {};
        r0 = 'Ngọt ngào lơ lửng giữa sắc hồng';
        r12['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm dịu ngọt đan xen dải cao trong trẻo, độ phân giải cao mang lại cảm nghe mượt mà, như rơi vào biển mây hồng', 'loudness+richness': 'Hòa âm ấm áp hòa cùng biên độ âm lượng êm ái, khả năng kiểm soát động tinh tế khiến vị ngọt khẽ vương bên tai', 'rhythm+richness': 'Hòa âm tinh tế bám sát nhịp điệu khoan thai, đáp ứng tức thời mềm mại khiến tâm trạng tan chảy trong ngọt ngào', 'richness+space_size': 'Hòa âm dịu ngọt hòa vào tiếng vang không gian trong suốt, chiều sâu âm học tinh tế đưa người nghe vào giấc mơ nhung hồng', 'richness+stereo_width': 'Hòa âm ngọt ngào trải rộng trong trường âm thanh nổi, định vị âm hình chuẩn xác như lời thì thầm dịu dàng của người thương bên tai', 'richness+vocal_1': 'Giọng hát ngọt ngào hòa quyện liền mạch với hòa âm mềm mại, xử lý dải tần mượt mà khiến từng câu hát ngọt thấm tận đáy lòng', 'richness+vocal_2': 'Hòa âm tinh tế nâng đỡ ổn định giọng hát trong trẻo, khả năng hòa bè tuyệt vời khiến không khí tràn ngập ngọt ngào sắc hồng', 'richness+vocal_3': 'Hòa âm dịu ngọt bao quanh giọng hát đầy đặn, âm hình trung âm rõ ràng tạo nên không gian mộng mơ, khiến nhịp tim cũng dịu lại', 'richness+vocal_4': 'Hòa âm dịu dàng đan xen tinh tế với giọng hát trong trẻo, độ đàn hồi âm học đầy đặn khiến đôi tai được vòng tay yêu thương ôm trọn', 'richness+vocal_5': 'Hòa âm nhẹ nhàng bao bọc giọng hát ngọt ngào, nền âm thanh chắc chắn mà mềm mại khiến hương vị lãng mạn tràn đầy khẽ lan tỏa', 'high_freq+loudness_1': 'Dải cao trong trẻo hòa cùng chuyển biến âm lượng tinh tế, kiểm soát áp lực âm mượt mà khiến vị ngọt khẽ tan bên tai', 'high_freq+loudness_2': 'Dải cao mềm mại hòa vào nhịp âm lượng nhẹ nhàng, sức căng động ổn định tựa như ngậm một viên kẹo hồng đang tan chảy', 'high_freq+rhythm_1': 'Dải cao mượt mà khớp chính xác với nhịp điệu khoan thai, đáp ứng tức thời nhẹ nhàng khiến tâm trạng đung đưa theo nhịp ngọt ngào', 'high_freq+rhythm_2': 'Dải cao sáng bừng nhảy nhót theo nhịp điệu nhẹ nhàng, khả năng bám động tuyệt vời khiến không khí lãng mạn lan tỏa', 'high_freq+rhythm_3': 'Dải cao trong trẻo điểm xuyết nhịp trống khoan thai, chuyển động âm học rõ ràng khiến vị ngọt mềm mại khẽ tràn ra', 'high_freq+space_size': 'Dải cao tinh khiết khúc xạ trong tiếng vang không gian trong suốt, âm bội âm học mềm mại khiến vị ngọt sắc hồng hiện diện khắp nơi', 'high_freq+stereo_width_1': 'Dải cao linh hoạt dạo bước trong trường âm thanh nổi rộng mở, sức căng âm hình mềm mại khiến hơi thở ngọt ngào quấn quanh đôi tai', 'high_freq+stereo_width_2': 'Dải cao sáng trong khẽ lấp lánh trong trường âm thanh nổi, âm hình rõ ràng khiến không khí lãng mạn sắc hồng tràn đầy', 'high_freq+vocal_1': 'Độ phân giải dải cao nâng giọng hát lên mượt mà, chất âm dẻo ngọt tựa như đầu lưỡi chạm phải mật ngọt', 'high_freq+vocal_2': 'Tăng ích dải cao khiến giọng hát càng trong ngọt và sáng, xử lý dải tần sạch mượt như tinh linh đang ngân nga bên tai', 'high_freq+vocal_3': 'Dải cao tinh tế làm nền cho giọng hát trong trẻo, năng lượng âm tần mềm mại khiến tâm trạng tan chảy trong giai điệu dịu dàng', 'high_freq+vocal_4': 'Dải cao trong trẻo nâng giọng hát ngọt ngào vút lên, dải cao tự nhiên và linh hoạt khiến sự lãng mạn nở rộ bên tai', 'high_freq+vocal_5': 'Dải cao mượt mà loại bỏ tạp âm giọng hát, chất âm trong sáng tự nhiên khiến từng câu chữ ngọt ngào khẽ rung động', 'loudness+rhythm_1': 'Âm lượng mềm mại hòa cùng nhịp điệu khoan thai, đáp ứng động ổn định khiến tình yêu khẽ chảy theo nhịp điệu', 'loudness+rhythm_2': 'Âm lượng ấm áp đẩy căng sức căng của nhịp điệu khoan thai, kiểm soát áp lực âm tinh tế khiến hạnh phúc sắc hồng tràn khắp cơ thể', 'loudness+space_size': 'Âm lượng mềm mại lấp đầy tiếng vang không gian trong suốt, sức căng áp lực âm lan tỏa tự nhiên khiến cơ thể như lơ lửng giữa biển mây hồng', 'loudness+stereo_width': 'Âm lượng nhẹ nhàng trải khắp trường âm thanh nổi rộng mở, động học vòm đầy đặn mang đến cảm giác ngọt ngào si tình chữa lành lòng người', 'loudness+vocal_1': 'Giọng hát ngọt ngào được giải phóng cùng âm lượng ổn định, mức áp lực âm vừa vặn khiến vị ngọt mềm mại chạm đến đáy lòng', 'loudness+vocal_2': 'Giọng hát có chất liệu hòa cùng tăng ích âm lượng mềm mại, đường giọng mượt mà đầy sức lay động, tràn ngập khí chất lãng mạn', 'loudness+vocal_3': 'Âm lượng đầy đặn ban cho giọng hát sức bật mềm mại, cộng hưởng rõ ràng khiến cảm xúc trong ngọt và dịu dàng trở nên chữa lành', 'rhythm+space_size': 'Nhịp điệu khoan thai lấp đầy tiếng vang không gian sáng sủa, chiều sâu âm học trong suốt khiến vị ngọt sắc hồng khẽ lan tỏa', 'rhythm+stereo_width': 'Nhịp điệu mềm mại luân phiên trái phải trong trường âm thanh nổi, nhịp điệu rõ ràng bao trùm mọi hướng, tâm trạng được xoa dịu lặng lẽ', 'rhythm+vocal': 'Giọng hát ngọt ngào bám sát nhịp điệu khoan thai, chuyển động tức thời tuyệt vời khiến đường giọng và phách hòa quyện, vị ngọt lặng lẽ tuôn chảy', 'space_size+stereo_width': 'Tiếng vang không gian trong suốt chồng lên trường âm thanh nổi rộng mở, sóng âm mềm mại lan tỏa tự nhiên, hơi thở lãng mạn miên man', 'space_size+vocal_1': 'Giọng hát trong trẻo vang vọng trong tiếng vang không gian mềm mại, ngân dài nhẹ nhàng lan tỏa, tâm trạng được chữa lành an yên', 'space_size+vocal_2': 'Giọng hát ngọt ngào khẽ bung sức trong tiếng vang không gian, cộng hưởng âm học ổn định khiến người nghe như lơ lửng giữa tầng mây hồng', 'stereo_width+vocal_1': 'Giọng hát ngọt ngào trải rộng trong trường âm thanh nổi, định vị âm hình chuẩn xác khiến đôi tai được lãng mạn khẽ ôm trọn', 'stereo_width+vocal_2': 'Trường âm thanh nổi rộng mở nâng đỡ ổn định giọng hát trong trẻo, âm tần vòm đầy đặn mang hơi ấm ngọt ngào dịu dàng đến đáy lòng', 'stereo_width+vocal_3': 'Giọng hát có chất liệu trải rộng trong trường âm thanh nổi, âm hình sáng rõ khiến sự trong ngọt mềm mại thấm sâu và miên man'};
        r12['dimPairs'] = r0;
        r11 = {};
        r0 = 'Tự tin tỏa sáng';
        r11['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm vững vàng đan xen dải cao trong trẻo, độ phân giải cao khiến chất âm tràn đầy sức căng, trong tĩnh lặng vẫn ánh lên vẻ ung dung', 'loudness+richness': 'Hòa âm chắc chắn hòa cùng kiểm soát âm lượng ổn định, dải động rộng mở khiến sự tự tin ẩn mình trong từng giai điệu', 'rhythm+richness': 'Hòa âm vững vàng bám sát nhịp điệu dứt khoát, đáp ứng tức thời tuyệt vời khiến giai điệu toát ra khí độ ung dung', 'richness+space_size': 'Hòa âm nhiều lớp lấp đầy tiếng vang không gian rộng mở, chiều sâu âm học đầy đặn mang lại cảm giác hiện diện chắc chắn và ung dung', 'richness+stereo_width': 'Hòa âm đầy đặn trải rộng trong trường âm thanh nổi, phân bố âm hình chuẩn xác khiến khí chất điềm tĩnh tự tin khẽ lan tỏa', 'richness+vocal_1': 'Giọng hát có chất liệu hòa cùng hòa âm vững vàng, khả năng tạo hình chắc chắn khiến từng câu hát ung dung mà mạnh mẽ', 'richness+vocal_2': 'Hòa âm đậm đà nâng đỡ ổn định giọng hát có chất liệu, khả năng hòa dải tần xuất sắc khiến nội lực trầm ổn tự nhiên bộc lộ', 'richness+vocal_3': 'Hòa âm chắc chắn bao quanh giọng hát đầy đặn, âm hình trung âm rõ ràng mang lại khí vực ung dung, kiên định và mạnh mẽ', 'richness+vocal_4': 'Hòa âm vững vàng đan xen tinh tế với giọng hát có chất liệu, khả năng kiểm soát dải tần tuyệt vời khiến ánh sáng tự tin tự nhiên toát ra', 'richness+vocal_5': 'Hòa âm đầy đặn nâng đỡ vững vàng giọng hát có chất liệu, nền âm thanh chắc chắn khiến sự tự tin tỏa sáng trong từng khúc ngân', 'high_freq+loudness_1': 'Dải cao trong trẻo hòa cùng kiểm soát âm lượng ổn định, sức căng áp lực âm tinh tế khiến khí chất trầm ổn tự thành', 'high_freq+loudness_2': 'Dải cao mượt mà hòa vào biên độ âm lượng vững vàng, khả năng nén động xuất sắc mang lại nội lực ung dung, không kiêu không hèn', 'high_freq+rhythm_1': 'Dải cao trong trẻo khớp chính xác với nhịp điệu dứt khoát, đáp ứng tức thời sạch sẽ khiến từng nhịp điệu toát lên vẻ chắc chắn', 'high_freq+rhythm_2': 'Dải cao sáng bừng vút lên theo nhịp điệu vững vàng, khả năng bám động tuyệt vời khiến sự tự tin rộng mở lặng lẽ nở rộ', 'high_freq+rhythm_3': 'Dải cao tinh tế điểm xuyết nhịp trống vững vàng, chuyển động âm học rõ ràng mang lại vẻ ung dung điềm tĩnh, tự chủ', 'high_freq+space_size': 'Dải cao trong suốt khúc xạ trong tiếng vang không gian rộng mở, âm bội âm học tự nhiên khiến khí độ ung dung trải rộng vô hạn', 'high_freq+stereo_width_1': 'Dải cao sáng trong trải rộng cân bằng trong trường âm thanh nổi, sức căng phân giải cao khiến khí chất tự tin điềm tĩnh rạng rỡ', 'high_freq+stereo_width_2': 'Dải cao mượt mà dạo bước trong trường âm thanh nổi rộng lớn, âm hình chuẩn xác khiến tầm nhìn rộng mở tự nhiên bộc lộ', 'high_freq+vocal_1': 'Độ phân giải dải cao khiến giọng hát trong sáng và thấu suốt, âm hình chất âm chắc chắn khiến từng câu ngân đều kiên định, mạnh mẽ', 'high_freq+vocal_2': 'Tăng ích dải cao làm giọng hát thêm xuyên suốt, xử lý dải tần dứt khoát mang sẵn khí độ ung dung, chắc chắn', 'high_freq+vocal_3': 'Dải cao tinh tế làm nền cho giọng hát có chất liệu, năng lượng âm tần ổn định khiến nội lực mạnh mẽ ẩn sâu trong đường giọng trong trẻo', 'high_freq+vocal_4': 'Dải cao trong trẻo nâng giọng hát chắc chắn trải rộng, dải cao tự nhiên cho thấy ánh sáng ung dung không vội vàng', 'high_freq+vocal_5': 'Dải cao mượt mà loại bỏ tạp âm giọng hát, chất âm trong sáng tự nhiên toát lên khí vực điềm tĩnh, ung dung và mạnh mẽ', 'loudness+rhythm_1': 'Âm lượng chắc chắn dẫn dắt nhịp điệu vững vàng, đáp ứng động ổn định khiến từng phách mạnh toát lên nội lực chắc chắn', 'loudness+rhythm_2': 'Âm lượng dồi dào đẩy căng sức căng của nhịp điệu dứt khoát, kiểm soát áp lực âm dứt khoát khiến bước đi ung dung tràn đầy sức mạnh', 'loudness+space_size': 'Âm lượng vững vàng lấp đầy tiếng vang không gian trong suốt, sức căng áp lực âm lan tỏa tự nhiên khiến khí chất điềm tĩnh tự bộc lộ', 'loudness+stereo_width': 'Âm lượng cân bằng trải khắp trường âm thanh nổi rộng mở, động học vòm đầy đặn khiến sự tự tin trầm ổn vừa vững vàng vừa thư thái', 'loudness+vocal_1': 'Giọng hát có chất liệu được giải phóng cùng âm lượng vững vàng, mức áp lực âm vừa vặn khiến đường giọng chắc chắn mang sẵn phong thái ung dung', 'loudness+vocal_2': 'Giọng hát đầy đặn hòa cùng tăng ích âm lượng tiết chế, đường giọng dứt khoát tràn đầy nội lực, sự tự tin ẩn trong từng câu ngân', 'loudness+vocal_3': 'Âm lượng đầy đặn ban cho giọng hát sức bật chắc chắn, cộng hưởng ổn định khiến khí chất ung dung, điềm tĩnh hiện rõ', 'rhythm+space_size': 'Nhịp trống vững vàng lấp đầy tiếng vang không gian rộng mở, chiều sâu âm học rõ ràng khiến sự tự tin ung dung tự nhiên trải rộng', 'rhythm+stereo_width': 'Nhịp điệu dứt khoát luân phiên trái phải trong trường âm thanh nổi, nhịp điệu rõ ràng bao trùm mọi hướng, mang lại cảm giác điềm tĩnh tự chủ', 'rhythm+vocal': 'Giọng hát có chất liệu bám sát nhịp điệu vững vàng, chuyển động tức thời tuyệt vời khiến đường giọng chắc chắn hòa cùng phách, ung dung trải rộng', 'space_size+stereo_width': 'Tiếng vang không gian rộng mở chồng lên trường âm thanh nổi toàn cảnh, sóng âm vững vàng lan tỏa tự nhiên, tạo nên khí vực chắc chắn và ung dung', 'space_size+vocal_1': 'Giọng hát có chất liệu vang vọng trong tiếng vang không gian rộng mở, ngân dài chắc chắn lan tỏa tự nhiên, khí chất điềm tĩnh và an định', 'space_size+vocal_2': 'Giọng hát chắc chắn khẽ bung sức trong tiếng vang không gian, cộng hưởng âm học vững vàng mang sẵn khí chất trầm ổn, không phô trương', 'stereo_width+vocal_1': 'Giọng hát vững vàng trải rộng trong trường âm thanh nổi, định vị âm hình chuẩn xác khiến khí chất tự tin ung dung tự nhiên bộc lộ', 'stereo_width+vocal_2': 'Trường âm thanh nổi rộng mở nâng đỡ ổn định giọng hát chắc chắn, âm tần vòm đầy đặn mang khí chất điềm tĩnh, trầm ổn đến từng chi tiết', 'stereo_width+vocal_3': 'Giọng hát có chất liệu trải rộng trong trường âm thanh nổi, âm hình chắc chắn toát lên vẻ ung dung, chỉ cần đứng đây là đủ'};
        r11['dimPairs'] = r0;
        r10 = {};
        r0 = 'Lãng mạn là vì sao bừng nở';
        r10['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm ấm áp đan xen dải cao trong trẻo, độ phân giải tinh tế khiến nốt nhạc lấp lánh như ánh sao, không khí lãng mạn quấn quanh thân', 'loudness+richness': 'Hòa âm ấm áp hòa cùng kiểm soát âm lượng mượt mà, động học tinh tế khiến tình yêu khẽ chảy si tình trong từng giai điệu', 'rhythm+richness': 'Hòa âm dịu dàng bám sát nhịp điệu khoan thai, đáp ứng tức thời ổn định khiến không khí lãng mạn miên man đến tận cùng', 'richness+space_size': 'Hòa âm mềm mại hòa vào tiếng vang không gian sâu thẳm, chiều sâu âm học tinh tế khiến sự lãng mạn lan tỏa khắp nơi như bầu trời đêm', 'richness+stereo_width': 'Hòa âm ấm áp trải rộng trong trường âm thanh nổi mênh mông, định vị âm hình chuẩn xác đưa người nghe vào biển sao lãng mạn dịu dàng', 'richness+vocal_1': 'Giọng hát có chất liệu hòa quyện liền mạch với hòa âm dịu dàng, xử lý dải tần mượt mà khiến đường giọng mềm mại tràn đầy rung động và lãng mạn', 'richness+vocal_2': 'Hòa âm tinh tế nâng đỡ ổn định giọng hát trong trẻo, khả năng hòa bè tuyệt vời tựa như sao trời buông xuống, không khí lãng mạn lập tức tràn đầy', 'richness+vocal_3': 'Hòa âm dịu dàng bao quanh giọng hát có chất liệu vút lên, âm hình trung âm rõ ràng như rơi vào biển sao bao la, xa xăm và miên man', 'richness+vocal_4': 'Hòa âm ấm áp đan xen tinh tế với giọng hát trong trẻo, độ đàn hồi âm học đầy đặn khiến sự lãng mạn thêm xa xăm, lòng tràn đầy dịu dàng', 'richness+vocal_5': 'Hòa âm nhẹ nhàng bao bọc giọng hát ngọt ngào, nền âm thanh chắc chắn mà mềm mại khiến vì sao lãng mạn rơi xuống vì bạn', 'high_freq+loudness_1': 'Dải cao trong trẻo hòa cùng chuyển biến âm lượng mềm mại, kiểm soát áp lực âm mượt mà khiến sự lãng mạn như ánh sao khẽ quấn bên tai', 'high_freq+loudness_2': 'Dải cao sáng dịu hòa vào biên độ âm lượng ấm áp, sức căng động ổn định mang lại không khí lãng mạn tinh tế và si tình', 'high_freq+rhythm_1': 'Dải cao linh hoạt khớp chính xác với nhịp điệu khoan thai, đáp ứng tức thời nhẹ nhàng như ánh sao lặng lẽ chảy trong nhịp điệu', 'high_freq+rhythm_2': 'Dải cao sáng bừng lấp lánh theo nhịp điệu nhẹ nhàng, khả năng bám động tuyệt vời khiến rung động lãng mạn si tình lặng lẽ lan tỏa', 'high_freq+rhythm_3': 'Dải cao trong trẻo điểm xuyết nhịp trống khoan thai, chuyển động âm học rõ ràng khiến không khí lãng mạn tràn đầy như ánh sao nhảy nhót', 'high_freq+space_size': 'Dải cao tinh khiết khúc xạ trong tiếng vang không gian sâu thẳm, âm bội âm học mềm mại như ánh sao rải khắp bầu trời đêm bao la', 'high_freq+stereo_width_1': 'Dải cao linh hoạt khẽ lấp lánh trong trường âm thanh nổi rộng mở, sức căng âm hình mềm mại khiến sự lãng mạn si tình bao quanh đôi tai', 'high_freq+stereo_width_2': 'Dải cao sáng trong dạo bước trong trường âm thanh nổi, âm hình tươi sáng tựa ánh sao lấp lánh, ấm áp và lãng mạn', 'high_freq+vocal_1': 'Độ phân giải dải cao khiến giọng hát mềm mại và tinh tế, chất âm dẻo ngọt khiến sự lãng mạn si tình chạm thẳng vào đáy lòng', 'high_freq+vocal_2': 'Tăng ích dải cao khiến giọng hát thêm trong trẻo, xử lý dải tần sạch mượt như được ánh sao khẽ ôm, sự lãng mạn miên man', 'high_freq+vocal_3': 'Dải cao tinh tế làm nền cho giọng hát trong trẻo, năng lượng âm tần mềm mại như ánh sao trong suốt, sự lãng mạn chữa lành tràn đầy dịu dàng', 'high_freq+vocal_4': 'Dải cao trong trẻo nâng giọng hát ngọt ngào trải rộng, dải cao tự nhiên và linh hoạt khiến không khí lãng mạn si tình khẽ lan tỏa', 'high_freq+vocal_5': 'Dải cao mượt mà loại bỏ tạp âm giọng hát, chất âm trong sáng tự nhiên khiến rung động lãng mạn xa xăm ẩn trong đường giọng', 'loudness+rhythm_1': 'Âm lượng mềm mại hòa cùng nhịp điệu khoan thai, đáp ứng động ổn định khiến tình yêu lãng mạn khẽ chảy theo nhịp điệu', 'loudness+rhythm_2': 'Âm lượng ấm áp đẩy căng sức căng của nhịp điệu khoan thai, kiểm soát áp lực âm tinh tế khiến sự lãng mạn trong giai điệu si tình khẽ lan rộng', 'loudness+space_size': 'Âm lượng ấm áp lấp đầy tiếng vang không gian sâu thẳm, sức căng áp lực âm lan tỏa tự nhiên khiến sự lãng mạn bao la như bầu trời đêm tràn đầy', 'loudness+stereo_width': 'Âm lượng nhẹ nhàng trải khắp trường âm thanh nổi rộng mở, động học vòm đầy đặn đưa người nghe vào vòng tay lãng mạn si tình, dịu dàng và nồng ấm', 'loudness+vocal_1': 'Giọng hát ngọt ngào được giải phóng cùng âm lượng ổn định, mức áp lực âm vừa vặn khiến đường giọng si tình tràn đầy tình yêu lãng mạn', 'loudness+vocal_2': 'Giọng hát có chất liệu hòa cùng tăng ích âm lượng mềm mại, tiếng ngân trầm mượt mà đầy sức lay động, khiến không khí lãng mạn lặng lẽ nở rộ', 'loudness+vocal_3': 'Âm lượng đầy đặn ban cho giọng hát sức căng mềm mại, cộng hưởng rõ ràng khiến sự lãng mạn ấm áp và xa xăm khiến người nghe đắm chìm sâu', 'rhythm+space_size': 'Nhịp điệu khoan thai lấp đầy tiếng vang không gian sâu thẳm, chiều sâu âm học bao la khiến sự lãng mạn dịu dàng được kể chậm rãi', 'rhythm+stereo_width': 'Nhịp điệu mềm mại luân phiên trái phải trong trường âm thanh nổi, nhịp điệu rõ ràng bao trùm mọi hướng, mang lại sự lãng mạn bao la và ấm áp', 'rhythm+vocal': 'Giọng hát ngọt ngào bám sát nhịp điệu khoan thai, chuyển động tức thời tuyệt vời khiến đường giọng si tình hòa cùng phách, sự lãng mạn tuôn chảy', 'space_size+stereo_width': 'Tiếng vang không gian bao la chồng lên trường âm thanh nổi toàn cảnh, sóng âm mềm mại lan tỏa tự nhiên, lãng mạn đến tận cùng như muôn ánh sao', 'space_size+vocal_1': 'Giọng hát trong trẻo vang vọng trong tiếng vang không gian sâu thẳm, ngân dài nhẹ nhàng lan tỏa, tựa sao trời buông xuống, dịu dàng và lãng mạn', 'space_size+vocal_2': 'Giọng hát ngọt ngào khẽ bung sức trong tiếng vang không gian, cộng hưởng âm học ổn định khiến không khí lãng mạn xa xăm tràn đầy', 'stereo_width+vocal_1': 'Giọng hát ngọt ngào trải rộng trong trường âm thanh nổi, định vị âm hình chuẩn xác khiến đôi tai được sự lãng mạn si tình khẽ ôm trọn', 'stereo_width+vocal_2': 'Trường âm thanh nổi rộng mở nâng đỡ ổn định giọng hát trong trẻo, âm tần vòm đầy đặn tựa sao trời rơi xuống, sự lãng mạn chạm vào dây đàn', 'stereo_width+vocal_3': 'Giọng hát có chất liệu trải rộng trong trường âm thanh nổi, âm hình sáng rõ toát lên vẻ dịu dàng, có một vì sao rơi xuống vì bạn'};
        r10['dimPairs'] = r0;
        r9 = {};
        r0 = 'Thả lỏng để thời gian tan chảy';
        r9['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm ấm áp đan xen dải cao mềm mại, độ phân giải tinh tế khiến nốt nhạc xoa dịu mệt mỏi, thời gian chậm lại trong giai điệu', 'loudness+richness': 'Hòa âm thư thái hòa cùng kiểm soát âm lượng mượt mà, động học tinh tế khiến âm điệu mềm mại khẽ chảy, mang lại sự thả lỏng', 'rhythm+richness': 'Hòa âm miên man bám sát nhịp điệu lười biếng, đáp ứng tức thời ổn định khiến tâm trạng lặng dần theo nốt nhạc, thời gian tan chảy', 'richness+space_size': 'Hòa âm mềm mại hòa vào tiếng vang không gian trong suốt, chiều sâu âm học sâu thẳm khiến người nghe như thả mình lơ lửng trên mặt biển tĩnh lặng', 'richness+stereo_width': 'Hòa âm ấm áp trải rộng trong trường âm thanh nổi mênh mông, phân bố âm hình đều đặn khiến cảm giác thư giãn khẽ ôm trọn thân', 'richness+vocal_1': 'Giọng hát có chất liệu hòa quyện liền mạch với hòa âm ấm áp, xử lý dải tần mượt mà khiến đường giọng miên man toát lên vẻ thư thái, ung dung', 'richness+vocal_2': 'Hòa âm tinh tế nâng đỡ ổn định giọng hát trong trẻo, khả năng hòa bè tuyệt vời khiến tâm trạng dần bình yên, tận hưởng sự an nhiên', 'richness+vocal_3': 'Hòa âm mềm mại bao quanh giọng hát lười biếng, âm hình trung âm rõ ràng mang lại không khí tự tại, như sóng biển khẽ đưa', 'richness+vocal_4': 'Hòa âm ấm áp đan xen tinh tế với giọng hát có chất liệu, kiểm soát dải tần thư thái khiến mệt mỏi toàn thân lặng lẽ tan biến', 'richness+vocal_5': 'Hòa âm nhẹ nhàng bao bọc giọng hát thư thái, nền âm thanh chắc chắn mà mềm mại khiến tâm cảnh dần an yên, hoàn toàn thả lỏng', 'high_freq+loudness_1': 'Dải cao mềm dịu hòa cùng chuyển biến âm lượng mượt mà, kiểm soát áp lực âm mượt mà xóa bỏ cảm giác gắt, chỉ còn tiếng vang âm học ấm áp', 'high_freq+loudness_2': 'Dải cao mềm mại hòa vào biên độ âm lượng nhẹ nhàng, sức căng động ổn định mang lại đường giọng trong sạch, khiến thân tâm thư thái', 'high_freq+rhythm_1': 'Dải cao mềm mại khớp chính xác với nhịp điệu lười biếng, đáp ứng tức thời thư thái như con sóng khẽ hôn bờ, thời gian chậm lại', 'high_freq+rhythm_2': 'Dải cao trong trẻo khẽ lướt theo nhịp điệu khoan thai, khả năng bám động tuyệt vời như gió biển phất qua mặt, xoa dịu mọi lo âu', 'high_freq+rhythm_3': 'Dải cao tinh tế điểm xuyết nhịp trống chậm rãi, chuyển động âm học mềm mại khiến thời gian tan chảy theo nốt nhạc', 'high_freq+space_size': 'Dải cao mềm dịu khúc xạ trong tiếng vang không gian trong suốt, âm bội âm học nhẹ và dài khiến âm điệu ấm áp khẽ lan tỏa', 'high_freq+stereo_width_1': 'Dải cao mềm mại dạo bước trong trường âm thanh nổi rộng mở, sức căng âm hình ổn định khiến cảm giác thư giãn trải rộng và tràn đầy đôi tai', 'high_freq+stereo_width_2': 'Dải cao trong trẻo khẽ gợn trong trường âm thanh nổi, âm hình tươi sáng khiến đôi tai được hơi thở dịu dàng ôm trọn', 'high_freq+vocal_1': 'Độ phân giải dải cao khiến giọng hát trong trẻo và thoát tục, chất âm mềm mại, trong suốt khiến đường giọng lười biếng khẽ lướt trong không khí', 'high_freq+vocal_2': 'Tăng ích dải cao khiến giọng hát trong trẻo, thoát tục, xử lý dải tần mượt mà mang lại sự thư thái tự tại, như lơ lửng trên mặt biển', 'high_freq+vocal_3': 'Dải cao tinh tế làm nền cho giọng hát trong veo, năng lượng âm tần mềm mại khiến từng câu đều tràn đầy chất liệu lười biếng, chữa lành', 'high_freq+vocal_4': 'Dải cao trong trẻo và mịn màng nâng giọng hát thoát tục trải rộng, dải cao tự nhiên và mềm mại khiến mọi phiền muộn được giọng hát xoa dịu', 'high_freq+vocal_5': 'Dải cao mượt mà loại bỏ tạp âm giọng hát, chất âm trong sáng, thuần khiết khiến thân tâm hoàn toàn đắm chìm trong trạng thái thư giãn', 'loudness+rhythm_1': 'Âm lượng mềm mại hòa cùng nhịp điệu khoan thai, đáp ứng động ổn định khiến phách mạnh khẽ rơi, thân tâm cũng thư thái theo', 'loudness+rhythm_2': 'Âm lượng tiết chế đẩy căng sức căng của nhịp điệu chậm rãi, kiểm soát áp lực âm tinh tế xoa dịu từng tia tâm trạng căng thẳng', 'loudness+space_size': 'Âm lượng ấm áp lấp đầy tiếng vang không gian thư thái, sức căng áp lực âm lan tỏa tự nhiên khiến cảm giác tĩnh lặng chữa lành tràn khắp xung quanh', 'loudness+stereo_width': 'Âm lượng nhẹ nhàng trải khắp trường âm thanh nổi rộng mở, động học vòm đầy đặn mang lại sự bao bọc âm học thư giãn đến cực độ', 'loudness+vocal_1': 'Giọng hát lười biếng được giải phóng cùng âm lượng ổn định, mức áp lực âm tiết chế khiến cảm xúc trầm lắng đắm sâu trong sự dịu dàng', 'loudness+vocal_2': 'Giọng hát có chất liệu hòa cùng kiểm soát âm lượng mềm dịu, đường giọng mượt mà, thư thái đầy sức lay động, khiến mệt mỏi tan chảy', 'loudness+vocal_3': 'Âm lượng ấm áp ban cho giọng hát sức căng mềm mại, cộng hưởng của tiếng ngân nhẹ nhàng khiến thân tâm hoàn toàn đắm chìm trong thư giãn, chữa lành', 'rhythm+space_size': 'Nhịp điệu lười biếng lấp đầy tiếng vang không gian sâu thẳm, chiều sâu âm học miên man khiến thời gian căng thẳng lặng lẽ chậm lại', 'rhythm+stereo_width': 'Nhịp điệu khoan thai luân phiên trái phải trong trường âm thanh nổi, nhịp điệu mềm mại bao trùm mọi hướng, khiến thân tâm dần buông bỏ', 'rhythm+vocal_1': 'Giọng hát có chất liệu bám sát nhịp điệu lười biếng, chuyển động tức thời tuyệt vời khiến đường giọng thư thái hòa cùng phách chậm, như dòng nước chảy', 'rhythm+vocal_2': 'Giọng hát khoan thai tự do tuôn chảy theo nhịp điệu mềm mại, cảm giác trầm âm học ổn định khiến người nghe cam tâm hoàn toàn thả lỏng', 'space_size+stereo_width': 'Tiếng vang không gian rộng mở chồng lên trường âm thanh nổi toàn cảnh, sóng âm mềm mại bao quanh tự nhiên, buông bỏ mọi căng thẳng của thân tâm', 'space_size+vocal_1': 'Giọng hát khoan thai vang vọng trong tiếng vang không gian rộng mở, ngân dài nhẹ nhàng lan tỏa tự nhiên, tâm cảnh dần trở về an yên', 'space_size+vocal_2': 'Giọng hát lười biếng khẽ bung sức trong tiếng vang không gian, cộng hưởng âm học ổn định khiến nội tâm tìm lại sự tĩnh mịch và thư giãn', 'stereo_width+vocal_1': 'Giọng hát khoan thai trải rộng trong trường âm thanh nổi, định vị âm hình chuẩn xác khiến đôi tai được cảm giác thư giãn tự nhiên khẽ quấn quanh', 'stereo_width+vocal_2': 'Trường âm thanh nổi rộng mở nâng đỡ ổn định giọng hát mềm mại, âm tần vòm đầy đặn mang hơi ấm chữa lành dịu dàng đến từng chi tiết', 'stereo_width+vocal_3': 'Giọng hát có chất liệu lan tỏa trong trường âm thanh nổi rộng mở, âm hình thư giãn khiến mọi phiền muộn bị sóng âm dịu dàng xóa tan'};
        r9['dimPairs'] = r0;
        r8 = {};
        r0 = 'Chữa lành là một vệt xanh';
        r8['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm ấm áp đan xen dải cao mềm mại, độ phân giải tinh tế khiến nốt nhạc chữa lành như gió xuân, mọi bất an được xoa dịu', 'loudness+richness': 'Hòa âm thư thái hòa cùng biên độ âm lượng mượt mà, kiểm soát động tinh tế khiến mệt mỏi tan biến trong giai điệu đầy đặn', 'rhythm+richness': 'Hòa âm mềm mại bám sát nhịp điệu nhẹ nhàng, đáp ứng tức thời ổn định khiến tâm trạng căng thẳng được chữa lành dịu dàng', 'richness+space_size': 'Hòa âm mềm mại hòa vào tiếng vang không gian chữa lành, chiều sâu âm học ấm áp trải rộng ra xung quanh, nâng đỡ mọi mệt mỏi một cách vững vàng', 'richness+stereo_width': 'Hòa âm ấm áp trải rộng trong trường âm thanh nổi mênh mông, phân bố âm hình đều đặn khiến cảm giác an toàn chữa lành lan tỏa', 'richness+vocal_1': 'Giọng hát có chất liệu hòa quyện liền mạch với hòa âm dịu dàng, xử lý dải tần mượt mà khiến từng câu ngân đều được nâng đỡ vững vàng', 'richness+vocal_2': 'Hòa âm tinh tế nâng đỡ ổn định giọng hát trong trẻo, khả năng hòa bè tuyệt vời mang lại sự chữa lành mềm mại, an trú mọi cảm xúc', 'richness+vocal_3': 'Hòa âm ấm áp ôm trọn giọng hát có chất liệu, âm hình trung âm rõ ràng mang lại cảm giác an tâm, xoa dịu, mọi phiền muộn được xoa phẳng', 'richness+vocal_4': 'Hòa âm mềm mại đan xen tinh tế với giọng hát trong trẻo, kiểm soát âm tần ổn định khiến cảm giác chữa lành miên man, nội tâm bình yên', 'richness+vocal_5': 'Hòa âm đầy đặn khẽ bao quanh giọng hát chữa lành, nền âm thanh chắc chắn mà ấm áp khiến mệt mỏi lập tức được buông xuống', 'high_freq+loudness_1': 'Dải cao mềm dịu hòa cùng chuyển biến âm lượng mượt mà, kiểm soát áp lực âm mượt mà xóa bỏ cảm giác chói tai, chỉ còn lại sự an tâm chữa lành', 'high_freq+loudness_2': 'Dải cao ấm áp hòa vào biên độ âm lượng thư thái, sức căng động ổn định bao bọc mọi cảm xúc một cách dịu dàng, mang lại sự an ổn', 'high_freq+rhythm_1': 'Dải cao mềm mại khớp chính xác với nhịp điệu an ổn, đáp ứng tức thời thư thái khiến tâm trạng lặng dần theo nhịp điệu', 'high_freq+rhythm_2': 'Dải cao trong trẻo gợn theo nhịp điệu khoan thai, khả năng bám động tuyệt vời mang lại sự chữa lành mềm mại, xua tan bất an', 'high_freq+rhythm_3': 'Dải cao tinh tế điểm xuyết nhịp trống thư thái, chuyển động âm học mềm mại như vệt xanh trải rộng, nâng đỡ mọi mệt mỏi một cách vững vàng', 'high_freq+space_size': 'Dải cao tinh khiết khúc xạ trong tiếng vang không gian trong suốt, âm bội âm học tươi sáng lan tỏa xung quanh, cảm giác chữa lành an tâm tràn đầy', 'high_freq+stereo_width_1': 'Dải cao linh hoạt dạo bước trong trường âm thanh nổi rộng mở, sức căng âm hình mềm mại khiến hơi thở chữa lành bao quanh đôi tai', 'high_freq+stereo_width_2': 'Dải cao sáng trong khẽ lấp lánh trong trường âm thanh nổi, âm hình tươi sáng và ấm áp khiến cảm giác an toàn chữa lành trọn vẹn', 'high_freq+vocal_1': 'Độ phân giải dải cao khiến giọng hát ấm áp và dịu dàng, âm hình chất âm sạch sẽ khiến mệt mỏi được sóng âm khẽ xoa phẳng', 'high_freq+vocal_2': 'Tăng ích dải cao khiến giọng hát càng thư thái và trong trẻo, xử lý dải tần mượt mà khiến nội tâm trở lại an ổn và bình yên', 'high_freq+vocal_3': 'Dải cao tinh tế làm nền cho giọng hát trong trẻo, năng lượng âm tần mềm mại mang lại sự chữa lành ấm áp, mọi cảm xúc đều được đón nhận', 'high_freq+vocal_4': 'Dải cao trong trẻo nâng giọng hát chữa lành trải rộng, dải cao tự nhiên và mềm mại mang lại cảm giác an tâm, như được ôm trọn một cách dịu dàng', 'high_freq+vocal_5': 'Dải cao mượt mà loại bỏ tạp âm giọng hát, chất âm trong sáng tự nhiên khiến giọng hát mềm mại giúp bạn buông bỏ căng thẳng', 'loudness+rhythm_1': 'Âm lượng ôn hòa hòa cùng nhịp điệu an ổn, đáp ứng động ổn định khiến phách mạnh khẽ rơi, mỗi phách đều đang chữa lành', 'loudness+rhythm_2': 'Âm lượng ấm áp đẩy căng sức căng của nhịp điệu thư thái, kiểm soát áp lực âm tinh tế khiến cảm giác an toàn chữa lành lan tỏa trong giai điệu', 'loudness+space_size': 'Âm lượng mềm mại lấp đầy tiếng vang không gian chữa lành, sức căng áp lực âm lan tỏa tự nhiên khiến nội tâm tìm lại sự an ổn và bình yên', 'loudness+stereo_width': 'Âm lượng ôn hòa trải khắp trường âm thanh nổi rộng mở, động học vòm đầy đặn khiến hơi ấm chữa lành bao trọn toàn thân', 'loudness+vocal_1': 'Giọng hát chữa lành được giải phóng cùng âm lượng ấm áp, mức áp lực âm vừa vặn khiến mọi bất an được tiếng ngân xoa phẳng', 'loudness+vocal_2': 'Giọng hát có chất liệu hòa cùng tăng ích âm lượng đầy đặn, đường giọng mượt mà, thư thái đầy sức bao dung, từ từ xoa dịu cảm giác mệt mỏi', 'loudness+vocal_3': 'Âm lượng ấm áp ban cho giọng hát sức căng nhẹ nhàng, cộng hưởng ổn định khiến sóng âm chữa lành an tâm nâng đỡ bạn một cách vững vàng', 'rhythm+space_size': 'Nhịp điệu nhẹ nhàng lấp đầy tiếng vang không gian sâu thẳm, chiều sâu âm học mềm mại khiến không khí chữa lành lan tỏa, nội tâm bình yên', 'rhythm+stereo_width': 'Nhịp điệu thư thái lan tỏa trái phải trong trường âm thanh nổi, nhịp điệu mềm mại bao trùm mọi hướng, được bao bọc bởi sự chữa lành', 'rhythm+vocal': 'Giọng hát có chất liệu bám sát nhịp điệu nhẹ nhàng, chuyển động tức thời tuyệt vời khiến đường giọng thư thái hòa cùng phách, chữa lành những cảm xúc nhỏ', 'space_size+stereo_width': 'Tiếng vang không gian chữa lành chồng lên trường âm thanh nổi toàn cảnh, sóng âm mềm mại bao quanh tự nhiên, mang lại cảm giác yên bình và an tâm', 'space_size+vocal_1': 'Giọng hát thư thái vang vọng trong tiếng vang không gian trong suốt, ngân dài mềm mại lan tỏa tự nhiên, dư âm chữa lành khiến tâm trạng bình yên', 'space_size+vocal_2': 'Giọng hát chữa lành khẽ vang vọng trong tiếng vang không gian, cộng hưởng âm học ổn định mang lại sự ấm áp an tâm, được nâng đỡ vững vàng', 'stereo_width+vocal_1': 'Giọng hát chữa lành trải rộng trong trường âm thanh nổi, định vị âm hình chuẩn xác khiến cảm giác bao quanh nhẹ nhàng nâng cảm giác an toàn chữa lành lên trọn vẹn', 'stereo_width+vocal_2': 'Trường âm thanh nổi rộng mở nâng đỡ ổn định giọng hát mềm mại, âm tần vòm đầy đặn tạo nên vòng ôm dịu dàng, chữa lành mệt mỏi', 'stereo_width+vocal_3': 'Giọng hát có chất liệu lan tỏa trong trường âm thanh nổi rộng mở, âm hình mềm mại mang lại sự chữa lành ấm áp, được giọng hát nâng đỡ vững vàng'};
        r8['dimPairs'] = r0;
        r7 = {};
        r0 = 'Bình yên là nơi gió dừng bước';
        r7['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm ấm áp đan xen dải cao mềm mại, độ phân giải tinh tế lọc sạch cảm giác gắt, khiến tâm trạng bình yên như mặt nước không gió', 'loudness+richness': 'Hòa âm thanh đạm hòa cùng biên độ âm lượng tiết chế, ổn định, kiểm soát động tinh tế khiến mọi thứ trở về tĩnh lặng', 'rhythm+richness': 'Hòa âm thư thái bám sát nhịp điệu khoan thai, đáp ứng tức thời mềm mại khiến trái tim xao động dần lặng xuống', 'richness+space_size': 'Hòa âm mềm mại hòa vào tiếng vang không gian tĩnh mịch, chiều sâu âm học sâu thẳm như mặt hồ an bình, không gợn sóng', 'richness+stereo_width': 'Hòa âm ấm áp trải rộng cân bằng trong trường âm thanh nổi mênh mông, âm hình ổn định khiến người nghe đắm chìm trong tĩnh lặng và ung dung', 'richness+vocal_1': 'Giọng hát có chất liệu hòa quyện liền mạch với hòa âm bình hòa, xử lý dải tần mượt mà khiến đường giọng tĩnh lặng ổn định như nước', 'richness+vocal_2': 'Hòa âm tinh tế nâng đỡ ổn định giọng hát trong trẻo, khả năng hòa bè tuyệt vời khiến sự xao động trong lòng dần lắng xuống', 'richness+vocal_3': 'Hòa âm ấm áp ôm trọn giọng hát có chất liệu, âm hình trung âm rõ ràng mang lại sự trong sạch, thư thái, nội tâm không gợn sóng', 'richness+vocal_4': 'Hòa âm bình hòa đan xen tinh tế với giọng hát trong trẻo, khả năng kiểm soát âm học tuyệt vời khiến vạn vật trở về tĩnh mịch', 'richness+vocal_5': 'Hòa âm mềm mại bao quanh giọng hát trong trẻo, nền âm thanh chắc chắn và bình yên khiến tâm cảnh như mặt hồ soi bóng trăng', 'high_freq+loudness_1': 'Dải cao mềm dịu hòa cùng chuyển biến âm lượng ổn định, kiểm soát áp lực âm mượt mà xóa bỏ cảm giác gắt, chỉ còn lại sự bình hòa, an yên', 'high_freq+loudness_2': 'Dải cao trong suốt hòa vào biên độ âm lượng nhẹ nhàng, sức căng động ổn định khiến tâm trạng tĩnh lặng như mặt nước phản chiếu bầu trời', 'high_freq+rhythm_1': 'Dải cao mềm mại khớp chính xác với nhịp điệu đều đặn, đáp ứng tức thời thư thái khiến tâm trí dần trở về bình yên', 'high_freq+rhythm_2': 'Dải cao trong trẻo lặng lẽ chảy theo nhịp điệu khoan thai, khả năng bám động tuyệt vời mang lại sự an ổn tĩnh lặng như mặt hồ', 'high_freq+rhythm_3': 'Dải cao tinh tế điểm xuyết nhịp trống khoan thai, chuyển động âm học mềm mại khiến nội tâm không còn một gợn sóng', 'high_freq+space_size': 'Dải cao tinh khiết khúc xạ trong tiếng vang không gian tĩnh mịch, âm bội âm học nhẹ nhàng khiến không khí bình hòa lặng lẽ lan tỏa', 'high_freq+stereo_width_1': 'Dải cao mềm mại dạo bước nhẹ trong trường âm thanh nổi, sức căng âm hình ổn định mang lại sự tĩnh lặng và bình hòa đến cực độ', 'high_freq+stereo_width_2': 'Dải cao sáng trong dạo bước trong trường âm thanh nổi rộng mở, âm hình sạch sẽ tạo nên sự tĩnh mịch như mặt hồ không gió', 'high_freq+vocal_1': 'Độ phân giải dải cao khiến giọng hát ấm áp và trong suốt, âm hình chất âm sạch sẽ mang lại cảm giác bình hòa, tĩnh tâm không bị quấy nhiễu', 'high_freq+vocal_2': 'Tăng ích dải cao khiến giọng hát càng trong trẻo, xử lý dải tần mượt mà khiến sự phiền muộn dần tan biến trong tĩnh lặng', 'high_freq+vocal_3': 'Dải cao tinh tế làm nền cho giọng hát có chất liệu, năng lượng âm tần tiết chế khiến tâm hồ bình thản giữ trọn một mảng yên tĩnh', 'high_freq+vocal_4': 'Dải cao trong trẻo nâng giọng hát tĩnh lặng trải rộng, dải cao tự nhiên khiến vạn vật đều lặng xuống trong sóng âm', 'high_freq+vocal_5': 'Dải cao mượt mà loại bỏ tạp âm giọng hát, chất âm trong sáng tự nhiên toát lên vẻ bình hòa, thản nhiên vừa vặn', 'loudness+rhythm_1': 'Âm lượng thanh đạm hòa cùng nhịp điệu ổn định, đáp ứng động tuyệt vời khiến mỗi tiếng đều tĩnh lặng và ung dung', 'loudness+rhythm_2': 'Âm lượng ấm áp đẩy căng sức căng của nhịp điệu bình hòa, kiểm soát áp lực âm tinh tế khiến nội tâm tĩnh lặng như mặt hồ không gió', 'loudness+space_size': 'Âm lượng mềm mại lấp đầy tiếng vang không gian tĩnh mịch, sức căng áp lực âm lan tỏa tự nhiên khiến nội tâm bình yên, không gợn sóng', 'loudness+stereo_width': 'Âm lượng ổn định trải khắp trường âm thanh nổi rộng mở, động học vòm tiết chế khiến cảm giác tĩnh lặng chữa lành lan tỏa bình hòa', 'loudness+vocal_1': 'Giọng hát tĩnh lặng được giải phóng cùng âm lượng tiết chế, mức áp lực âm vừa vặn khiến tâm trạng ổn định, thản nhiên', 'loudness+vocal_2': 'Giọng hát có chất liệu hòa cùng tăng ích âm lượng ấm áp, đường giọng mượt mà, thư thái đầy sức bao dung, khiến nội tâm bình yên', 'loudness+vocal_3': 'Âm lượng ổn định ban cho giọng hát sức căng mềm mại, cộng hưởng thanh đạm khiến sự tĩnh mịch, an ổn như con thuyền đậu nơi bến vắng', 'rhythm+space_size': 'Nhịp điệu khoan thai lấp đầy tiếng vang không gian tĩnh mịch, chiều sâu âm học miên man khiến tâm hồ an bình giữ được sự yên tĩnh', 'rhythm+stereo_width': 'Nhịp điệu đều đặn lan tỏa trái phải trong trường âm thanh nổi, nhịp điệu mềm mại bao trùm mọi hướng, mang lại sự tĩnh lặng và thản nhiên', 'rhythm+vocal': 'Giọng hát có chất liệu bám sát nhịp điệu khoan thai, chuyển động tức thời tuyệt vời khiến đường giọng tĩnh lặng hòa cùng phách, lòng bình yên như nước', 'space_size+stereo_width': 'Tiếng vang không gian tĩnh mịch chồng lên trường âm thanh nổi toàn cảnh, sóng âm mềm mại bao quanh tự nhiên, an bình như mặt hồ không gió', 'space_size+vocal_1': 'Giọng hát tĩnh lặng vang vọng trong tiếng vang không gian trong suốt, ngân dài mềm mại lan tỏa tự nhiên, tâm trạng bình hòa, không gợn sóng', 'space_size+vocal_2': 'Giọng hát có chất liệu khẽ gợn trong tiếng vang không gian, cộng hưởng âm học ổn định mang lại sự thản nhiên tĩnh lặng, gió lặng nước yên', 'stereo_width+vocal_1': 'Giọng hát có chất liệu trải rộng trong trường âm thanh nổi, định vị âm hình chuẩn xác khiến cảm giác tĩnh lặng bình hòa bao quanh an ổn', 'stereo_width+vocal_2': 'Trường âm thanh nổi rộng mở nâng đỡ ổn định giọng hát thanh đạm, âm tần vòm thư thái mang lại sự bình hòa khiến lòng an yên', 'stereo_width+vocal_3': 'Giọng hát có chất liệu lan tỏa trong trường âm thanh nổi rộng mở, âm hình mềm mại mang lại sự bình yên như mặt hồ không gió'};
        r7['dimPairs'] = r0;
        r6 = {};
        r0 = 'Nỗi nhớ ẩn trong bài hát';
        r6['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm ấm áp đan xen dải cao trong trẻo, độ phân giải cao tái hiện từng kỷ niệm một cách tinh tế, nỗi nhớ hiện lên trong chất âm tuyệt mỹ', 'loudness+richness': 'Hòa âm đậm đà hòa cùng biên độ âm lượng tinh tế, kiểm soát động ấm áp khiến nỗi nhớ dâng lên trong lòng như thủy triều dịu dàng', 'rhythm+richness': 'Hòa âm dày dặn khớp với nhịp điệu khoan thai, cảm nghe ổn định khiến thời gian ngày cũ dần hiện về theo nốt nhạc', 'richness+space_size': 'Hòa âm ấm áp hòa vào tiếng vang không gian rộng mở, dư âm âm học miên man kéo dài nỗi nhớ, tràn đầy sự dịu dàng của ngày xưa', 'richness+stereo_width': 'Hòa âm tinh tế được trường âm thanh nổi chuẩn xác thu gọn, chất âm bao quanh khiến nỗi nhớ quấn quanh đáy lòng một cách dịu dàng', 'richness+vocal_1': 'Giọng hát có chất liệu hòa quyện liền mạch với hòa âm tuyệt mỹ, đường giọng thâm tình tái hiện trọn vẹn chất liệu cao cấp của nỗi nhớ', 'richness+vocal_2': 'Hòa âm mềm mại bao bọc từng lớp giọng hát ấm áp, khả năng hòa bè tuyệt vời khiến kỷ niệm khẽ vấn vít bên tai', 'richness+vocal_3': 'Hòa âm đậm đà nâng đỡ vững vàng giọng hát trong trẻo, tầng âm học chuyên nghiệp khiến thời gian cũ đầy hoài niệm dâng lên trong lòng', 'richness+vocal_4': 'Hòa âm nổi khối đan xen tinh tế với giọng hát thâm tình, dải tần khớp hoàn hảo khiến nỗi nhớ miên man được kể lại nhẹ nhàng', 'richness+vocal_5': 'Hòa âm đắm chìm bao quanh giọng hát một cách ấm áp, không gian âm học đầy chất liệu khiến nỗi nhớ ẩn vào từng giai điệu', 'high_freq+loudness_1': 'Dải cao trong trẻo hòa cùng chuyển biến âm lượng mượt mà, kiểm soát động mượt mà khiến quá khứ khẽ đung đưa bên tai', 'high_freq+loudness_2': 'Dải cao trong suốt hòa vào biên độ âm lượng tinh tế, áp lực âm mềm mại mang lại cảm giác hoài niệm đến cực độ, nỗi nhớ dần lan tỏa', 'high_freq+rhythm_1': 'Dải cao tinh khiết lưu chuyển trong nhịp điệu khoan thai, đáp ứng tức thời chuẩn xác khiến kỷ niệm dần rõ nét theo phách', 'high_freq+rhythm_2': 'Dải cao tinh tế khẽ gợn theo nhịp điệu chậm, cảm giác nhịp điệu đỉnh cao khiến nỗi nhớ ấm áp không ngừng lan tỏa', 'high_freq+rhythm_3': 'Dải cao thuần khiết điểm xuyết nhịp điệu nhẹ nhàng, khoảng lặng âm học vừa vặn khiến thời gian cũ được chiếu lại trong lòng', 'high_freq+space_size': 'Dải cao mượt mà khúc xạ trong tiếng vang không gian rộng mở, tạo chiều sâu cực mạnh, khiến nỗi nhớ mở ra như một bức tranh', 'high_freq+stereo_width_1': 'Dải cao linh hoạt dạo bước trong trường âm thanh nổi rộng lớn, cảm nghe vòm phân giải cao lập tức đẩy cảm xúc hoài niệm lên trọn vẹn', 'high_freq+stereo_width_2': 'Dải cao mềm mại hòa vào trường âm thanh nổi riêng tư, định vị âm hình chuẩn xác mang lại sự đồng hành độc quyền, nỗi nhớ như hình với bóng', 'high_freq+vocal_1': 'Độ phân giải dải cao đầy đặn, giọng hát thoát tục chạm tai, như gió nhẹ đưa những kỷ niệm quý giá khẽ thổi về tim', 'high_freq+vocal_2': 'Dải cao tinh tế mạ lên giọng hát một lớp sáng ấm áp, đường giọng miên man biến nỗi nhớ không lời thành lời bày tỏ cao cấp nhất', 'high_freq+vocal_3': 'Dải cao mềm mại như gió nhẹ phất qua giọng hát, xử lý dải tần sạch sẽ khiến thời gian cũ miên man hiện lại bên tai', 'high_freq+vocal_4': 'Dải cao thoát tục đan xen giọng hát có chất liệu sâu thẳm, tạo nên không gian nghe đầy chất điện ảnh, nỗi nhớ ẩn vào nốt nhạc', 'high_freq+vocal_5': 'Dải cao mượt mà loại bỏ tạp âm giọng hát, chất âm tự nhiên và cao cấp khiến trái tim lại được quá khứ khẽ lay động', 'loudness+rhythm_1': 'Chuyển biến âm lượng mượt mà hòa cùng nhịp điệu chậm rãi, cảm nghe như cảnh quay chậm trong phim khiến nỗi nhớ lan tỏa', 'loudness+rhythm_2': 'Âm lượng mềm mại dâng lên trong nhịp điệu lười biếng, dải động dễ chịu khiến những kỷ niệm quý giá dần hiện rõ', 'loudness+space_size': 'Kiểm soát âm lượng tinh vi lấp đầy tiếng vang không gian sâu thẳm, tạo nên bức tranh âm học đầy bao dung, tràn đầy nỗi nhớ', 'loudness+stereo_width': 'Âm lượng tinh tế hòa cùng trường âm thanh nổi rộng mở một cách tự nhiên, cảm nghe bao bọc đắm chìm khiến nỗi nhớ vấn vít bên tai', 'loudness+vocal_1': 'Giọng hát có chất liệu khẽ ngân hòa cùng khả năng nắm bắt âm lượng tinh tế, giữ lại hơi thở chân thực, khiến nỗi nhớ lan tỏa trong lòng', 'loudness+vocal_2': 'Nén âm lượng mượt mà khiến giọng hát thâm tình toát lên vẻ mượt mà, chất âm ấm áp khiến người nghe không khỏi nhớ về ngày trước', 'loudness+vocal_3': 'Âm lượng tiết chế và cân bằng nâng đỡ giọng hát thoát tục, nỗi nhớ nặng trĩu lập tức được sóng âm dịu dàng hóa giải', 'rhythm+space_size': 'Nhịp điệu khoan thai lan tỏa trong tiếng vang không gian rộng mở, thời gian như bị kéo dài, bức tranh kỷ niệm từ từ mở ra', 'rhythm+stereo_width': 'Nhịp điệu lười biếng đung đưa trong trường âm thanh nổi rộng lớn, cảm giác bao bọc mãnh liệt đưa bạn vào hành trình nỗi nhớ xuyên thời gian', 'rhythm+vocal': 'Giọng hát đầy từ tính khớp chính xác với nhịp điệu chậm rãi, cảm giác nhịp điệu tự nhiên và thư thái khiến nỗi nhớ khẽ tuôn chảy', 'space_size+stereo_width': 'Tiếng vang không gian rộng mở kết hợp trường âm thanh nổi toàn cảnh, tạo nên kết giới kỷ niệm đầy chiều sâu, tràn ngập nỗi nhớ', 'space_size+vocal_1': 'Giọng hát thoát tục vang vọng trong không gian vang rộng mở, dư âm trong trẻo phác họa tầng lớp phong phú, lại một lần nữa nhớ về bạn', 'space_size+vocal_2': 'Giọng hát đầy sức xuyên thấu vang khắp không gian vang vô tận, ngân dài âm học vừa vặn khiến kỷ niệm dâng trào', 'stereo_width+vocal_1': 'Giọng hát vòm dạo bước trong trường âm thanh nổi riêng tư, cảm giác áp tai phân giải cao khiến bạn an tâm tận hưởng việc được nỗi nhớ bao bọc', 'stereo_width+vocal_2': 'Trường âm thanh nổi rộng mở bao bọc vững vàng giọng hát có chất liệu, trải nghiệm âm học sâu lắng và đắm chìm khiến thời gian cũ ấm áp hiện về', 'stereo_width+vocal_3': 'Trường âm thanh nổi đỉnh cao tạo không gian riêng cho giọng hát có chất liệu, định vị âm hình tuyệt vời khiến nỗi nhớ trở nên có dấu vết'};
        r6['dimPairs'] = r0;
        r5 = {};
        r0 = 'Cô đơn là bóng hình lặng lẽ';
        r5['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm lạnh lẽo đan xen dải cao trong suốt, độ phân giải tinh tế mang lại chất liệu, bóng hình lặng lẽ tận hưởng khoảng riêng trong gió chiều', 'loudness+richness': 'Hòa âm trầm lắng hòa cùng âm lượng tiết chế, áp lực âm ổn định cách ly mọi huyên náo, khiến bóng hình lặng lẽ đắm chìm trong tĩnh lặng cô đơn', 'rhythm+richness': 'Hòa âm thư thái đi theo nhịp điệu lười biếng, đáp ứng tức thời ổn định khiến bước chân im lặng cũng mang chất điện ảnh', 'richness+space_size': 'Hòa âm lạnh lẽo hòa vào tiếng vang không gian sâu thẳm, chiều sâu âm học trống trải khiến dư âm cô đơn miên man trong màn đêm', 'richness+stereo_width': 'Hòa âm mềm mại được trường âm thanh nổi thu gọn chuẩn xác, âm hình sạch sẽ khiến bóng hình cô đơn được màn đêm bao bọc dịu dàng', 'richness+vocal_1': 'Giọng hát có chất liệu hòa quyện liền mạch với hòa âm lạnh lẽo, xử lý dải tần mượt mà khiến đường giọng độc hành mang sẵn vẻ cao cấp', 'richness+vocal_2': 'Hòa âm tinh tế bao bọc ổn định giọng hát trong trẻo, khả năng hòa bè tuyệt vời khiến tâm trạng cô đơn lặng lẽ lắng xuống trong gió chiều', 'richness+vocal_3': 'Hòa âm trầm lắng nâng đỡ vững vàng giọng hát có chất liệu, âm hình trung âm rõ ràng cách ly huyên náo bên ngoài, để lại cho cô đơn một mảng tĩnh lặng', 'richness+vocal_4': 'Giọng hát có chất liệu đan xen tinh tế với hòa âm nhẹ nhàng, khả năng kiểm soát âm học tuyệt vời khiến hành trình độc bước tràn đầy chất thơ', 'richness+vocal_5': 'Hòa âm lạnh lẽo bao quanh giọng hát có chất liệu, nền âm thanh chắc chắn khiến bước chân cô đơn khẽ vang trong sâu thẳm màn đêm', 'high_freq+loudness_1': 'Dải cao trong trẻo hòa cùng âm lượng tiết chế, áp lực âm ổn định cách ly huyên náo, khiến bóng hình lặng lẽ tận hưởng sự riêng tư an bình', 'high_freq+loudness_2': 'Dải cao lạnh lẽo hòa vào biên độ âm lượng mềm mại, sức căng động ổn định khiến thế giới lặng yên, chỉ còn tiếng thở ung dung', 'high_freq+rhythm_1': 'Dải cao trong trẻo khớp chính xác với nhịp điệu khoan thai, đáp ứng tức thời êm ả khiến bước chân cô đơn cũng trở nên chắc chắn, ung dung', 'high_freq+rhythm_2': 'Dải cao tinh tế đung đưa theo nhịp điệu chậm, khả năng bám động tuyệt vời khiến tâm trạng trong màn đêm trở nên an ổn, tĩnh lặng', 'high_freq+rhythm_3': 'Dải cao sáng trong điểm xuyết nhịp trống khoan thai, chuyển động âm học rõ ràng khiến bóng hình giữa đám đông vẫn giữ được sự tỉnh táo', 'high_freq+space_size': 'Dải cao tinh khiết khúc xạ trong tiếng vang không gian trống trải, âm bội âm học lạnh lẽo khiến không khí cô đơn càng thêm trầm lắng', 'high_freq+stereo_width_1': 'Dải cao linh hoạt dạo bước nhẹ trong trường âm thanh nổi, sức căng âm hình tiết chế khiến sự cô đơn trong màn đêm trở nên tĩnh lặng khác thường', 'high_freq+stereo_width_2': 'Dải cao sáng trong khẽ thu hẹp trong trường âm thanh nổi, âm hình sạch sẽ tạo nên cảm giác lạnh lẽo tách biệt, không tranh với đời', 'high_freq+vocal_1': 'Độ phân giải dải cao khiến giọng hát lạnh lẽo và hư ảo, âm hình chất âm sạch sẽ khiến cảm xúc lắng sâu trong lòng được giải phóng trong tĩnh lặng', 'high_freq+vocal_2': 'Tăng ích dải cao khiến giọng hát càng trong trẻo và mảnh mai, xử lý dải tần mượt mà đồng hành cùng bạn độc bước qua màn đêm và con đường', 'high_freq+vocal_3': 'Dải cao tinh tế làm nền cho giọng hát có chất liệu, năng lượng âm tần tiết chế khiến bóng hình cô đơn cũng tỏa ra hơi thở tĩnh lặng', 'high_freq+vocal_4': 'Dải cao trong trẻo nâng giọng hát lạnh lẽo trải rộng, dải cao tự nhiên khiến tâm sự ẩn trong giai điệu, không bị quấy nhiễu', 'high_freq+vocal_5': 'Dải cao mượt mà loại bỏ tạp âm giọng hát, chất âm trong sáng tự nhiên khiến bước đi trong màn đêm se lạnh thêm ung dung', 'loudness+rhythm_1': 'Âm lượng tiết chế hòa cùng nhịp điệu chậm rãi, đáp ứng động ổn định khiến bước chân tĩnh lặng mang thêm chất liệu trầm ổn', 'loudness+rhythm_2': 'Âm lượng nhẹ nhàng đẩy căng sức căng của nhịp điệu khoan thai, kiểm soát áp lực âm tinh tế khiến tâm trạng trầm lắng được giai điệu dịu dàng đón nhận', 'loudness+space_size': 'Âm lượng yếu ớt lấp đầy tiếng vang không gian trống trải, sức căng áp lực âm lan tỏa tự nhiên khiến vạn vật chìm vào im lặng, chỉ còn lại sự riêng tư', 'loudness+stereo_width': 'Âm lượng trầm lắng trải khắp trường âm thanh nổi, động học vòm thu hẹp mang lại cảm giác an toàn, khiến bóng hình cô đơn không bị quấy nhiễu', 'loudness+vocal_1': 'Giọng hát có chất liệu khẽ ngân cùng âm lượng tiết chế, mức áp lực âm vừa vặn khiến lời tự sự không người nghe cũng trở nên quyến rũ', 'loudness+vocal_2': 'Giọng hát khoan thai hòa cùng tăng ích âm lượng thanh đạm, đường giọng mượt mà đầy sức bao dung, lặng lẽ băng qua màn đêm tĩnh lặng', 'loudness+vocal_3': 'Âm lượng khoan thai ban cho giọng hát sức căng mềm mại, cộng hưởng nhẹ nhàng khiến tâm cảnh trong màn đêm se lạnh giữ được sự bình yên, thản nhiên', 'rhythm+space_size': 'Nhịp điệu chậm rãi lấp đầy tiếng vang không gian trống trải, chiều sâu âm học miên man khiến bước đi không bạn đồng hành cũng trở nên ung dung', 'rhythm+stereo_width': 'Nhịp điệu khoan thai đung đưa trái phải trong trường âm thanh nổi, nhịp điệu lạnh lẽo bao trùm mọi hướng, một mình bước vào màn đêm quyến rũ', 'rhythm+vocal': 'Giọng hát có chất liệu bám sát nhịp điệu chậm rãi, chuyển động tức thời tuyệt vời khiến đường giọng se lạnh hòa cùng phách chậm, tâm sự khẽ tuôn chảy', 'space_size+stereo_width': 'Tiếng vang không gian lạnh lẽo chồng lên trường âm thanh nổi toàn cảnh, sóng âm tiết chế bao quanh tự nhiên, khiến thế giới huyên náo lập tức tĩnh lặng', 'space_size+vocal_1': 'Giọng hát có chất liệu vang vọng trong tiếng vang không gian trống trải, ngân dài lạnh lẽo lan tỏa tự nhiên, khiến tâm trạng cô đơn trầm lắng', 'space_size+vocal_2': 'Giọng hát thoát tục khẽ gợn trong tiếng vang không gian, cộng hưởng âm học ổn định đồng hành cùng màn đêm dần sâu, một mình ung dung tiến bước', 'stereo_width+vocal_1': 'Giọng hát có chất liệu được trường âm thanh nổi hẹp bao quanh một cách lạnh lẽo, định vị âm hình chuẩn xác khiến thời gian riêng tư không bị quấy nhiễu', 'stereo_width+vocal_2': 'Trường âm thanh nổi rộng mở nâng đỡ ổn định giọng hát mảnh mai, âm tần vòm tiết chế cách ly bóng hình cô đơn với huyên náo', 'stereo_width+vocal_3': 'Giọng hát có chất liệu trải rộng thanh đạm trong trường âm thanh nổi, âm hình sạch sẽ đồng hành cùng bạn băng qua màn đêm, tĩnh lặng và quyến rũ'};
        r5['dimPairs'] = r0;
        r4 = {};
        r0 = 'Nỗi buồn là giọt nước mắt pixel';
        r4['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm nhẹ nhàng, dải cao khẽ rung như những pixel li ti, cảm xúc xuống thấp trong khoảnh khắc này được ôm trọn trong tĩnh lặng', 'loudness+richness': 'Hòa âm trầm chậm, âm lượng nhẹ và hư ảo như pixel rơi, trái tim mệt mỏi cuối cùng cũng có thể buông bỏ phòng bị', 'rhythm+richness': 'Hòa âm khoan thai, nhịp điệu chậm lại, hãy để chút chua xót trong lòng từ từ được giải tỏa theo giai điệu', 'richness+space_size': 'Hòa âm vang vọng nơi không gian rộng rãi, tiếng vang miên man, để lại không gian thở cho nỗi buồn không nơi nương tựa', 'richness+stereo_width': 'Hòa âm thu hẹp, trường âm thanh hóa thành tấm chăn mỏng nhưng ấm áp, khẽ ôm trọn sự yếu ớt đôi lúc', 'richness+vocal_1': 'Giọng hát và hòa âm đan xen nhẹ nhàng, đường giọng khẽ rung, cho phép bản thân có một giấc mơ dễ vỡ trong khoảnh khắc này', 'richness+vocal_2': 'Hòa âm cuốn giọng hát chầm chậm lắng xuống, không cần lúc nào cũng mạnh mẽ, bài hát này nâng đỡ mọi giọt nước mắt của bạn', 'richness+vocal_3': 'Hòa âm xếp lớp quấn quanh giọng hát, mang lại sự đồng hành an tâm, hãy để nỗi tủi thân hóa thành pixel rồi tan theo gió', 'richness+vocal_4': 'Hòa âm trầm chậm nâng đỡ giọng hát, hóa giải chút chua xót trong đáy lòng, mệt rồi thì hãy nghỉ một lát trong sự dịu dàng này', 'richness+vocal_5': 'Hòa âm se lạnh bao bọc giọng hát, đường giọng khẽ rung, mọi điều không vui đều được thấu hiểu trong khoảnh khắc này', 'high_freq+loudness_1': 'Dải cao li ti rung lên, âm lượng nhẹ nhàng như pixel bong ra, mượn thời lượng một bài hát để giải phóng sự yếu mềm', 'high_freq+loudness_2': 'Dải cao yếu ớt, âm lượng khoan thai, như một cơn mưa xuân không tiếng, lặng lẽ gột rửa sự nặng nề trong lòng', 'high_freq+rhythm_1': 'Dải cao li ti, nhịp điệu chậm bước, để những tâm sự nặng nề dần được phách nhạc hóa giải', 'high_freq+rhythm_2': 'Dải cao trượt theo phách như giọt nước, đôi lúc xuống thấp cũng không sao, hãy để âm nhạc cùng bạn lặng im', 'high_freq+rhythm_3': 'Dải cao khẽ điểm xuyết phách chậm, đường giọng khẽ rung, chút chua xót trong đáy lòng dần lắng xuống trong sự vỗ về của nốt nhạc', 'high_freq+space_size': 'Dải cao tan ra như pixel vỡ, tiếng vang miên man, tìm cho nỗi u uất trong lòng một lối thoát an toàn để giãi bày', 'high_freq+stereo_width_1': 'Dải cao khẽ rung bên tai, trường âm thanh thu lại đôi chút, chữa lành trong vũ trụ nhỏ chỉ thuộc về bạn', 'high_freq+stereo_width_2': 'Dải cao lạnh lẽo, trường âm thanh mỏng manh bao bọc toàn thân, hãy để bài hát này khẽ mang đi mọi phiền muộn của bạn', 'high_freq+vocal_1': 'Giọng hát dải cao lạnh lẽo, chất âm hơi chát, biến tâm sự nặng ngàn cân thành nước mắt, hãy khóc một trận thật thỏa thuê', 'high_freq+vocal_2': 'Dải cao thê lương vấn vít, đường giọng khẽ rung, dù nước mắt có vỡ đê thì vẫn có giai điệu dịu dàng này nâng đỡ bạn', 'high_freq+vocal_3': 'Giọng hát dải cao nhẹ bẫng, đường giọng khẽ rung, đừng sợ sự yếu ớt đôi lúc, hãy để âm nhạc mang đi chút chua xót của bạn', 'high_freq+vocal_4': 'Dải cao khẽ quấn giọng hát, lộ ra nền âm se lạnh, ở đây hãy buông bỏ phòng bị, không cần giả vờ mạnh mẽ nữa', 'high_freq+vocal_5': 'Giọng hát dải cao li ti như nước mắt bong ra, khi lồng ngực ngột ngạt, hãy cứ ngân nga theo giai điệu và khóc cho thỏa', 'loudness+rhythm_1': 'Âm lượng nhẹ và chậm, phách nhạc chậm lại, đừng vội vã, hãy dừng lại lắng nghe nỗi buồn và sự mềm yếu trong lòng', 'loudness+rhythm_2': 'Âm lượng ngân nga theo nhịp điệu chậm, khi cảm giác bất lực ập đến, hãy để âm nhạc trở thành bờ vai bạn tựa vào', 'loudness+space_size': 'Âm lượng yếu ớt lan trong tiếng vang, xung quanh lặng xuống, để lại chút không gian cho nỗi buồn được giải phóng', 'loudness+stereo_width': 'Âm thanh trầm thấp bao quanh trong trường âm thanh, như một vòng tay ấm áp, khẽ xoa dịu vết thương trong lòng bạn', 'loudness+vocal_1': 'Giọng hát trầm và nghẹn ngào khẽ ngân, lực nhẹ nhàng, cảm xúc yếu ớt theo đường giọng tuôn chảy, được chữa lành dần dần', 'loudness+vocal_2': 'Giọng hát trầm buồn khẽ hát, âm lượng nhẹ bẫng, cho phép mình đôi lúc không vui, nỗi tủi thân tích tụ sẽ tan đi', 'loudness+vocal_3': 'Giọng hát tối và yếu khẽ rơi cùng âm thanh trầm, không cần lúc nào cũng giả vờ mạnh mẽ, nước mắt cũng là khởi đầu của sự giải tỏa', 'rhythm+space_size': 'Nhịp điệu chậm lan trong trường âm thanh trống trải, khoảng thời gian tràn ngập nỗi buồn, có giai điệu đồng hành thì không đến mức khó khăn', 'rhythm+stereo_width': 'Nhịp điệu chậm đung đưa trong trường âm thanh rộng mở, đôi lúc sa ngã cũng không sao, những nốt nhạc dịu dàng sẽ nâng đỡ bạn', 'rhythm+vocal': 'Giọng hát khẽ hát theo phách chậm, để cảm xúc bị kìm nén bấy lâu được giải phóng thật thỏa thuê và an toàn theo bài hát này', 'space_size+stereo_width': 'Trường âm thanh trống trải bao quanh bạn, cách ly huyên náo bên ngoài, tủi thân và buồn bã ở đây không cần gồng mình nữa', 'space_size+vocal_1': 'Giọng hát vang vọng nơi xa, dư âm miên man, khi lồng ngực ngột ngạt, hãy để tiếng hát thay bạn kể nỗi buồn', 'space_size+vocal_2': 'Đường giọng run rẩy vang trong khoảng trống mênh mông, khi cô đơn hãy nhớ, muốn khóc thì cứ khóc thật thoải mái', 'stereo_width+vocal_1': 'Đường giọng vỡ vụn quấn quanh trường âm thanh hẹp, như một vòng ôm riêng tư, đón nhận mọi điều chưa hoàn hảo của bạn', 'stereo_width+vocal_2': 'Trường âm thanh nổi nâng đỡ giọng hát một cách vững vàng, hóa giải chút chua xót trong đáy lòng, cảm xúc yếu ớt ở đây rất an toàn', 'stereo_width+vocal_3': 'Giọng hát nhẹ nhàng trải rộng trong trường âm thanh trái phải, xoa dịu hơi thở run rẩy, nước mắt pixel rồi sẽ hóa thành ánh sáng'};
        r4['dimPairs'] = r0;
        r3 = {};
        r0 = 'Thâm tình là một tách cà phê';
        r3['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm đậm đà như chút rượu lên men, dải cao khẽ rung nhẹ, câu chuyện ẩn trong đáy lòng đón vị ngọt hậu trong giai điệu', 'loudness+richness': 'Hòa âm trầm ấm, âm lượng nhẹ và tiết chế, muôn ngàn suy nghĩ dần được hóa giải trong sự đồng hành dịu dàng', 'rhythm+richness': 'Hòa âm ôn hòa, khoan thai, nhịp điệu chậm bước, những suy nghĩ dâng trào dần tan đi theo nốt nhạc linh hoạt', 'richness+space_size': 'Hòa âm vang vọng nơi không gian rộng rãi, tiếng vang miên man, chút ngọt hậu của kỷ niệm rồi sẽ hóa thành hơi ấm được chữa lành', 'richness+stereo_width': 'Hòa âm khẽ thu lại, trường âm thanh hóa thành góc yên tĩnh, lời thì thầm trong đáy lòng có giai điệu lặng lẽ lắng nghe', 'richness+vocal_1': 'Giọng hát hòa cùng hòa âm đậm đà, đường giọng trầm ấm, những điều chưa nói ra hãy để bài hát này thay bạn nói', 'richness+vocal_2': 'Hòa âm dịu dàng bao bọc giọng hát, ngàn lời vạn ý trong lòng đều được giải tỏa trong trường âm thanh đậm đà và bao dung này', 'richness+vocal_3': 'Hòa âm ấm áp nâng đỡ vững vàng giọng hát, những tâm trạng rối rắm cứ mượn giai điệu tuyệt đẹp mà giãi bày thỏa thuê', 'richness+vocal_4': 'Hòa âm và giọng hát đan xen nhẹ nhàng, thấu hiểu gợn sóng nhỏ trong lòng bạn, khi tĩnh lặng có âm nhạc mang lại an ủi sâu sắc', 'richness+vocal_5': 'Hòa âm ấm áp khẽ bao quanh giọng hát, để đáy lòng hoàn toàn buông bỏ phòng bị, gửi mọi suy nghĩ cho giai điệu', 'high_freq+loudness_1': 'Dải cao sáng trong khẽ rung, âm lượng mềm và tiết chế, những suy nghĩ rối ren đều được nốt nhạc ấm áp lặng lẽ hóa giải', 'high_freq+loudness_2': 'Dải cao mềm và trầm lắng, âm lượng vững vàng, những tâm trạng khó nói thành lời tìm được lối thoát trong nốt nhạc', 'high_freq+rhythm_1': 'Dải cao trong trẻo và mịn màng, nhịp điệu chậm bước, đừng vội vã, hãy để giai điệu xoa phẳng vầng trán đang nhíu lại của bạn', 'high_freq+rhythm_2': 'Dải cao khẽ nhảy theo phách, sau khi nếm dư vị kỷ niệm, những suy nghĩ dâng trào rồi sẽ đón sự bình yên và giải tỏa', 'high_freq+rhythm_3': 'Dải cao nhẹ nhàng điểm xuyết phách chậm, trong đường giọng khẽ rung, những tâm nguyện không biết tỏ cùng ai đều có giai điệu ấm áp thấu hiểu', 'high_freq+space_size': 'Dải cao lan tỏa nơi không gian rộng rãi, tiếng vang miên man, để lại cho kỷ niệm một không gian an toàn để an trú và giải tỏa', 'high_freq+stereo_width_1': 'Dải cao khẽ dạo bên tai, trường âm thanh thu lại để dành cho bạn một góc tĩnh lặng, lặng lẽ chờ tâm trạng hoàn toàn quang đãng', 'high_freq+stereo_width_2': 'Dải cao thanh đạm và mềm mại, trường âm thanh khẽ bao bọc, mọi hương vị của quá khứ có bài hát này cùng bạn nếm trải', 'high_freq+vocal_1': 'Giọng hát dải cao khẽ rung, chất âm đậm đà, cảm xúc ẩn trong tim chi bằng cứ theo giai điệu ấm áp mà nói hết ra', 'high_freq+vocal_2': 'Dải cao mềm mại vấn vít giọng hát, trong hơi thở khoan thai, mọi hương vị khó nói thành lời đều có âm nhạc ấm áp làm bạn', 'high_freq+vocal_3': 'Giọng hát dải cao nhẹ nhàng, đường giọng ôn hòa, những suy nghĩ ẩn trong từng câu hát luôn được giai điệu khẽ nâng đỡ', 'high_freq+vocal_4': 'Dải cao khẽ quấn giọng hát, thêm chút nền âm đậm đà, khi không biết bắt đầu từ đâu thì hãy lặng lẽ nghe hết bài hát này', 'high_freq+vocal_5': 'Giọng hát dải cao li ti khẽ rung, gợn sóng trong lòng đôi lúc, hãy yên tâm gửi cho giai điệu để nó dịu dàng bày tỏ', 'loudness+rhythm_1': 'Âm lượng nhẹ nhàng, phách nhạc khoan thai, chậm lại bước chân vội vã, tận hưởng chút an yên trong âm nhạc', 'loudness+rhythm_2': 'Âm lượng ngân nga theo nhịp điệu chậm, suy nghĩ nhiều cũng không sao, luôn có phách nhạc dịu dàng sẵn lòng cùng bạn gánh vác', 'loudness+space_size': 'Âm lượng nhẹ nhàng lan trong tiếng vang, mang lại không khí trong lành, để kỷ niệm dần thăng hoa trong bầu không khí tĩnh mịch', 'loudness+stereo_width': 'Âm thanh trầm và trầm lắng bao quanh trong trường âm thanh, như một cơn gió ấm khẽ ôm lấy, giai điệu thấu hiểu những điều chưa nói ra', 'loudness+vocal_1': 'Giọng hát trầm thấp khẽ ngân, lực nhẹ nhàng, cảm xúc trầm lắng theo giai điệu tuôn chảy, tâm cảnh dần trở nên nhẹ nhõm', 'loudness+vocal_2': 'Giọng hát thâm tình khẽ hát, âm lượng nhẹ và tiết chế, muôn ngàn cảm khái mượn bài hát cảm động này để được giải phóng trọn vẹn', 'loudness+vocal_3': 'Giọng hát dịu dàng khẽ rơi cùng âm thanh trầm, thêm chút đậm đà cho đường giọng, câu chuyện ngày cũ không cần phải cưỡng cầu nữa', 'rhythm+space_size': 'Nhịp điệu chậm lan nơi không gian rộng rãi, thời gian trở nên dịu dàng, vừa đủ để nếm trải vẻ đẹp được thời gian lắng đọng', 'rhythm+stereo_width': 'Nhịp điệu chậm đung đưa trong trường âm thanh rộng mở, mang lại cảm giác nhẹ nhàng, những tâm sự rối ren dần được giải tỏa theo giai điệu dịu êm', 'rhythm+vocal': 'Giọng hát khẽ ngân theo phách chậm, giai điệu đậm đà như dòng suối ấm, mọi tâm trạng đều được dịu dàng đón nhận trong khoảnh khắc này', 'space_size+stereo_width': 'Trường âm thanh rộng mở bao quanh bạn, hơi ấm mang lại bầu không khí an bình, những suy nghĩ không nơi nương tựa rồi sẽ tan đi', 'space_size+vocal_1': 'Giọng hát vang vọng dịu dàng nơi xa, dư âm miên man và đậm đà, muôn ngàn hương vị trong đáy lòng luôn có giai điệu lắng nghe', 'space_size+vocal_2': 'Đường giọng cảm động vang trong khoảng trống, khi tĩnh lặng hãy nhớ, những tiếng lòng không thể nói ra đều có thể gửi cho bài hát', 'stereo_width+vocal_1': 'Đường giọng trầm thấp quấn quanh trường âm thanh hẹp, mang lại cho bạn một vòng ôm riêng tư, thấu hiểu tâm sự chỉ chia sẻ với tiếng hát', 'stereo_width+vocal_2': 'Trường âm thanh nổi nâng đỡ giọng hát một cách vững vàng, mang lại sự đồng hành an tâm, ở đây có thể buông bỏ phòng bị và thoải mái là chính mình', 'stereo_width+vocal_3': 'Giọng hát khoan thai khẽ trải trong trường âm thanh trái phải, nâng đỡ hơi thở an bình, hãy để giai điệu cùng bạn nếm trải sự đậm đà của thời gian'};
        r3['dimPairs'] = r0;
        r2 = {};
        r0 = 'U sầu là căn phòng xanh thẳm';
        r2['keyword'] = r0;
        r0 = {'high_freq+richness': 'Hòa âm đậm đà đan xen dải cao trong trẻo, như ánh sáng mờ nơi biển sâu, khiến mệt mỏi tan biến trong tầng âm học tinh tế', 'loudness+richness': 'Hòa âm trầm lắng hòa cùng âm lượng mượt mà, tạo cảm giác lơ lửng không trọng lượng, đến cả hơi thở cũng trở nên nhẹ nhàng và thư thái', 'rhythm+richness': 'Hòa âm đậm đà dâng lên theo nhịp điệu khoan thai, nhịp điệu ổn định khiến các dây thần kinh căng thẳng chìm xuống trong sắc xanh thẳm', 'richness+space_size': 'Tiếng vang không gian rộng mở đan xen hòa âm tinh tế, dư âm xanh thẳm vang vọng nơi đây, dùng âm học ban cho nội tâm sự tĩnh lặng', 'richness+stereo_width': 'Trường âm thanh nổi rộng mở thu gọn hòa âm sâu thẳm một cách chuẩn xác, tạo nên kết giới xanh thẳm riêng tư, cách ly huyên náo bên ngoài', 'richness+vocal_1': 'Giọng hát có chất liệu hòa quyện với hòa âm đắm chìm, đường giọng sâu thẳm, xoa dịu chuẩn xác từng tấc mệt mỏi', 'richness+vocal_2': 'Hòa âm mềm mại như sóng biển bao bọc giọng hát, hơi thở chậm lại theo sóng âm, tận hưởng sự chữa lành như dưới đáy biển sâu', 'richness+vocal_3': 'Hòa âm thoát tục bao quanh giọng hát đầy từ tính, độ phân giải rõ ràng khiến mọi phiền muộn lập tức tan biến theo ánh sáng bóng nước biển sâu', 'richness+vocal_4': 'Hòa âm sâu thẳm nâng đỡ vững vàng giọng hát có chất liệu, cảm nghe tinh tế và dịu dàng khiến người nghe an tâm chìm vào giấc mơ xanh thẳm', 'richness+vocal_5': 'Hòa âm đầy đặn bao bọc giọng hát một cách tinh tế, chuyển tiếp dải tần mượt mà mang lại trạng thái thả lỏng đắm chìm, tận hưởng sự tự do', 'high_freq+loudness_1': 'Dải cao trong trẻo phối cùng kiểm soát âm lượng tinh tế, sóng âm mềm mại gột rửa mọi phù hoa, chỉ còn lại sự thuần khiết và thoát tục', 'high_freq+loudness_2': 'Dải cao trong suốt hòa vào biên độ âm lượng mượt mà, như ánh sáng mờ nơi biển sâu lấp lánh, mang lại vẻ đẹp âm học trong trẻo', 'high_freq+rhythm_1': 'Dải cao trong trẻo lưu chuyển trong nhịp điệu khoan thai, chuyển động tức thời chuẩn xác khiến cả những nốt nhạc li ti cũng tràn đầy sức sống chữa lành', 'high_freq+rhythm_2': 'Dải cao trong suốt gợn theo nhịp điệu lười biếng, cảm giác chìm xuống ổn định đưa bạn rơi vào vùng thoải mái xanh thẳm', 'high_freq+rhythm_3': 'Dải cao thuần khiết điểm xuyết nhịp điệu chậm tùy hứng, khoảng lặng vừa vặn tỏa ra chất liệu lười biếng và thư giãn', 'high_freq+space_size': 'Dải cao tinh khiết khúc xạ trong tiếng vang không gian rộng mở, tạo chiều sâu cực mạnh, khiến tâm trí trải rộng vô hạn', 'high_freq+stereo_width_1': 'Dải cao sáng trong dạo bước tinh tế trong trường âm thanh vòm, cảm giác áp tai phân giải cao khiến người nghe lập tức buông bỏ phòng bị', 'high_freq+stereo_width_2': 'Dải cao mềm mại hòa vào trường âm thanh nổi riêng tư, định vị âm hình chuẩn xác mang lại cảm giác bao bọc như dưới biển sâu', 'high_freq+vocal_1': 'Dải cao trong trẻo và thấu suốt, giọng hát thoát tục chạm tai, như một cơn gió biển sâu thổi tan mọi nặng nề và mệt mỏi', 'high_freq+vocal_2': 'Dải cao tinh tế mạ lên giọng hát ánh sáng mờ xanh thẳm, đường giọng miên man biến sự mệt mỏi không lời thành lời bày tỏ dịu dàng', 'high_freq+vocal_3': 'Dải cao mềm mại như sóng biển phất qua giọng hát, xử lý dải tần sạch sẽ, khiến tâm sự được âm học chuyên nghiệp chữa lành', 'high_freq+vocal_4': 'Dải cao thoát tục đan xen giọng hát sâu thẳm, tạo cảm giác không trọng lượng vừa vặn, đắm chìm trong kết giới xanh thẳm', 'high_freq+vocal_5': 'Dải cao mượt mà loại bỏ cảm giác gắt của giọng hát, chất âm tự nhiên khiến cảm xúc xuống thấp nở ra như đóa hoa biển sâu', 'loudness+rhythm_1': 'Chuyển biến âm lượng mượt mà hòa cùng nhịp điệu chậm của biển sâu, làm chậm dòng chảy thời gian, mang lại trải nghiệm nghe tinh tế', 'loudness+rhythm_2': 'Âm lượng mềm mại dâng lên trong nhịp điệu lười biếng, cưỡi lên dòng hải lưu xanh thẳm, tận hưởng cảm giác lơ lửng không áp lực', 'loudness+space_size': 'Kiểm soát âm lượng tinh vi lấp đầy tiếng vang không gian sâu thẳm, tạo nên vùng nước tĩnh mịch đầy bao dung', 'loudness+stereo_width': 'Âm lượng tinh tế hòa cùng trường âm thanh nổi vòm một cách tự nhiên, như được sắc xanh thẳm bao bọc, tận hưởng cảm giác an toàn tuyệt đối', 'loudness+vocal_1': 'Giọng hát khẽ ngân hòa cùng khả năng nắm bắt âm lượng tinh tế, giữ lại hơi thở chân thực, chữa lành mọi điều không vui', 'loudness+vocal_2': 'Nén âm lượng mượt mà khiến giọng hát sâu thẳm toát lên vẻ mượt mà, sự mệt mỏi trong đáy lòng được chất âm ấm áp an bài thỏa đáng', 'loudness+vocal_3': 'Âm lượng tiết chế và cân bằng nâng đỡ giọng hát thoát tục, những tâm sự nặng nề lập tức được sóng âm biển sâu làm tan chảy', 'rhythm+space_size': 'Nhịp điệu khoan thai lan trong tiếng vang không gian xanh thẳm, thời gian như ngừng lại, mang lại sự chữa lành sâu bên trong', 'rhythm+stereo_width': 'Nhịp điệu lười biếng đung đưa trong trường âm thanh nổi rộng mở, cảm nghe như không trọng lượng đưa bạn vào thiền định xanh thẳm', 'rhythm+vocal_1': 'Giọng hát đầy từ tính khớp chính xác với nhịp điệu chậm của biển sâu, cảm giác tự nhiên và thư thái khiến các dây thần kinh căng thẳng lập tức buông lỏng', 'rhythm+vocal_2': 'Giọng hát tự do tuôn chảy theo nhịp điệu lười biếng, cảm giác chìm xuống ổn định khiến bạn cam tâm đắm chìm trong đó', 'space_size+stereo_width': 'Tiếng vang không gian rộng mở kết hợp trường âm thanh nổi toàn cảnh, tạo nên kết giới xanh thẳm sâu lắng và tĩnh mịch đến cực độ', 'space_size+vocal_1': 'Giọng hát thoát tục vang vọng trong không gian vang xanh thẳm, dư âm trong trẻo phác họa tầng lớp phong phú, xoa dịu mọi gợn sóng', 'space_size+vocal_2': 'Giọng hát đầy sức xuyên thấu vang khắp không gian vô tận, âm học vang vừa vặn khiến cảm xúc được giải phóng', 'stereo_width+vocal_1': 'Giọng hát vòm dạo bước trong trường âm thanh nổi riêng tư, cảm giác áp tai phân giải cao khiến bạn an tâm tận hưởng khoảng riêng', 'stereo_width+vocal_2': 'Trường âm thanh nổi rộng mở bao bọc vững vàng giọng hát có chất liệu, trải nghiệm đắm chìm sâu lắng gột rửa mọi cảm giác mệt mỏi', 'stereo_width+vocal_3': 'Trường âm thanh nổi tạo khoang an toàn riêng cho giọng hát có chất liệu, lời thì thầm ấm áp chỉ để mang lại cho bạn sự đồng hành chuyên nghiệp nhất'};
        r2['dimPairs'] = r0;
        r0 = {};
        r1 = 'Mỗi tâm trạng đều có tiếng vọng';
        r0['keyword'] = r1;
        r1 = {'high_freq+richness': 'Hòa âm tinh tế đan xen dải cao trong suốt, độ phân giải cao khiến chất âm thuần khiết không tì vết, lặng lẽ đắm chìm trong giai điệu tinh khôi', 'loudness+richness': 'Hòa âm ấm áp hòa cùng âm lượng cân bằng, kiểm soát động mượt mà mang lại cảm nghe tự nhiên, an tâm tận hưởng âm nhạc trong khoảnh khắc này', 'rhythm+richness': 'Hòa âm thư thái bám sát nhịp điệu tự nhiên, đáp ứng tức thời tuyệt vời khiến người nghe yên lặng lắng nghe từng giai điệu thuần khiết', 'richness+space_size': 'Hòa âm tinh tế hòa vào tiếng vang không gian trong trẻo, chiều sâu âm học thấu suốt mang lại trải nghiệm nghe nhạc đắm chìm đến cực độ', 'richness+stereo_width': 'Hòa âm cân bằng trải rộng trong trường âm thanh nổi mênh mông, định vị âm hình chuẩn xác tạo nên khu vực nghe riêng tư, không bị quấy nhiễu', 'richness+vocal_1': 'Giọng hát có chất liệu hòa quyện liền mạch với hòa âm ấm áp, xử lý dải tần mượt mà để bạn yên lặng nghe nhạc và cảm nhận giai điệu tuôn chảy', 'richness+vocal_2': 'Hòa âm tinh tế nâng đỡ ổn định giọng hát trong trẻo, khả năng hòa bè tuyệt vời mang lại cảm nghe cao cấp, yên lặng tận hưởng âm nhạc', 'richness+vocal_3': 'Hòa âm mềm mại ôm trọn giọng hát có chất liệu, âm hình trung âm rõ ràng khiến thời gian yên lặng nghe nhạc trở nên chữa lành lạ thường', 'richness+vocal_4': 'Hòa âm cân bằng đan xen tinh tế với giọng hát trong trẻo, khả năng kiểm soát dải tần tuyệt vời mang lại cảm nghe thư thái, đắm chìm trong tĩnh lặng', 'richness+vocal_5': 'Hòa âm đầy đặn bao quanh giọng hát có chất liệu, nền âm thanh chắc chắn mang lại cảm nghe phân lớp rõ ràng, cứ yên lặng nghe nhạc là đủ', 'high_freq+loudness_1': 'Dải cao trong suốt hòa cùng kiểm soát âm lượng ổn định, sức căng áp lực âm tinh tế mang lại cảm nghe thuần khiết, khoảnh khắc này chỉ có âm nhạc', 'high_freq+loudness_2': 'Dải cao sạch sẽ hòa vào biên độ âm lượng cân bằng, nén động mượt mà để bạn lặng lẽ cảm nhận từng chi tiết âm nhạc trong sự thoải mái', 'high_freq+rhythm_1': 'Dải cao mượt mà khớp chính xác với nhịp điệu khoan thai, đáp ứng tức thời rõ ràng mang lại không khí đắm chìm, yên lặng tận hưởng việc nghe nhạc', 'high_freq+rhythm_2': 'Dải cao nhẹ nhàng dâng lên theo nhịp điệu tự nhiên, khả năng bám động tuyệt vời khiến bạn đắm chìm trong thế giới âm nhạc thuần khiết', 'high_freq+rhythm_3': 'Dải cao trong suốt điểm xuyết nhịp trống khoan thai, chuyển động âm học mềm mại mang lại cảm nghe tự nhiên, an tâm tận hưởng âm nhạc', 'high_freq+space_size': 'Dải cao tinh khiết khúc xạ trong tiếng vang không gian rộng mở, âm bội âm học tự nhiên khiến không khí nghe nhạc càng thêm đắm chìm', 'high_freq+stereo_width_1': 'Dải cao linh hoạt trải rộng cân bằng trong trường âm thanh nổi, cảm nghe vòm phân giải cao khiến việc yên lặng nghe nhạc trở nên dễ chịu lạ thường', 'high_freq+stereo_width_2': 'Dải cao mượt mà dạo bước trong trường âm thanh nổi rộng mở, âm hình chuẩn xác mang lại trải nghiệm âm nhạc đắm chìm sâu sắc', 'high_freq+vocal_1': 'Độ phân giải dải cao khiến giọng hát tinh tế và trong suốt, âm hình chất âm chắc chắn mang lại cảm nghe dễ chịu, tĩnh lặng thưởng thức giai điệu dịu dàng', 'high_freq+vocal_2': 'Tăng ích dải cao khiến giọng hát càng sạch và mềm mại, xử lý dải tần mượt mà để bạn lặng lẽ lắng nghe tiếng hát tinh tế tuôn chảy', 'high_freq+vocal_3': 'Dải cao tinh tế làm nền cho giọng hát trong sáng, năng lượng âm tần ổn định mang lại cảm nghe ấm áp, yên lặng tận hưởng khoảnh khắc này', 'high_freq+vocal_4': 'Dải cao trong trẻo nâng giọng hát có chất liệu trải rộng, dải cao tự nhiên mang lại trải nghiệm nghe nhạc đắm chìm, thư thái và bình hòa', 'high_freq+vocal_5': 'Dải cao mượt mà loại bỏ tạp âm giọng hát, chất âm trong sáng tự nhiên tái hiện chi tiết rõ ràng, yên lặng nghe nhạc là tuyệt nhất', 'loudness+rhythm_1': 'Âm lượng chắc chắn dẫn dắt nhịp điệu khoan thai, đáp ứng động ổn định khiến thời gian lặng lẽ tuôn chảy trong nốt nhạc', 'loudness+rhythm_2': 'Âm lượng cân bằng đẩy căng sức căng của nhịp điệu êm ả, kiểm soát áp lực âm tinh tế để bạn đắm chìm tận hưởng nhịp điệu âm nhạc thuần khiết', 'loudness+space_size': 'Âm lượng đầy đặn lấp đầy tiếng vang không gian trong suốt, sức căng áp lực âm lan tỏa tự nhiên khiến tầng lớp nghe nhạc càng rõ ràng', 'loudness+stereo_width': 'Âm lượng cân bằng trải khắp trường âm thanh nổi rộng mở, động học vòm đầy đặn khiến từng nốt nhạc rõ ràng khẽ nhảy nhót bên tai', 'loudness+vocal_1': 'Giọng hát có chất liệu được giải phóng cùng âm lượng cân bằng, mức áp lực âm vừa vặn để bạn yên lặng thưởng thức từng câu ngân', 'loudness+vocal_2': 'Giọng hát đầy đặn hòa cùng tăng ích âm lượng ấm áp, đường giọng mượt mà đầy sức lay động, lặng lẽ tận hưởng vẻ đẹp của âm nhạc', 'loudness+vocal_3': 'Âm lượng ổn định ban cho giọng hát sức căng tinh tế, cộng hưởng trong trẻo mang lại cảm nghe bình hòa, thư thái, cứ yên lặng lắng nghe là đủ', 'rhythm+space_size': 'Nhịp điệu khoan thai lấp đầy tiếng vang không gian trong trẻo, chiều sâu âm học miên man khiến không khí yên lặng nghe nhạc tràn đầy', 'rhythm+stereo_width': 'Nhịp điệu êm ả luân phiên trái phải trong trường âm thanh nổi, nhịp điệu rõ ràng bao trùm mọi hướng, khiến từng nốt nhạc đều đang kể chuyện', 'rhythm+vocal': 'Giọng hát có chất liệu bám sát nhịp điệu khoan thai, chuyển động tức thời tuyệt vời khiến đường giọng và phách hòa quyện hoàn hảo, yên lặng cảm nhận', 'space_size+stereo_width': 'Tiếng vang không gian trong suốt chồng lên trường âm thanh nổi toàn cảnh, sóng âm cân bằng bao quanh tự nhiên, yên lặng nghe nhạc chữa lành đến cực độ', 'space_size+vocal_1': 'Giọng hát có chất liệu vang vọng trong tiếng vang không gian trong suốt, ngân dài rõ ràng lan tỏa tự nhiên, yên lặng tận hưởng khoảnh khắc âm nhạc', 'space_size+vocal_2': 'Giọng hát trong trẻo khẽ bung sức trong tiếng vang không gian, cộng hưởng âm học vững vàng mang lại bầu không khí bình hòa, tĩnh tâm nghe nhạc', 'stereo_width+vocal_1': 'Giọng hát có chất liệu trải rộng trong trường âm thanh nổi, định vị âm hình chuẩn xác mang lại sự bao bọc ấm áp, yên lặng nghe nhạc đắm chìm', 'stereo_width+vocal_2': 'Trường âm thanh nổi rộng mở nâng đỡ ổn định giọng hát trong trẻo, âm tần vòm đầy đặn mang lại trải nghiệm âm nhạc sạch sẽ và thuần khiết', 'stereo_width+vocal_3': 'Giọng hát có chất liệu lan tỏa trong trường âm thanh nổi rộng mở, âm hình chuẩn xác bao quanh rõ ràng bên tai, yên lặng nghe nhạc'};
        r0['dimPairs'] = r1;
        r1 = {};
        r1['ecstatic'] = r16;
        r1['passionate'] = r15;
        r1['energetic'] = r14;
        r1['joyful'] = r13;
        r1['sweet'] = r12;
        r1['confident'] = r11;
        r1['romantic'] = r10;
        r1['relaxed'] = r9;
        r1['healing'] = r8;
        r1['calm'] = r7;
        r1['nostalgic'] = r6;
        r1['lonely'] = r5;
        r1['sad'] = r4;
        r1['bitter'] = r3;
        r1['depressed'] = r2;
        r1['default'] = r0;
        r0 = {'keyword': 'Mỗi tâm trạng đều có tiếng vọng', 'description': 'Âm nhạc đang phát, từng chi tiết của âm thanh đều đang được tái hiện cho bạn'};
        r1['fallback'] = r0;
        r0 = a4;
        r0['exports'] = r1;
        r0 = undefined;
        return r0;
    };
factories[968]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r2 = a5;
        r1 = global;
        r5 = r1.Object;
        r4 = r5.defineProperty;
        r3 = {};
        r0 = true;
        r3['value'] = r0;
        r0 = '__esModule';
        r0 = r4.bind(r5)(r2, r0, r3);
        r0 = undefined;
        r2['EMOTION_BASELINES'] = r0;
        r2['EMOTION_INTENSITY_THRESHOLDS'] = r0;
        r2['FALLBACK_BEFORE_RATIOS'] = r0;
        r2['FALLBACK_SONG_ACTIVATIONS'] = r0;
        r2['GENRE_BASELINES'] = r0;
        r2['HIGH_FREQ_SONG_ACT_COMPENSATION'] = r0;
        r2['INTENSITY_THRESHOLDS'] = r0;
        r2['LOUDNESS_SONG_ACT_COMPENSATION'] = r0;
        r2['SONG_FEATURE_RANGES'] = r0;
        r2['SONG_FEATURE_WEIGHTS'] = r0;
        r2['VISUAL_DIMENSIONS'] = r0;
        r3 = ['loudness', 'high_freq', 'vocal', 'rhythm', 'stereo_width', 'space_size', 'richness'];
        r2['VISUAL_DIMENSIONS'] = r3;
        r3 = [0.16, 0.29];
        r2['INTENSITY_THRESHOLDS'] = r3;
        r3 = {};
        r4 = [0.148, 0.239];
        r3['bitter'] = r4;
        r4 = [0.218, 0.383];
        r3['calm'] = r4;
        r4 = [0.178, 0.308];
        r3['confident'] = r4;
        r4 = [0.169, 0.334];
        r3['depressed'] = r4;
        r4 = [0.185, 0.308];
        r3['ecstatic'] = r4;
        r4 = [0.164, 0.293];
        r3['energetic'] = r4;
        r4 = [0.165, 0.269];
        r3['healing'] = r4;
        r4 = [0.128, 0.256];
        r3['joyful'] = r4;
        r4 = [0.222, 0.36];
        r3['lonely'] = r4;
        r4 = [0.137, 0.232];
        r3['nostalgic'] = r4;
        r4 = [0.123, 0.223];
        r3['passionate'] = r4;
        r4 = [0.161, 0.329];
        r3['relaxed'] = r4;
        r4 = [0.188, 0.31];
        r3['romantic'] = r4;
        r4 = [0.159, 0.25];
        r3['sad'] = r4;
        r4 = [0.121, 0.228];
        r3['sweet'] = r4;
        r2['EMOTION_INTENSITY_THRESHOLDS'] = r3;
        r3 = {};
        r4 = {'loudness': 0.228, 'high_freq': 0.385, 'vocal': 0.672, 'rhythm': 0.575, 'stereo_width': 0.857, 'space_size': 0.688, 'richness': 0.457};
        r3['default'] = r4;
        r4 = {'loudness': 0.361, 'high_freq': 0.539, 'vocal': 0.538, 'rhythm': 0.977, 'stereo_width': 0.92, 'space_size': 0.55, 'richness': 0.576};
        r3['ecstatic'] = r4;
        r4 = {'loudness': 0.342, 'high_freq': 0.578, 'vocal': 0.739, 'rhythm': 0.92, 'stereo_width': 0.685, 'space_size': 0.413, 'richness': 0.413};
        r3['passionate'] = r4;
        r4 = {'loudness': 0.323, 'high_freq': 0.578, 'vocal': 0.605, 'rhythm': 0.92, 'stereo_width': 0.594, 'space_size': 0.343, 'richness': 0.329};
        r3['energetic'] = r4;
        r4 = {'loudness': 0.323, 'high_freq': 0.616, 'vocal': 0.739, 'rhythm': 0.862, 'stereo_width': 0.513, 'space_size': 0.343, 'richness': 0.413};
        r3['joyful'] = r4;
        r4 = {'loudness': 0.228, 'high_freq': 0.424, 'vocal': 1, 'rhythm': 0.46, 'stereo_width': 0.594, 'space_size': 0.343, 'richness': 0.658};
        r3['sweet'] = r4;
        r4 = {'loudness': 0.323, 'high_freq': 0.539, 'vocal': 0.806, 'rhythm': 0.747, 'stereo_width': 0.685, 'space_size': 0.482, 'richness': 0.329};
        r3['confident'] = r4;
        r4 = {'loudness': 0.162, 'high_freq': 0.308, 'vocal': 0.941, 'rhythm': 0.287, 'stereo_width': 0.594, 'space_size': 0.757, 'richness': 0.742};
        r3['romantic'] = r4;
        r4 = {'loudness': 0.162, 'high_freq': 0.231, 'vocal': 0.739, 'rhythm': 0.345, 'stereo_width': 0.513, 'space_size': 0.618, 'richness': 0.413};
        r3['relaxed'] = r4;
        r4 = {'loudness': 0.162, 'high_freq': 0.27, 'vocal': 0.806, 'rhythm': 0.287, 'stereo_width': 0.513, 'space_size': 0.618, 'richness': 0.658};
        r3['healing'] = r4;
        r4 = {'loudness': 0.114, 'high_freq': 0.193, 'vocal': 0.605, 'rhythm': 0.23, 'stereo_width': 0.434, 'space_size': 0.757, 'richness': 0.413};
        r3['calm'] = r4;
        r4 = {'loudness': 0.18, 'high_freq': 0.27, 'vocal': 0.941, 'rhythm': 0.402, 'stereo_width': 0.594, 'space_size': 0.757, 'richness': 0.493};
        r3['nostalgic'] = r4;
        r4 = {'loudness': 0.162, 'high_freq': 0.154, 'vocal': 0.739, 'rhythm': 0.345, 'stereo_width': 0.513, 'space_size': 0.893, 'richness': 0.658};
        r3['lonely'] = r4;
        r4 = {'loudness': 0.162, 'high_freq': 0.154, 'vocal': 0.874, 'rhythm': 0.345, 'stereo_width': 0.513, 'space_size': 0.825, 'richness': 0.658};
        r3['sad'] = r4;
        r4 = {'loudness': 0.228, 'high_freq': 0.193, 'vocal': 0.739, 'rhythm': 0.517, 'stereo_width': 0.434, 'space_size': 0.893, 'richness': 0.742};
        r3['bitter'] = r4;
        r4 = {'loudness': 0.114, 'high_freq': 0.116, 'vocal': 0.672, 'rhythm': 0.23, 'stereo_width': 0.343, 'space_size': 0.963, 'richness': 0.493};
        r3['depressed'] = r4;
        r2['EMOTION_BASELINES'] = r3;
        r3 = {};
        r4 = {'loudness': 0.294, 'high_freq': 0.462, 'vocal': 1, 'rhythm': 0.575, 'stereo_width': 0.48, 'space_size': 0.343, 'richness': 0.484};
        r3['pop-family'] = r4;
        r4 = {'loudness': 0.162, 'high_freq': 0.27, 'vocal': 0.941, 'rhythm': 0.287, 'stereo_width': 0.399, 'space_size': 0.688, 'richness': 0.731};
        r3['folk-family'] = r4;
        r4 = {'loudness': 0.427, 'high_freq': 0.5, 'vocal': 0.538, 'rhythm': 0.977, 'stereo_width': 0.799, 'space_size': 0.413, 'richness': 0.275};
        r3['rhythm-family'] = r4;
        r4 = {'loudness': 0.19, 'high_freq': 0.347, 'vocal': 0.202, 'rhythm': 0.345, 'stereo_width': 0.92, 'space_size': 1, 'richness': 0.731};
        r3['instrumental-family'] = r4;
        r2['GENRE_BASELINES'] = r3;
        r6 = r1.Object;
        r5 = r6.assign;
        r3 = new Array(2);
        r4 = -40;
        r3[0] = r4;
        r4 = 0;
        r3[1] = r4;
        r4 = {};
        r7 = [60, 200];
        r4['repbpm'] = r7;
        r4['loudness_lufs'] = r3;
        r3 = [0, 30];
        r4['dynamic_range'] = r3;
        r3 = [0, 1];
        r4['stereo_width_ratio'] = r3;
        r7 = r1.Object;
        r3 = r7.fromEntries;
        r10 = r1.Array;
        r9 = r10.from;
        r8 = {};
        r1 = 32;
        r8['length'] = r1;
        r1 = function(a0, a1) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r1
            r1 = 'mel_';
            r0 = a1;
            r1 = r1 + r0;
            r0 = new Array(2);
            r0[0] = r1;
            r1 = new Array(2);
            r2 = -70;
            r1[0] = r2;
            r2 = 0;
            r1[1] = r2;
            r0[1] = r1;
            return r0;
        };
        r1 = r9.bind(r10)(r8, r1);
        r3 = r3.bind(r7)(r1);
        r1 = {};
        r7 = [0, 1];
        r1['vocal_time_ratio'] = r7;
        r7 = [0, 1];
        r1['vocal_energy_ratio'] = r7;
        r7 = [0, 1];
        r1['harmonic_intensity_ratio'] = r7;
        r7 = [0, 1];
        r1['drum_time_ratio'] = r7;
        r7 = [0, 10];
        r1['rhythm_complexity'] = r7;
        r7 = [0, 1];
        r1['drum_energy_ratio'] = r7;
        r7 = [0, 1200];
        r1['vocal_main_freq'] = r7;
        r7 = [0, 30];
        r1['vocal_dynamic_range'] = r7;
        r7 = [0, 1];
        r1['bass_energy_ratio'] = r7;
        r7 = [0, 1];
        r1['other_instrument_energy_ratio'] = r7;
        r1 = r5.bind(r6)(r4, r3, r1);
        r2['SONG_FEATURE_RANGES'] = r1;
        r9 = new Array(11);
        r1 = ['mel_0', 0.04];
        r9[0] = r1;
        r1 = ['mel_1', 0.04];
        r9[1] = r1;
        r1 = ['mel_2', 0.04];
        r9[2] = r1;
        r1 = ['mel_3', 0.04];
        r9[3] = r1;
        r1 = ['mel_4', 0.03];
        r9[4] = r1;
        r1 = ['mel_5', 0.03];
        r9[5] = r1;
        r1 = ['mel_6', 0.03];
        r9[6] = r1;
        r1 = ['mel_7', 0.03];
        r9[7] = r1;
        r1 = ['loudness_lufs', 0.4];
        r9[8] = r1;
        r1 = ['dynamic_range'];
        r3 = -0.3;
        r1[1] = r3;
        r9[9] = r1;
        r1 = ['bass_energy_ratio', 0.05];
        r9[10] = r1;
        r8 = new Array(8);
        r1 = ['mel_24', 0.13];
        r8[0] = r1;
        r1 = ['mel_25', 0.13];
        r8[1] = r1;
        r1 = ['mel_26', 0.12];
        r8[2] = r1;
        r1 = ['mel_27', 0.12];
        r8[3] = r1;
        r1 = ['mel_28', 0.13];
        r8[4] = r1;
        r1 = ['mel_29', 0.12];
        r8[5] = r1;
        r1 = ['mel_30', 0.13];
        r8[6] = r1;
        r1 = ['mel_31', 0.12];
        r8[7] = r1;
        r7 = new Array(17);
        r1 = ['mel_8', 0.055];
        r7[0] = r1;
        r1 = ['mel_9', 0.055];
        r7[1] = r1;
        r1 = ['mel_10', 0.055];
        r7[2] = r1;
        r1 = ['mel_11', 0.055];
        r7[3] = r1;
        r1 = ['mel_12', 0.055];
        r7[4] = r1;
        r1 = ['mel_13', 0.055];
        r7[5] = r1;
        r1 = ['mel_14', 0.055];
        r7[6] = r1;
        r1 = ['mel_15', 0.055];
        r7[7] = r1;
        r1 = ['mel_16', 0.055];
        r7[8] = r1;
        r1 = ['mel_17', 0.055];
        r7[9] = r1;
        r1 = ['mel_18', 0.055];
        r7[10] = r1;
        r1 = ['mel_19', 0.055];
        r7[11] = r1;
        r1 = ['vocal_time_ratio', 0.1];
        r7[12] = r1;
        r1 = ['vocal_energy_ratio', 0.08];
        r7[13] = r1;
        r1 = ['harmonic_intensity_ratio', 0.06];
        r7[14] = r1;
        r1 = ['vocal_main_freq', 0.05];
        r7[15] = r1;
        r1 = ['vocal_dynamic_range', 0.05];
        r7[16] = r1;
        r6 = new Array(6);
        r1 = ['repbpm', 0.28];
        r6[0] = r1;
        r1 = ['dynamic_range', 0.12];
        r6[1] = r1;
        r1 = ['drum_time_ratio', 0.22];
        r6[2] = r1;
        r1 = ['rhythm_complexity', 0.22];
        r6[3] = r1;
        r1 = ['drum_energy_ratio', 0.08];
        r6[4] = r1;
        r1 = ['bass_energy_ratio', 0.08];
        r6[5] = r1;
        r5 = new Array(1);
        r1 = ['stereo_width_ratio', 1];
        r5[0] = r1;
        r4 = new Array(2);
        r1 = ['dynamic_range', 0.6];
        r4[0] = r1;
        r1 = ['stereo_width_ratio', 0.4];
        r4[1] = r1;
        r3 = new Array(14);
        r1 = ['mel_16', 0.065];
        r3[0] = r1;
        r1 = ['mel_17', 0.065];
        r3[1] = r1;
        r1 = ['mel_18', 0.065];
        r3[2] = r1;
        r1 = ['mel_19', 0.065];
        r3[3] = r1;
        r1 = ['mel_20', 0.06];
        r3[4] = r1;
        r1 = ['mel_21', 0.06];
        r3[5] = r1;
        r1 = ['mel_22', 0.06];
        r3[6] = r1;
        r1 = ['mel_23', 0.06];
        r3[7] = r1;
        r1 = ['mel_24', 0.05];
        r3[8] = r1;
        r1 = ['mel_25', 0.05];
        r3[9] = r1;
        r1 = ['mel_26', 0.05];
        r3[10] = r1;
        r1 = ['mel_27', 0.05];
        r3[11] = r1;
        r1 = ['harmonic_intensity_ratio', 0.15];
        r3[12] = r1;
        r1 = ['other_instrument_energy_ratio', 0.15];
        r3[13] = r1;
        r1 = {};
        r1['loudness'] = r9;
        r1['high_freq'] = r8;
        r1['vocal'] = r7;
        r1['rhythm'] = r6;
        r1['stereo_width'] = r5;
        r1['space_size'] = r4;
        r1['richness'] = r3;
        r2['SONG_FEATURE_WEIGHTS'] = r1;
        r1 = 1.8;
        r2['HIGH_FREQ_SONG_ACT_COMPENSATION'] = r1;
        r1 = 0.57;
        r2['LOUDNESS_SONG_ACT_COMPENSATION'] = r1;
        r1 = {'loudness': 0.287, 'high_freq': 0.254, 'vocal': 0.421, 'rhythm': 0.252, 'stereo_width': 0.269, 'space_size': 0.25, 'richness': 0.277};
        r2['FALLBACK_SONG_ACTIVATIONS'] = r1;
        r1 = {'loudness': 0.85, 'high_freq': 0.83, 'vocal': 0.9, 'rhythm': 0.84, 'stereo_width': 0.85, 'space_size': 0.83, 'richness': 0.85};
        r2['FALLBACK_BEFORE_RATIOS'] = r1;
        return r0;
    };
factories[969]=function(a0, a1, a2, a3, a4, a5, a6) {
var r0,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34,r35,r36,r37,r38,r39,r40,r41,r42,r43,r44,r45,r46,r47,r48,r49,r50,r51,r52,r53,r54,r55,r56,r57,r58,r59,r60,r61,r62,r63,r64,r65,r66,r67,r68,r69,r70,r71,r72,r73,r74,r75,r76,r77,r78,r79,r80,r81,r82,r83,r84,r85,r86,r87,r88,r89,r90,r91,r92,r93,r94,r95,r96,r97,r98,r99; // Environment: r3
        r0 = global;
        r4 = r0.Object;
        r3 = r4.defineProperty;
        r2 = {};
        r0 = true;
        r2['value'] = r0;
        r1 = a5;
        r0 = '__esModule';
        r0 = r3.bind(r4)(r1, r0, r2);
        r0 = undefined;
        return r0;
    };

var dependencies = {"872": [873, 874], "873": [2, 10], "874": [2, 875, 876, 877, 878, 879, 880, 881, 882, 883, 884, 885, 886, 887, 888, 889, 890, 891, 892, 893, 894, 895, 896, 897, 898, 899, 900, 901, 902, 903, 904, 905, 906, 907, 908, 909, 910, 911, 912, 913, 914, 915, 916, 917, 918, 919, 920, 921, 922, 923, 924, 925, 926, 927, 928, 929, 930, 931, 932, 933, 934, 935, 936, 937, 938], "875": [], "876": [], "877": [], "878": [], "879": [], "880": [], "881": [], "882": [], "883": [], "884": [], "885": [], "886": [], "887": [], "888": [], "889": [], "890": [], "891": [], "892": [], "893": [], "894": [], "895": [], "896": [], "897": [], "898": [], "899": [], "900": [], "901": [], "902": [], "903": [], "904": [], "905": [], "906": [], "907": [], "908": [], "909": [], "910": [], "911": [], "912": [], "913": [], "914": [], "915": [], "916": [], "917": [], "918": [], "919": [], "920": [], "921": [], "922": [], "923": [], "924": [], "925": [], "926": [], "927": [], "928": [], "929": [], "930": [], "931": [], "932": [], "933": [], "934": [], "935": [], "936": [], "937": [], "938": [], "939": [940, 941, 943, 944], "940": [873], "941": [2, 10, 942, 873, 940], "942": [], "943": [873], "944": [873], "945": [], "949": [2, 325, 10, 950, 873], "950": [], "952": [942, 949, 873], "953": [2, 10, 942, 873, 949], "957": [2, 10, 958, 959, 945, 961, 962], "958": [2, 4, 873], "959": [2, 4, 10, 942, 945, 960, 873], "960": [], "961": [962], "962": [], "963": [964, 969], "964": [2, 14, 15, 10, 4, 965, 966, 873, 968], "965": [2, 945, 966, 967], "966": [962], "967": [], "968": [], "969": []};
function load(id){
 if(id===2)return function(x){return x&&x.__esModule?x:{default:x}};
 if(id===4)return function(x,n){return Array.from(x).slice(0,n)};
 if(id===10)return function(x){return Array.from(x)};
 if(id===14)return function(instance,Constructor){if(!(instance instanceof Constructor))throw new TypeError('Cannot call a class as a function')};
 if(id===15)return function(Constructor,proto,statics){function define(target,props){if(props)props.forEach(function(p){p.enumerable=p.enumerable||false;p.configurable=true;if('value' in p)p.writable=true;Object.defineProperty(target,p.key,p)})}define(Constructor.prototype,proto);define(Constructor,statics);return Constructor};
 if(id===325)return function(o,k,v){o[k]=v;return o};
 if(cache[id])return cache[id].exports;
 if(!factories[id])throw Error('missing '+id);
 var module={exports:{}};cache[id]=module;
 factories[id](global,load,null,null,module,module.exports,dependencies[id]);return module.exports;
}

function moodPrepare(row, signature, config) {
 var processed=load(957).preprocess(row), input=processed.input;
 config=config||{};
 var overrides=config.emotionTagOverride||{};
 Object.keys(overrides).some(function(label){
   if(Array.isArray(overrides[label])&&overrides[label].indexOf(row.song_id)>=0&&load(945).isEmotionLabel(label)){
     input.emotion_label=label;input.emotion_idx=load(945).EMOTION_INDEX[label];input.emotion_source='config_override';return true;
   }return false;
 });
 var force=Array.isArray(config.defaultTemplateSongIds)&&config.defaultTemplateSongIds.indexOf(row.song_id)>=0;
 var template=load(872).resolveModel64Template(force?'default':input.emotion_label,input.family);
 var payload=Object.assign({},input,{module_switches:template.moduleSwitches,base_extra_aev2:load(939).buildBaseExtraAev2FromTemplate(template.effectParams)});
 var canInfer=processed.availability.hasAllAudioFeatures&&!processed.fallbackReason&&!force;
 return {input:input,template:template,tensors:canInfer?load(953).encodeEmoFX(payload,signature):[],infer:!!canInfer,
    signature:signature,config:config,source:canInfer?'model':'preset_default',fallbackReason:processed.fallbackReason||(!canInfer?'missing audio features':undefined)};
}
function moodFinish(prepared, output) {
 var fx=load(939), input=prepared.input, params;
 if(prepared.infer){
  var decoded=load(952).decodeEmoFX([{name:'aev2',dtype:'float32',shape:[1,32],data:output}],prepared.signature,prepared.template.moduleSwitches);
  params=fx.mergeDecodedAev2IntoTemplate(prepared.template.effectParams,decoded);
 } else params=fx.prepareEffectTemplate(prepared.template.effectParams);
 params=fx.applySshaperRule(params,input.chorus_loudness).params;
 if(prepared.config.supportStereo===true)params=fx.applyMultichannelPostProcess(params,input.emotion_idx);
 if(Array.isArray(prepared.config.degradeSongIds)&&prepared.config.degradeSongIds.indexOf(prepared.config.songId)>=0)params=fx.applyDegradation(params);
 var visual=load(963).getVisualExtraStrategy().compute(input,params,{debug:function(){}});
 return {aev2:params,source:prepared.source,emotion:input.emotion_label,family:input.family,fallbackReason:prepared.fallbackReason,visualExtra:visual};
}
if(typeof module!=='undefined')module.exports={load:load,moodPrepare:moodPrepare,moodFinish:moodFinish};
