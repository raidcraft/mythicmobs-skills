# Raid-Craft MythicMobs Skills

[![Build Status](https://github.com/raidcraft/mythicmobs-skills/workflows/Build/badge.svg)](../../actions?query=workflow%3ABuild)
[![GitHub release (latest SemVer including pre-releases)](https://img.shields.io/github/v/release/raidcraft/mythicmobs-skills?include_prereleases&label=release)](../../releases)
[![codecov](https://codecov.io/gh/raidcraft/mythicmobs-skills/branch/master/graph/badge.svg)](https://codecov.io/gh/raidcraft/mythicmobs-skills)
[![Commitizen friendly](https://img.shields.io/badge/commitizen-friendly-brightgreen.svg)](http://commitizen.github.io/cz-cli/)
[![semantic-release](https://img.shields.io/badge/%20%20%F0%9F%93%A6%F0%9F%9A%80-semantic--release-e10079.svg)](https://github.com/semantic-release/semantic-release)

A collection of custom MythicMobs skills used on the Raid-Craft server.

* [Full Prison](#full-prison)

## Full Prison

Sets a full 3x2x3 prison around the player with an optional top and bottom. This extends the default `prison` mechanic.

| Attribute | Aliases | Description | Default Value |
| --------- | ------- | ----------- | ------------- |
| material  | m | Any valid bukkit materialtype. | iron_bars |
| baseMaterial  | bm | Any valid bukkit materialtype. Should be a solid block to avoid falling through. | iron_block |
| topMaterial  | tm | Any valid bukkit materialtype. | barrier |
| duration | d |How long (in ticks) the prison will last. | 100 |
| breakable | b | (true/false) Whether or not the prison blocks can be broken. | false |
| fullBase | fb | (true/false) Whether or not the prison should have a 3x3 base or just a single block. | false |
| fullTop | ft | (true/false) Whether or not the prison should have a 3x3 top or just a single block. | false |
