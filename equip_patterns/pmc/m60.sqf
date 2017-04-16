if (!isServer) exitWith {};
_unit = _this select 0;

removeAllWeapons _unit;
removeAllItems _unit;
removeAllAssignedItems _unit;
removeUniform _unit;
removeVest _unit;
removeBackpack _unit;
removeHeadgear _unit;
removeGoggles _unit;
_unit addItemToVest 'rhsusf_100Rnd_762x51';
_unit addItemToVest 'rhsusf_100Rnd_762x51_m62_tracer';
_unit addBackpack 'usm_pack_alice';
for '_i' from 1 to 3 do {_unit addItemToBackpack 'rhsusf_100Rnd_762x51_m62_tracer';};
for '_i' from 1 to 2 do {_unit addItemToBackpack 'rhsusf_100Rnd_762x51_m80a1epr';};
_unit addWeapon 'hlc_lmg_M60E4';
